package com.dar.coupon.system.project2.service;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService{

    @Override
    public void addCoupon(int companyId, Coupon coupon) throws CouponSystemExceptions {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.COMPANY_NOT_EXIST));
        if (this.couponRepository.existsByTitleAndCompany(coupon.getTitle(), company)) {
            throw new CouponSystemExceptions(ErrMsg.COUPON_ADD_TITLE_EXIST);
        }

        if (coupon.getEndDate().compareTo(Date.valueOf(LocalDate.now())) < 0) {
            throw new CouponSystemExceptions(ErrMsg.COUPON_ADD_INVALID_END_DATE);
        }
        if (coupon.getAmount() <= 0) {
            throw new CouponSystemExceptions(ErrMsg.COUPON_ADD_INVALID_AMOUNT);
        }
        if (coupon.getId() != 0) {
            throw new CouponSystemExceptions(ErrMsg.COUPON_ADD_INVALID_ID);
        }
        coupon.setCompany(company);
        couponRepository.save(coupon);


    }

    @Override
    public void updateCoupon(int companyId, int couponID, Coupon coupon) throws CouponSystemExceptions {
        int id = coupon.getId();
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.COMPANY_NOT_EXIST));
        if (!couponRepository.existsByIdAndCompany(couponID, company)) {
            throw new CouponSystemExceptions(ErrMsg.COUPON_NOT_EXIST);
        }
        if (couponID != id) {
            throw new CouponSystemExceptions(ErrMsg.COUPON_UPDATE_CANNOT_UPDATE_COUPON_ID);
        }
        if (coupon.getCompany()!= null && coupon.getCompany().getId() != companyId) {
            throw new CouponSystemExceptions(ErrMsg.COUPON_UPDATE__COMPANY_ID_INVALID);
        }
        coupon.setId(couponID);
        coupon.setCompany(company);
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int companyId, int couponID) throws CouponSystemExceptions {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.COMPANY_NOT_EXIST));
        if (!couponRepository.existsByIdAndCompany(couponID, company)) {
            throw new CouponSystemExceptions(ErrMsg.COUPON_NOT_EXIST);
        }
        couponRepository.deletePurchase(companyId);
        couponRepository.deleteById(couponID);
    }

    @Override
    public Coupon getSingle(int companyId, int couponId) throws CouponSystemExceptions {
        Company company = companyRepository.findById(companyId).get();
        Coupon coupon = couponRepository.findByIdAndCompany(couponId, company);
        if (coupon == null){
            throw new CouponSystemExceptions(ErrMsg.COUPON_NOT_EXIST);
        }
        return coupon;
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemExceptions {
        Company company = companyRepository.findById(companyId).get();
        List<Coupon> coupons = couponRepository.findByCompany(company);
        if (coupons.isEmpty()){
            throw new CouponSystemExceptions(ErrMsg.NO_COUPONS);
        }
        return coupons;
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponSystemExceptions {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.COMPANY_NOT_EXIST));
        List<Coupon> coupons = couponRepository.findByCompanyAndCategory(company, category);
        if (coupons.isEmpty()){
            throw new CouponSystemExceptions(ErrMsg.NO_COUPONS_BY_CATEGORY);
        }
        return coupons;
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponSystemExceptions {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.COMPANY_NOT_EXIST));
        List<Coupon> coupons = couponRepository.findByCompanyAndPriceLessThan(company, maxPrice);
        if (coupons.isEmpty()){
            throw new CouponSystemExceptions(ErrMsg.NO_COUPONS_BY_PRICE);
        }
        return coupons;
    }

    @Override
    public Company getCompanyDetails(int companyId) throws CouponSystemExceptions {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemExceptions(ErrMsg.COMPANY_NOT_EXIST));
    }

    @Override
    public void register(Company company) throws CouponSystemExceptions {
        String name = company.getName();
        String email = company.getEmail();
        String password = company.getPassword();
        if (company.getId() != 0) {
            throw new CouponSystemExceptions(ErrMsg.COMPANY_ADD_INVALID_ID);
        }
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemExceptions(ErrMsg.COMPANY_ADD_EMAIL_EXIST);
        }
        if (companyRepository.existsByName(name)) {
            throw new CouponSystemExceptions(ErrMsg.COMPANY_ADD_NAME_EXIST);
        }
        if (name == null || name.isBlank() ||
                email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new CouponSystemExceptions(ErrMsg.INVALID_DETAILS);
        }
        companyRepository.save(company);
    }

    @Override
    public int login(String email, String password){

        return companyRepository.findIdByEmailAndPassword(email, password);
    }
}
