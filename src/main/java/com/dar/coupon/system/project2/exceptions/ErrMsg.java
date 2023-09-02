package com.dar.coupon.system.project2.exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {
    COMPANY_ADD_INVALID_ID("The company ID must be initialized to 0 or left uninitialized"),
    COMPANY_ADD_NAME_EXIST("Cannot add company since name already exist"),
    COMPANY_ADD_EMAIL_EXIST("Cannot add company since email already exist"),
    COMPANY_NOT_EXIST("There are no company with this ID"),
    COMPANY_UPDATE_CANNOT_UPDATE_ID("Cannot update company ID"),
    COMPANY_UPDATE_CANNOT_UPDATE_NAME("Cannot update company name"),
    CUSTOMER_ADD_INVALID_ID("The customer ID must be initialized to 0 or left uninitialized"),
    CUSTOMER_ADD_EMAIL_EXIST("Cannot add customer since email already exist"),
    CUSTOMER_NOT_EXIST("There are no customer with this ID"),
    CUSTOMER_UPDATE_CANNOT_UPDATE_ID("Cannot update customer ID"),

    COUPON_ADD_TITLE_EXIST("Cannot add coupon since coupon title already exist"),

    COUPON_ADD_INVALID_END_DATE("Cannot add coupon since coupon's end date is expired"),
    COUPON_ADD_INVALID_COMPANY_ID("Cannot add coupon since coupon's company id does not match your company's id"),

    COUPON_ADD_INVALID_AMOUNT("Cannot add coupon since coupon's amount most be greater then 0"),
    COUPON_ADD_INVALID_ID("Cannot add coupon since coupon ID must be initialized to 0 or left uninitialized "),
    COUPON_UPDATE_CANNOT_UPDATE_COUPON_ID("Cannot update coupon ID"),

    COUPON_UPDATE__COMPANY_ID_INVALID("Cannot update coupon since coupon's company id not match your company's id"),

    COUPON_NOT_EXIST("There is no coupon with this ID"),
    PURCHASE_COUPON_CANNOT_PURCHASE_AGAIN("Cannot purchase the same coupon more then once"),
    PURCHASE_COUPON_EXPIRED("Cannot purchase an expired coupon"),
    PURCHASE_COUPON_SOLD_OUT("This coupon is sold out"),
    NO_COUPONS_BY_CATEGORY("No coupons found for the given category"),
    NO_COUPONS_BY_PRICE("No coupons found under that price"),
    NO_COUPONS("You have no coupons..."),
    INVALID_DETAILS("You most fill all required fields"),
    REGISTER_AS_ADMIN("Cannot register as admin"),
    REGISTER_NAME_EXIST("Oops, looks like your company name is already in use"),
    REGISTER_EMAIL_EXIST("Email address already in use"),
    LOGIN_WRONG_LOGIN_DETAILS("Wrong email or password"),
    NOT_AUTHORIZED("Access Denied: You are not authorized to access this page or perform this action."),
    SESSION_EXPIRED("your session expired, please log-in again");
    private final String message;

    ErrMsg(String message) {
        this.message = message;
    }
}
