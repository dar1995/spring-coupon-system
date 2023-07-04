package com.dar.coupon.system.project2.login;

import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.security.TokenService;
import com.dar.coupon.system.project2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class LoginManagerServiceImpl implements LoginManagerService{
    @Autowired
    CompanyService companyService;
    @Autowired
    CustomerService customerService;
    @Autowired
    AdminService adminService;

    ClientService clientService;
    @Autowired
    TokenService tokenService;
    @Override
    public void register(User user,ClientType clientType) throws CouponSystemExceptions {
        switch (clientType){
            case ADMIN :
                throw new CouponSystemExceptions(ErrMsg.REGISTER_AS_ADMIN);
            case COMPANY:
                Company company = Company.builder()
                        .name(user.getCompanyName())
                        .email(user.getEmail())
                        .password(user.getPassword()).build();
                companyService.register(company);
                break;
            case CUSTOMER:
                Customer customer = Customer.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword()).build();
                customerService.register(customer);
                break;
        }

    }

    @Override
    public Map<UUID, Integer> login(String email, String password, ClientType clientType) throws CouponSystemExceptions {
        int id;
        UUID token;
        Map<UUID, Integer> map = new HashMap<>();
        switch (clientType){
            case ADMIN :
                clientService =(ClientService) adminService;
                break;
            case CUSTOMER:
                clientService = (ClientService) customerService;
                break;
            case COMPANY:
                clientService = (ClientService) companyService;
                break;
        }
        id= clientService.login(email, password);
        if (id == 0){
            throw new CouponSystemExceptions(ErrMsg.LOGIN_WRONG_LOGIN_DETAILS);
        }
        User user = User.builder()
                .clientType(clientType)
                .id(id)
                .build();
        token = tokenService.addToken(user);
        map.put(token, id);
        return map;
    }
}
