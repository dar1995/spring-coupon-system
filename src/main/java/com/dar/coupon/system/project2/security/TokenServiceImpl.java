package com.dar.coupon.system.project2.security;

import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.login.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Getter
@Setter
public class TokenServiceImpl implements TokenService {
    private Map<UUID, Information> tokens = new HashMap<>();

    @Override
    public UUID addToken(User user) {
        UUID token = UUID.randomUUID();
        Information info = Information.builder()
                .id(user.getId())
                .time(LocalDateTime.now())
                .clientType(user.getClientType()).build();
        tokens.put(token, info);
        return token;
    }

    @Override
    public boolean isUserAllowed(UUID token, ClientType clientType) throws CouponSystemExceptions {
        Information info = tokens.get(token);
        if (info == null) {
            throw new CouponSystemExceptions(ErrMsg.SESSION_EXPIRED);
        }
        ClientType clientType1 = info.getClientType();
        return clientType1.equals(clientType);
    }

    @Override
    public int idFromUuid(UUID token) {
        Information info = tokens.get(token);
        int id = info.getId();
        return id;
    }
}
