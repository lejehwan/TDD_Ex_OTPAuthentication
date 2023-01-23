package org.example.service;

import org.example.domain.OTPType;

public class TOTPConfirmService implements OTPConfirm{

    @Override
    public boolean authorize(Long userId, String otpPassword) {
        return false;
    }

    @Override
    public int getOTPType() {
        return OTPType.TOTP.getType();
    }
}
