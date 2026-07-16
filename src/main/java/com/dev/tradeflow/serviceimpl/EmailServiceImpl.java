package com.dev.tradeflow.serviceimpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dev.tradeflow.entity.User;
import com.dev.tradeflow.service.EmailService;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

@Service
public class EmailServiceImpl implements EmailService {

    private final Resend resend;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${app.email.from}")
    private String fromEmail;

    public EmailServiceImpl(Resend resend) {
        this.resend = resend;
    }

    @Override
    public void sendVerificationEmail(User user, String token) {

        String verificationLink =
                baseUrl + "/api/auth/verify?token=" + token;

        String html = """
                <html>
                <body style="font-family:Arial,sans-serif;background:#f4f4f4;padding:30px;">
                    <div style="max-width:600px;margin:auto;background:#ffffff;padding:30px;border-radius:10px;">
                        <h2 style="color:#2563eb;">Welcome to TradeFlow</h2>

                        <p>Hello <strong>%s</strong>,</p>

                        <p>Thank you for registering with TradeFlow.</p>

                        <p>Please verify your email address by clicking the button below.</p>

                        <p style="text-align:center;">
                            <a href="%s"
                               style="background:#2563eb;
                                      color:white;
                                      text-decoration:none;
                                      padding:12px 24px;
                                      border-radius:6px;
                                      display:inline-block;">
                                Verify Email
                            </a>
                        </p>

                        <p>This verification link will expire in 24 hours.</p>

                        <hr>

                        <p style="font-size:12px;color:gray;">
                            If you didn't create this account, you can safely ignore this email.
                        </p>
                    </div>
                </body>
                </html>
                """
                .formatted(user.getFirstName(), verificationLink);

        try {

            CreateEmailOptions params = CreateEmailOptions.builder()
                    .from(fromEmail)
                    .to(user.getEmail())
                    .subject("Verify your TradeFlow account")
                    .html(html)
                    .build();

            CreateEmailResponse response = resend.emails().send(params);

            System.out.println("Email sent successfully.");
            System.out.println(response.getId());

        } catch (ResendException e) {

            throw new RuntimeException("Failed to send verification email.", e);

        }
    }
}