package com.dar.coupon.system.project2.exceptions;

public class CouponSystemExceptions extends Exception {
    public CouponSystemExceptions(ErrMsg errMsg) {
        super(errMsg.getMessage());
    }
}
