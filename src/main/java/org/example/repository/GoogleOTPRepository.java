package org.example.repository;

import org.example.domain.GoogleOTP;

public interface GoogleOTPRepository {

    void register(GoogleOTP googleOTP);

    GoogleOTP findById(Long id);
}
