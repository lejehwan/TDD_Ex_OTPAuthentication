package org.example.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class User {

    private Long id;
    private String userName;
    private String password;
    @Setter
    private Long otpId;

    public User(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

}
