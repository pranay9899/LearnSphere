package com.learnsphere.app.services;

import com.learnsphere.app.entity.User;
import com.learnsphere.app.entity.mapper.VerificationToken;
import com.learnsphere.app.entity.mapper.VerificationToken.TokenType;
import com.learnsphere.app.entity.dto.RegisterRequest;
import com.learnsphere.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final VerificationTokenService tokenService;
    private final EmailService emailService;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserService userService,
                           VerificationTokenService tokenService,
                           EmailService emailService,
                           UserRepository userRepository) {

        this.userService = userService;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        // Save user as PENDING
        var dto = userService.register(request);

        // Create verification token (valid 1 hour)
        VerificationToken token = tokenService.createToken(
                dto.getEmail(),
                TokenType.VERIFY_EMAIL,
                Instant.now().plusSeconds(3600)
        );

        // Build verification URL
        String verificationLink = "http://localhost:8080/auth/verify?token=" + token.getToken();

        // Send email
        emailService.sendVerificationEmail(dto.getEmail(), verificationLink);
    }

    @Override
    @Transactional
    public void verifyEmail(String token) {
        // Validate token
        VerificationToken vt = tokenService.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid verification token"));

        if (vt.getExpiryTime().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Verification token has expired");
        }

        User user = userRepository.findByEmail(vt.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Activate account
        user.setStatus(User.Status.ACTIVE);
        userRepository.save(user);

        // Remove token
        tokenService.delete(vt);
    }
}
