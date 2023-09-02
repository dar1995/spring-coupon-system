package com.dar.coupon.system.project2.controllers;

import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.security.TokenService;
import com.dar.coupon.system.project2.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/admin")
@CrossOrigin
public class AdminController {
    @Autowired
   private AdminService adminService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("companies")
    public Company[] getAllCompanies(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return adminService.getAllCompanies().toArray(new Company[0]);
    }

    @GetMapping("companies/{companyId}")
    public Company getSingleCompanyById(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return adminService.getSingleCompany(companyId);
    }

    @GetMapping("customers")
    public Customer[] getAllCustomers(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return adminService.getAllCustomers().toArray(new Customer[0]);
    }


    @GetMapping("customers/{customerId}")
    public Customer getSingleCustomerById(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return adminService.getSingleCustomer(customerId);
    }

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestHeader(value = "Authorization") UUID token, @RequestBody Company company) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return adminService.addCompany(company);
    }

    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestHeader(value = "Authorization") UUID token, @RequestBody Customer customer) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return adminService.addCustomer(customer);
    }

    @PutMapping("companies/{companyId}")
    public Company updateCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @RequestBody Company company) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return adminService.updateCompany(companyId, company);
    }

    @PutMapping("customers/{customerId}")
    public Customer updateCustomer(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId, @RequestBody Customer customer) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return adminService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        adminService.deleteCompany(companyId);
    }

    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        adminService.deleteCustomer(customerId);
    }


}
