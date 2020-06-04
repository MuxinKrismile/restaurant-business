package com.recommend.business.service;

import com.recommend.business.bean.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.authUrl}")
    private String loginUrl;

    public AuthResponse auth(String token) {
        ResponseEntity<AuthResponse> response = this.restTemplate.getForEntity(this.loginUrl + "?token=" + token, AuthResponse.class);
        return response.getBody();
    }

    public String getAuthUrl() {
        return this.loginUrl;
    }
}
