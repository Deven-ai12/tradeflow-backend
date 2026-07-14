package com.dev.tradeflow.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dev.tradeflow.entity.User;
import com.dev.tradeflow.entity.VerificationToken;
import com.dev.tradeflow.repository.UserRepository;
import com.dev.tradeflow.repository.VerificationTokenRepository;
import com.dev.tradeflow.service.VerificationService;

import jakarta.transaction.Transactional;

@Service
public class VerificationServiceImpl implements VerificationService{

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;

    public VerificationServiceImpl(
            VerificationTokenRepository verificationTokenRepository,
            UserRepository userRepository) {

        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void verifyEmail(String token) {

        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Invalid verification token"));

        
        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Verification token has expired.");
        }

        User user = verificationToken.getUser();
        
        System.out.println("Before: " + user.getEnabled());
        
        user.setEnabled(true);
        
        System.out.println("After: " + user.getEnabled());
        
        userRepository.save(user);
        
        System.out.println("Saved");
        
        verificationTokenRepository.delete(verificationToken);
    }


}
