package com.dar.coupon.system.project2.login;

import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;

import java.util.Map;
import java.util.UUID;

public interface LoginManagerService {
    void register(User user, ClientType clientType) throws CouponSystemExceptions;
    Map<UUID, Integer> login(String email, String password, ClientType clientType) throws CouponSystemExceptions;

}
