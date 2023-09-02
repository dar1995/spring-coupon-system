package com.dar.coupon.system.project2.Job;

import com.dar.coupon.system.project2.security.Information;

import com.dar.coupon.system.project2.security.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenRemoval {
    @Autowired
    private TokenServiceImpl tokenService;
    private Map<UUID, Information> map = new HashMap<>();

    @Scheduled(fixedRate = 1000)
    public void removeExpiredTokens(){
        map = tokenService.getTokens();
        for (UUID uuid:map.keySet()) {
            if (map.get(uuid).getTime().plusMinutes(30).isBefore(LocalDateTime.now())){
                map.remove(uuid);
                tokenService.setTokens(map);

            }
        }
    }
}
