package com.dar.coupon.system.project2.controllers;

import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.models.CompanyToList;
import com.dar.coupon.system.project2.models.CouponToObject;
import com.dar.coupon.system.project2.models.CustomerToObject;
import com.dar.coupon.system.project2.security.TokenService;
import com.dar.coupon.system.project2.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    TokenService tokenService;
    @GetMapping("companies")
    public CompanyToList getAllCompanies(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return new CompanyToList(adminService.getAllCompanies());
    }
    @GetMapping("companies/{companyId}")
    public Company getSingleCompanyById(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return adminService.getSingleCompany(companyId);
    }
    @GetMapping("customers")
    public CustomerToObject getAllCustomers(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return new CustomerToObject(adminService.getAllCustomers());
    }


    @GetMapping("customers/{customerId}")
    public Customer getSingleCustomerById(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return adminService.getSingleCustomer(customerId);
    }

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestHeader(value = "Authorization") UUID token,@RequestBody Company company) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        adminService.addCompany(company);
    }
    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestHeader(value = "Authorization") UUID token, @RequestBody Customer customer) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        adminService.addCustomer(customer);
    }

    @PutMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @RequestBody Company company) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        adminService.updateCompany(companyId, company);
    }

    @PutMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId, @RequestBody Customer customer) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        adminService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        adminService.deleteCompany(companyId);
    }
    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        adminService.deleteCustomer(customerId);
    }



}
