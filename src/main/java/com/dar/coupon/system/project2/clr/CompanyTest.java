package com.dar.coupon.system.project2.clr;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.repositories.CompanyRepository;
import com.dar.coupon.system.project2.service.CompanyService;
import com.dar.coupon.system.project2.utils.Art;
import com.dar.coupon.system.project2.utils.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(3)
public class CompanyTest implements CommandLineRunner {
    @Autowired
  private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    private Company companyForCheck;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(Art.COMPANY_SERVICE_TEST);

        System.out.println("Testing as company ID - 1");
        System.out.println("------------------------------------------");
        Test.test("Company Service - get all coupon - success ");
        companyService.getCompanyCoupons(1).forEach(System.out::println);
        System.out.println("------------------------------------------");
        System.out.println();
        Test.test("Company Service - get single coupon - coupon id not exist or not belong to the company");
        try {
            companyService.getSingle(1, 3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Service - get single coupon - success");
        System.out.println(companyService.getSingle(1, 1));
        System.out.println("------------------------------------");
        Coupon couponToAdd = Coupon.builder()
                .id(0)
                .category(Category.HEALTH)
                .title("Program responsibly")
                .description("Get a free orthopedic chair pad")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(100)
                .price(75)
                .image("https://media2.giphy.com/media/Wsju5zAb5kcOfxJV9i/200w.webp?cid=ecf05e47hfcje9z6dtfzwionh558toq9jytrke37ypb03601&ep=v1_gifs_search&rid=200w.webp&ct=g")
                .build();
        Test.test("Company Service - add coupon - name already exist");
        couponToAdd.setTitle("SONY HEADPHONES");

        try {
            companyService.addCoupon(1, couponToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        Test.test("Company Service - add coupon - coupon end date expired ");
        Company myCompany = companyRepository.findById(1).get();
        couponToAdd.setTitle("Program responsibly");
        couponToAdd.setEndDate(Date.valueOf(LocalDate.now().minusWeeks(2)));
        try {
            companyService.addCoupon(1, couponToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Service - add coupon - amount is 0 ");
        couponToAdd.setEndDate(Date.valueOf(LocalDate.now().plusWeeks(2)));
        couponToAdd.setAmount(0);
        try {
            companyService.addCoupon(1, couponToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Service - add coupon - invalid coupon id ");
        couponToAdd.setAmount(100);
        couponToAdd.setId(8);
        try {
            companyService.addCoupon(1, couponToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Service - add coupon - success name exist in other company");
        couponToAdd.setId(0);
        couponToAdd.setTitle("Dubai deal");
        companyService.addCoupon(1, couponToAdd);
        companyService.getCompanyCoupons(1).forEach(System.out::println);
        System.out.println("------------------------------------");
        System.out.println();
        Coupon couponToUpdate = companyService.getSingle(1, 8);
        final int COUPON_ID = couponToUpdate.getId();
        Test.test("Company Service - update coupon - coupon id not exist or not belong to the company");
        couponToUpdate.setTitle("Program responsibly");
        try {
            companyService.updateCoupon(1, 2, couponToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Service - update coupon - cannot update coupon id  ");
        couponToUpdate.setId(3);
        try {
            companyService.updateCoupon(1, COUPON_ID, couponToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Service - update coupon - cannot update coupon company id ");
        companyForCheck = companyRepository.findById(3).get();
        couponToUpdate.setId(COUPON_ID);
        couponToUpdate.setCompany(companyForCheck);
        try {
            companyService.updateCoupon(1, COUPON_ID, couponToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Service - update coupon - success ");
        couponToUpdate.setCompany(myCompany);
        companyService.updateCoupon(1, COUPON_ID, couponToUpdate);
        companyService.getCompanyCoupons(1).forEach(System.out::println);

        System.out.println("-----------------------------------------------------------");
        Test.test("Company Service - delete coupon - coupon id not exist or not belong to the company ");
        try {
            companyService.deleteCoupon(1, 3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Service - delete coupon - success ");

        companyService.deleteCoupon(1, COUPON_ID);
        companyService.getCompanyCoupons(1).forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        System.out.println();
        Test.test("Company Service - get all coupon by category - no coupons found ");
        try {
            System.out.println(companyService.getCompanyCoupons(1, Category.FOOD));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Service - get all coupon by category- success ");
        companyService.getCompanyCoupons(1, Category.ELECTRONICS).forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        System.out.println();
        Test.test("Company Service - get all coupon by max price - no coupons found ");
        try {
            System.out.println(companyService.getCompanyCoupons(1, 50));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Service - get all coupon by max price - success ");
        companyService.getCompanyCoupons(1, 350).forEach(System.out::println);
        System.out.println("----------------------------------------------------");
        System.out.println();
        Test.test("Company Service - get company details - success ");
        System.out.println(companyService.getCompanyDetails(1));

    }
}
