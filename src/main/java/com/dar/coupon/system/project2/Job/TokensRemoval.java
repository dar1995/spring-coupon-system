package com.dar.coupon.system.project2.Job;

import com.dar.coupon.system.project2.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokensRemoval {
    @Autowired
    TokenService tokenService;
    public void tokenRemove(){

    }
}
