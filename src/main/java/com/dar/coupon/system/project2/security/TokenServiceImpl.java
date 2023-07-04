package com.dar.coupon.system.project2.security;

import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.login.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TokenServiceImpl  implements TokenService{
    private Map<UUID, Information> tokens = new HashMap<>();
    @Override
    public UUID addToken(User user) {
        UUID token = UUID.randomUUID();
        Information info = Information.builder()
                .id(user.getId())
                .time(LocalDateTime.now())
                .clientType(user.getClientType()).build();
        tokens.put(token, info);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tokens.remove(token);
            }
        }, 1000*60*30);
        return token;
    }

    @Override
    public boolean isUserAllowed(UUID token, ClientType clientType) throws CouponSystemExceptions {
        Information info = tokens.get(token);
        if (info == null){
            throw new CouponSystemExceptions(ErrMsg.SESSION_EXPIRED);
        }
        ClientType clientType1 = info.getClientType();
        return clientType1.equals(clientType);
    }
}
