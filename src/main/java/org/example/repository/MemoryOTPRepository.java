package org.example.repository;

import org.example.domain.GoogleOTP;

import java.util.HashMap;
import java.util.Map;

public class MemoryOTPRepository implements GoogleOTPRepository{

    private Map<Long, GoogleOTP> otps = new HashMap<>();

    @Override
    public void register(GoogleOTP googleOTP) {
        otps.put(googleOTP.getId(), googleOTP);
    }

    @Override
    public GoogleOTP findById(Long id) {
        return otps.get(id);
    }
}
