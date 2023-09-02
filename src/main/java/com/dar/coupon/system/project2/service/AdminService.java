package com.dar.coupon.system.project2.service;

import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;

import java.util.List;

public interface AdminService {
    Company addCompany(Company company) throws CouponSystemExceptions;

    Company updateCompany(int companyID, Company company) throws CouponSystemExceptions;

    void deleteCompany(int companyID) throws CouponSystemExceptions;

    List<Company> getAllCompanies();

    Company getSingleCompany(int companyID) throws CouponSystemExceptions;

    Customer addCustomer(Customer customer) throws CouponSystemExceptions;

    Customer updateCustomer(int customerID, Customer customer) throws CouponSystemExceptions;

    void deleteCustomer(int customerID) throws CouponSystemExceptions;

    List<Customer> getAllCustomers();

    Customer getSingleCustomer(int customerID) throws CouponSystemExceptions;
}
