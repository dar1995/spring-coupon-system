package com.dar.coupon.system.project2.login;

import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.dto.CompanyRegisterReq;
import com.dar.coupon.system.project2.dto.CustomerRegisterReq;
import com.dar.coupon.system.project2.dto.LoginResDto;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.models.NameAndId;
import com.dar.coupon.system.project2.security.TokenService;
import com.dar.coupon.system.project2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class LoginManagerServiceImpl implements LoginManagerService {
    @Autowired
   private CompanyService companyService;
    @Autowired
   private CustomerService customerService;
    @Autowired
   private AdminService adminService;

   private ClientService clientService;
    @Autowired
    private TokenService tokenService;

    @Override
    public void CompanyRegister(CompanyRegisterReq companyRegisterReq) throws CouponSystemExceptions {
        Company company = Company.builder()
                .name(companyRegisterReq.getName())
                .email(companyRegisterReq.getEmail())
                .password(companyRegisterReq.getPassword()).build();
        companyService.register(company);

    }

    @Override
    public void CustomerRegister(CustomerRegisterReq customerRegisterReq) throws CouponSystemExceptions {
        Customer customer = Customer.builder()
                .firstName(customerRegisterReq.getFirstName())
                .lastName(customerRegisterReq.getLastName())
                .email(customerRegisterReq.getEmail())
                .password(customerRegisterReq.getPassword()).build();
        customerService.register(customer);

    }

    @Override
    public LoginResDto login(String email, String password, ClientType clientType) throws CouponSystemExceptions {
        NameAndId nameAndId;
        int id;
        UUID token;
        switch (clientType) {
            case ADMIN:
                clientService = (ClientService) adminService;
                break;

            case CUSTOMER:
                clientService = (ClientService) customerService;
                break;
            case COMPANY :
                clientService = (ClientService) companyService;
                break;
        }
        nameAndId = clientService.login(email, password);

        if (nameAndId == null) {
            throw new CouponSystemExceptions(ErrMsg.LOGIN_WRONG_LOGIN_DETAILS);
        }
        User user = User.builder()
                .clientType(clientType)
                .id(nameAndId.getId())
                .build();
        token = tokenService.addToken(user);
        LoginResDto loginResDto = LoginResDto.builder()
                .name(nameAndId.getName())
                .token(token).build();
        return loginResDto;
    }
}
