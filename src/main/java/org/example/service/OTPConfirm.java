package org.example.service;

public interface OTPConfirm {

    public boolean authorize(Long userId, String otpPassword);

    public int getOTPType();

}
