package com.dar.coupon.system.project2.service;

import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    @Override
    public void addCompany(Company company) throws CouponSystemExceptions {
        int id = company.getId();
        if (id != 0) {
            throw new CouponSystemExceptions(ErrMsg.COMPANY_ADD_INVALID_ID);
        }
        String name = company.getName();
        if (companyRepository.existsByName(name)) {
            throw new CouponSystemExceptions(ErrMsg.COMPANY_ADD_NAME_EXIST);
        }
        String email = company.getEmail();
        if (companyRepository.existsByEmail(email)) {
            throw new CouponSystemExceptions(ErrMsg.COMPANY_ADD_EMAIL_EXIST);
        }
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyID, Company company) throws CouponSystemExceptions {
        Company companyToUpdate = companyRepository.findById(companyID).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.COMPANY_NOT_EXIST));
        if (companyID != company.getId()) {
            throw new CouponSystemExceptions(ErrMsg.COMPANY_UPDATE_CANNOT_UPDATE_ID);
        }

        if (!companyToUpdate.getName().equals(company.getName())) {
            throw new CouponSystemExceptions(ErrMsg.COMPANY_UPDATE_CANNOT_UPDATE_NAME);
        }
        company.setId(companyID);
        companyRepository.saveAndFlush(company);

    }

    @Override
    public void deleteCompany(int companyID) throws CouponSystemExceptions {
        if (!companyRepository.existsById(companyID)) {
            throw new CouponSystemExceptions(ErrMsg.COMPANY_NOT_EXIST);
        }
        couponRepository.deletePurchase(companyID);
        companyRepository.deleteById(companyID);

    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyID) throws CouponSystemExceptions {
        return companyRepository.findById(companyID).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.COMPANY_NOT_EXIST));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemExceptions {
        int id = customer.getId();
        if (id != 0) {
            throw new CouponSystemExceptions(ErrMsg.CUSTOMER_ADD_INVALID_ID);
        }
        String email = customer.getEmail();
        if (this.customerRepository.existsByEmail(email)) {
            throw new CouponSystemExceptions(ErrMsg.CUSTOMER_ADD_EMAIL_EXIST);
        }
        this.customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerID, Customer customer) throws CouponSystemExceptions {
        if (!this.customerRepository.existsById(customerID)) {
            throw new CouponSystemExceptions(ErrMsg.CUSTOMER_NOT_EXIST);
        }
        if (customerID != customer.getId()) {
            throw new CouponSystemExceptions(ErrMsg.CUSTOMER_UPDATE_CANNOT_UPDATE_ID);
        }
        customer.setId(customerID);
        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerID) throws CouponSystemExceptions {
        if (!customerRepository.existsById(customerID)) {
            throw new CouponSystemExceptions(ErrMsg.CUSTOMER_NOT_EXIST);
        }
        this.customerRepository.deleteById(customerID);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerID) throws CouponSystemExceptions {
        return customerRepository.findById(customerID).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.CUSTOMER_NOT_EXIST));
    }

    @Override
    public int login(String email, String password) {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            return -999;
        }
        return 0;
    }
}
