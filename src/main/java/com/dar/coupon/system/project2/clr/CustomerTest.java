package com.dar.coupon.system.project2.clr;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.repositories.CouponRepository;
import com.dar.coupon.system.project2.service.CustomerService;
import com.dar.coupon.system.project2.utils.Art;
import com.dar.coupon.system.project2.utils.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class CustomerTest implements CommandLineRunner {
    @Autowired
    CustomerService customerService;
    @Autowired
    CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(Art.CUSTOMER_SERVICE_TEST);
        System.out.println("Testing as customer ID - 10");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        Test.test("Customer Service - get customer coupons - success ");
        customerService.getCustomerCoupons(10).forEach(System.out::println);
        System.out.println("-------------------------------------------------------");
        System.out.println();
        Test.test("Customer Service - get single coupon - The coupon either does not exist or has not been purchased by the customer.");
        try {
            customerService.getSingle(10, 7);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Customer Service - get single coupon - success");
        System.out.println(customerService.getSingle(10, 3));
        System.out.println("-------------------------------------------------------");
        System.out.println();
        Test.test("Customer Service - purchase coupon - coupon already purchased");
        try {
            customerService.purchaseCoupon(10, 3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Customer Service - purchase coupon - coupon sold out");
        try {
            customerService.purchaseCoupon(10,4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Customer Service - purchase coupon - coupon expired");
        try {
            customerService.purchaseCoupon(10,6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Customer Service - purchase coupon - success");
        int amount = 0;
        amount = couponRepository.findById(7).get().getAmount();
        System.out.println("Coupon 7 amount before purchasing: " + amount);
        customerService.purchaseCoupon(10,7);
        customerService.purchaseCoupon(10,5);
        customerService.getCustomerCoupons(10).forEach(System.out::println);
        amount = customerService.getSingle(10,7).getAmount();
        System.out.println("Coupon 7 amount after purchasing: " + amount);
        System.out.println("---------------------------------------------------------");
        System.out.println();
        Test.test("Customer Service - get customer coupons by category - no coupons ");
        try {
            System.out.println(customerService.getCustomerCoupons(10, Category.FOOD));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Test.test("Customer Service - get customer coupons by category - success ");
        customerService.getCustomerCoupons(10, Category.HEALTH).forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
        System.out.println();
        Test.test("Customer Service - get customer coupons by price - no coupons ");
        try {
            System.out.println(customerService.getCustomerCoupons(10,10));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Test.test("Customer Service - get customer coupons by price - success ");
        customerService.getCustomerCoupons(10,100).forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
        System.out.println();
        Test.test("Customer Service - get customer details - success ");
        System.out.println(customerService.getCustomerDetails(10));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");





    }
}
