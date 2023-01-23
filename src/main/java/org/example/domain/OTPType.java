package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OTPType {

    TOTP(0, "totp"),
    HOTP(1, "hotp");

    private int type;
    private String typeName;
}
