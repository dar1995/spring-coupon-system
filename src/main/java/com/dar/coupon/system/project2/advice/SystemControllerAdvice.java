package com.dar.coupon.system.project2.advice;

import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SystemControllerAdvice {
    @ExceptionHandler(value = CouponSystemExceptions.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleException(Exception e) {
        return new ErrDetails(e.getMessage());
    }
}
