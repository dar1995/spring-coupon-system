package com.dar.coupon.system.project2.controllers;

import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.login.LoginManagerService;
import com.dar.coupon.system.project2.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    LoginManagerService loginManagerService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@RequestBody User user, @RequestParam String val) throws CouponSystemExceptions {
        loginManagerService.register(user, ClientType.valueOf(val));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<UUID, Integer> login(@RequestParam String email, @RequestParam String password, @RequestParam ClientType clientType) throws CouponSystemExceptions {
        return loginManagerService.login(email, password, clientType);
    }


}
