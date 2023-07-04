package com.dar.coupon.system.project2.service;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    @Override
    public void purchaseCoupon(int customerId, int couponID) throws CouponSystemExceptions {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.CUSTOMER_NOT_EXIST));
        Coupon couponUpdate = couponRepository.findById(couponID).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.COUPON_NOT_EXIST));
        if (couponRepository.isPurchased(customerId, couponID)) {
            throw new CouponSystemExceptions(ErrMsg.PURCHASE_COUPON_CANNOT_PURCHASE_AGAIN);
        }
        if (couponUpdate.getAmount() <= 0) {
            throw new CouponSystemExceptions(ErrMsg.PURCHASE_COUPON_SOLD_OUT);
        }
        if (couponUpdate.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemExceptions(ErrMsg.PURCHASE_COUPON_EXPIRED);
        }
        int amount = couponUpdate.getAmount();
        couponUpdate.setAmount(amount - 1);
        couponRepository.saveAndFlush(couponUpdate);
        customer.purchaseCoupon(couponUpdate);
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemExceptions {
        List<Coupon> coupons = couponRepository.customerCoupons(customerId);
        if (coupons.isEmpty()) {
            throw new CouponSystemExceptions(ErrMsg.NO_COUPONS);
        }
        return coupons;
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, Category category) throws CouponSystemExceptions {
        List<Coupon> coupons = couponRepository.customerCoupons(customerId, category.name());
        if (coupons.isEmpty()) {
            throw new CouponSystemExceptions(ErrMsg.NO_COUPONS_BY_CATEGORY);
        }
        return coupons;
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws CouponSystemExceptions {
        List<Coupon> coupons = couponRepository.customerCoupons(customerId, maxPrice);
        if (coupons.isEmpty()) {
            throw new CouponSystemExceptions(ErrMsg.NO_COUPONS_BY_PRICE);
        }
        return coupons;
    }

    @Override
    public Coupon getSingle(int customerId, int couponId) throws CouponSystemExceptions {
        Coupon coupon = couponRepository.customerSingleCoupons(customerId, couponId);
        if (coupon == null) {
            throw new CouponSystemExceptions(ErrMsg.COUPON_NOT_EXIST);
        }
        return coupon;
    }

    @Override
    public Customer getCustomerDetails(int customerId) throws CouponSystemExceptions {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.CUSTOMER_NOT_EXIST));
    }

    @Override
    public void register(Customer customer) throws CouponSystemExceptions {
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String email = customer.getEmail();
        String password = customer.getPassword();
        if (customer.getId() != 0) {
            throw new CouponSystemExceptions(ErrMsg.CUSTOMER_ADD_INVALID_ID);
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemExceptions(ErrMsg.CUSTOMER_ADD_EMAIL_EXIST);
        }
        if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank() ||
                email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new CouponSystemExceptions(ErrMsg.INVALID_DETAILS);
        }
        customerRepository.save(customer);
    }

    @Override
    public int login(String email, String password) {

        return customerRepository.findIdByEmailAndPassword(email, password);
    }
}
