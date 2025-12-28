package com.learnsphere.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

/**
 * Email service for sending verification / transactional emails.
 */
@Service
public class EmailService {

    private final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    /**
     * Configurable from address. Mapped from 'spring.mail.from' (or 'spring.mail.username').
     * Using a sensible default if the property is not explicitly set.
     */
    @Value("${spring.mail.username: npranay9899@gmail.com}")
    private String fromAddress;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a verification email. Returns false and logs details on failure.
     *
     * @param to               recipient email
     * @param verificationLink verification URL to include in email
     * @return true if email send was attempted successfully, false otherwise
     */
    public boolean sendVerificationEmail(String to, String verificationLink) {
        if (mailSender == null) {
//            log.warn("JavaMailSender is not available. Verification link for {}: {}", to, verificationLink);
            log.warn("JavaMailSender is not available. Verification link for {}: {} (From: {})",
                    to,
                    verificationLink,
                    fromAddress);
            return false;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();

            // false = not multipart (no attachments); use UTF-8 encoding
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    false,
                    StandardCharsets.UTF_8.name()
            );

            helper.setFrom(fromAddress);
            helper.setTo(to);
            helper.setSubject("Verify your email");

            // HTML Email Content
            String html = "<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "<head><title>Verify Your Email</title></head>"
                    + "<body>"
                    + "<h2>Welcome to LearnSphere!</h2>"
                    + "<p>Thank you for registering. Please click the link below to verify your email address:</p>"
                    + "<p style=\"padding: 10px 0;\"><a href=\"" + verificationLink + "\" "
                    + "style=\"background-color: #007bff; color: white; padding: 10px 15px; text-decoration: none; border-radius: 5px; display: inline-block;\">"
                    + "Verify Email Address"
                    + "</a></p>"
                    + "<p>If the button doesn't work, you can copy and paste the following link into your browser:</p>"
                    + "<p><code>" + verificationLink + "</code></p>"
                    + "<p>If you did not request this, please ignore this email.</p>"
                    + "<p>The LearnSphere Team</p>"
                    + "</body></html>";

            // true = content is HTML
            helper.setText(html, true);

            mailSender.send(message);
            log.info("Verification email sent successfully to {}", to);
            return true;
        } catch (Exception ex) {

            log.error("Failed to send verification email to {}. Verification link: {}. (From: {}) Exception: {}",
                    to,
                    verificationLink,
                    fromAddress, // <-- Third argument for the sender email
                    ex.toString());

            log.debug("Full exception while sending email", ex);
            return false;
        }
    }
}