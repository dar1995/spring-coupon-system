package com.dar.coupon.system.project2.security;

import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.login.User;

import java.util.UUID;

public interface TokenService {
    UUID addToken(User user);

    boolean isUserAllowed(UUID token, ClientType clientType) throws CouponSystemExceptions;
    int idFromUuid(UUID token);

}
