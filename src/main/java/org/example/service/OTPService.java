package org.example.service;

import org.example.repository.GoogleOTPRepository;

public class OTPService {

    private GoogleOTPRepository otpRepository;

    public OTPService(GoogleOTPRepository otpRepository){
        this.otpRepository = otpRepository;
    }

    public String getOTPType(Long id){
        return otpRepository.findById(id).getOtpType().getTypeName();
    }
}
