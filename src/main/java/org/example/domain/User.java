package org.example.domain;

import lombok.Getter;

@Getter
public class User {

    private Long id;
    private String userName;
    private String password;
    private Long otpId;

    public User(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

}
