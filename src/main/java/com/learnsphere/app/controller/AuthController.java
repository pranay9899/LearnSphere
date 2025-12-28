package com.learnsphere.app.controller;

import com.learnsphere.app.configuration.security.JwtService;
import com.learnsphere.app.entity.User;
import com.learnsphere.app.entity.mapper.VerificationToken;
import com.learnsphere.app.entity.mapper.VerificationToken.TokenType;
import com.learnsphere.app.entity.dto.*;
import com.learnsphere.app.repository.UserRepository;
import com.learnsphere.app.services.*;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final VerificationTokenService tokenService;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          VerificationTokenService tokenService,
                          JwtService jwtService,
                          EmailService emailService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {

        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ---------------------------------------------------------------------
    // REGISTRATION + EMAIL VERIFICATION
    // ---------------------------------------------------------------------

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest req) {
        UserDto created = userService.register(req);

        // Create verification token (valid for 24 hours)
        VerificationToken vt = tokenService.createToken(
                created.getEmail(),
                TokenType.VERIFY_EMAIL,
                Instant.now().plusSeconds(24 * 3600)
        );

        // Build verification link
        String link = "http://localhost:8080/auth/verify?token=" + vt.getToken();

        // Send email
        emailService.sendVerificationEmail(created.getEmail(), link);

        return ResponseEntity.ok("Registration successful. Verification email sent.");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam("token") String token) {
        VerificationToken vt = tokenService.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid verification token"));

        if (vt.getExpiryTime().isBefore(Instant.now())) {
            return ResponseEntity.badRequest().body("Verification token expired");
        }

        User user = userRepository.findByEmail(vt.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setStatus(User.Status.ACTIVE);
        userRepository.save(user);

        tokenService.delete(vt);

        return ResponseEntity.ok("Email verification successful. You may now login.");
    }

    // ---------------------------------------------------------------------
    // LOGIN (JWT)
    // ---------------------------------------------------------------------

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthDTO authReq) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword())
        );

        UserDetails ud = (UserDetails) auth.getPrincipal();

        String role = ud.getAuthorities().stream()
                .findFirst().map(Object::toString).orElse("ROLE_STUDENT");

        String token = jwtService.generateToken(ud.getUsername(), role);

        userService.recordLogin(ud.getUsername());

        Long uid = userRepository.findByEmail(ud.getUsername()).map(User::getId).orElse(null);

        return ResponseEntity.ok(
                new AuthResponseDTO(token, uid, ud.getUsername(), role)
        );
    }

    // ---------------------------------------------------------------------
    // REQUEST PASSWORD RESET
    // ---------------------------------------------------------------------

    @PostMapping("/request-reset")
    public ResponseEntity<String> requestReset(@RequestParam("email") String email) {

        userService.findByEmail(email).ifPresent(u -> {
            VerificationToken token = tokenService.createToken(
                    email,
                    TokenType.RESET_PASSWORD,
                    Instant.now().plusSeconds(3600)
            );

            String resetLink = "http://localhost:8080/auth/reset?token=" + token.getToken();
            emailService.sendVerificationEmail(email, resetLink); // or send dedicated reset email
        });

        return ResponseEntity.ok("If the account exists, a password reset link has been sent.");
    }

    // ---------------------------------------------------------------------
    // RESET PASSWORD (CONSUME TOKEN)
    // ---------------------------------------------------------------------

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest req) {

        VerificationToken token = tokenService.findByToken(req.getToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (token.getExpiryTime().isBefore(Instant.now())) {
            return ResponseEntity.badRequest().body("Reset token expired");
        }

        if (token.getType() != TokenType.RESET_PASSWORD) {
            return ResponseEntity.badRequest().body("Invalid token type");
        }

        User user = userRepository.findByEmail(token.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Set new hashed password
        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        userRepository.save(user);

        // Delete token after use
        tokenService.delete(token);

        return ResponseEntity.ok("Password has been reset successfully.");
    }

    // ---------------------------------------------------------------------
    // LOGOUT (JWT is stateless)
    // ---------------------------------------------------------------------

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful. (Client must delete JWT)");
    }
}
