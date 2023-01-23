package org.example.service;

import org.example.domain.OTPType;

public class HOTPConfirmService implements OTPConfirm{

    @Override
    public boolean authorize(Long userId, String otpPassword) {
        return false;
    }

    @Override
    public int getOTPType() {
        return OTPType.HOTP.getType();
    }
}
