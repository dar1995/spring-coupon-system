package com.dar.coupon.system.project2.clr;

import com.dar.coupon.system.project2.beans.*;
import com.dar.coupon.system.project2.service.AdminService;
import com.dar.coupon.system.project2.utils.Art;
import com.dar.coupon.system.project2.utils.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(2)
public class AdminTest implements CommandLineRunner {
    @Autowired
    private AdminService adminService;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(Art.ADMIN_SERVICE_TEST);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();
        Test.test("Admin Service - get all companies - success");
        System.out.println();
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("-------------------------------------------");
        System.out.println();
        Company companyToAdd = Company.builder()
                .id(5)
                .name("TEVA")
                .email("info@teva.com")
                .password("1234").build();
        Test.test("Admin Service - add company - invalid id");
        try {
            adminService.addCompany(companyToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - add company - name already exist");
        companyToAdd.setId(0);
        companyToAdd.setName("ZARA");
        try {
            adminService.addCompany(companyToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - add company - email already exist");
        companyToAdd.setName("TEVA");
        companyToAdd.setEmail("info@zara.com");
        try {
            adminService.addCompany(companyToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - add company - success");
        companyToAdd.setEmail("info@teva.com");
        adminService.addCompany(companyToAdd);
        System.out.println();
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("--------------------------------------------");

        System.out.println();
        Test.test("Admin Service - get single company- id not exist");
        try {
            adminService.getSingleCompany(200);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - get single company- success");
        System.out.println(adminService.getSingleCompany(7));
        System.out.println("--------------------------------------------");

        System.out.println();
        Company companyToUpdate = adminService.getSingleCompany(11);
        final int COMPANY_ID = companyToUpdate.getId();
        final String NAME = companyToUpdate.getName();
        Test.test("Admin Service - update company - company id not exist");
        companyToUpdate.setPassword("0987");
        try {
            adminService.updateCompany(23, companyToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - update company - cannot update company id");
        companyToUpdate.setId(20);
        try {
            adminService.updateCompany(COMPANY_ID, companyToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - update company - cannot update company name");
        companyToUpdate.setId(COMPANY_ID);
        companyToUpdate.setName("Google");
        try {
            adminService.updateCompany(COMPANY_ID, companyToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - update company - success");
        companyToUpdate.setName(NAME);
        companyToUpdate.setEmail("teva@teva.org");
        companyToUpdate.setPassword("4321");
        adminService.updateCompany(COMPANY_ID, companyToUpdate);
        System.out.println();
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("-----------------------------------------");
        System.out.println();
        Test.test("Admin Service - delete  company and company coupons - company not exist ");
        try {
            adminService.deleteCompany(15);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - delete  company and company coupons - success ");
        adminService.deleteCompany(2);
        System.out.println();
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("-------------------------------------------");
        System.out.println("");
        Test.test("Admin Service - get all customer - success");
        System.out.println();
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("-----------------------------------------");

        System.out.println();
        Test.test("Admin Service - get single customer - id not exist");
        try {
            adminService.getSingleCustomer(80);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - get single customer - success");
        System.out.println(adminService.getSingleCustomer(1));
        System.out.println("---------------------------------------");
        System.out.println();
        Customer customerToAdd = Customer.builder()
                .id(7)
                .firstName("Omer")
                .lastName("Rothman")
                .email("Omer@gmail.com")
                .password("1234")
                .build();
        Test.test("Admin Service - add customer- invalid id");
        try {
            adminService.addCustomer(customerToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Service - add customer- email exist");
        customerToAdd.setId(0);
        customerToAdd.setEmail("Yoav@gmail.com");
        try {
            adminService.addCustomer(customerToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - add customer- success");
        customerToAdd.setEmail("Omer@gmail.com");
        adminService.addCustomer(customerToAdd);
        System.out.println();
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("---------------------------------------------");
        System.out.println();
        Customer customerToUpdate = adminService.getSingleCustomer(11);
        final int CUSTOMER_ID = customerToUpdate.getId();
        Test.test("Admin Service - update customer - customer id not exist");
        try {
            adminService.updateCustomer(900, customerToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - update customer - cannot update customer id");
        customerToUpdate.setId(7);
        customerToUpdate.setFirstName("Noa");
        try {
            adminService.updateCustomer(1, customerToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - update customer - success");
        customerToUpdate.setId(CUSTOMER_ID);
        customerToUpdate.setFirstName("Raz");
        customerToUpdate.setLastName("Rabin");
        customerToUpdate.setEmail("Raz@gmail.com");
        adminService.updateCustomer(CUSTOMER_ID, customerToUpdate);
        System.out.println();
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("---------------------------------------");
        System.out.println();
        Test.test("Admin Service - delete customer and customer purchased coupons - customer id not exist");
        try {
            adminService.deleteCustomer(24);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Service - delete customer and customer purchased coupons - success");
        adminService.deleteCustomer(5);
        System.out.println();
        adminService.getAllCustomers().forEach(System.out::println);

    }
}
