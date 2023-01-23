package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoogleOTP {

    private Long id;
    private String otpKey;
    private OTPType otpType;
}
