
package com.dar.coupon.system.project2.clr;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.repositories.CompanyRepository;
import com.dar.coupon.system.project2.repositories.CouponRepository;
import com.dar.coupon.system.project2.repositories.CustomerRepository;
import com.dar.coupon.system.project2.utils.Art;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
public class Init implements CommandLineRunner {


    @Autowired
   private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {
        Company company1 = Company.builder()
                .name("IVORY")
                .email("info@ivory.com")
                .password("1234")
                .build();
        Company company2 = Company.builder()
                .name("ZARA")
                .email("info@zara.com")
                .password("1234")
                .build();

        Company company3 = Company.builder()
                .name("ISROTEL")
                .email("info.isrotel.com")
                .password("1234")
                .build();

        Company company4 = Company.builder()
                .name("IHERB")
                .email("info@iherb.com")
                .password("1234")
                .build();

        Company company5 = Company.builder()
                .name("KFC")
                .email("info@kfc.com")
                .password("1234")
                .build();

        Company company6 = Company.builder()
                .name("ADIDAS")
                .email("info@adidas.com")
                .password("1234")
                .build();

        Company company7 = Company.builder()
                .name("Apple")
                .email("info@apple.com")
                .password("1234")
                .build();

        Company company8 = Company.builder()
                .name("ELAL")
                .email("info@elal.com")
                .password("1234")
                .build();

        Company company9 = Company.builder()
                .name("SABICH OVAD")
                .email("info@ovad.com")
                .password("1234")
                .build();

        Company company10 = Company.builder()
                .name("BE STORE")
                .email("info@pharm.com")
                .password("1234")
                .build();

        Coupon coupon1 = Coupon.builder()
                .company(company2)
                .category(Category.CLOTHING)
                .title("SUMMER SALE!")
                .description("Buy two jeans with 50% off discount")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
                .amount(150)
                .price(130)
                .image("https://as1.ftcdn.net/v2/jpg/02/29/74/20/1000_F_229742070_UOUlBZtOdQBzYDaPhDyH9hbpftbK9inT.jpg")
                .build();

        Coupon coupon2 = Coupon.builder()
                .company(company4)
                .category(Category.HEALTH)
                .title("B12 FEST!")
                .description("Get a free Vitamin B12 bottle for every purchase above 150 NIS ")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(4)))
                .amount(300)
                .price(30)
                .image("https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/sol/sol03220/y/37.jpg")
                .build();

        Coupon coupon3 = Coupon.builder()
                .company(company5)
                .category(Category.FOOD)
                .title("1+1 Buckets")
                .description("Buy a chicken bucket and get the second one for free!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(0)
                .price(55)
                .image("https://as1.ftcdn.net/v2/jpg/03/68/44/40/1000_F_368444058_TiFuWznROsYSNO8v5s1MZOSirsFJPhni.jpg")
                .build();

        Coupon coupon4 = Coupon.builder()
                .company(company8)
                .category(Category.VACATION)
                .title("Dubai deal")
                .description("All included 5 days vacation to dubai for 500$")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusWeeks(1)))
                .amount(250)
                .price(1500)
                .image("https://t4.ftcdn.net/jpg/01/70/66/75/240_F_170667573_7EnaDhe9xo1elwC9fAVjy02BPiJrZ9PW.jpg")
                .build();

        Coupon coupon5 = Coupon.builder()
                .company(company1)
                .category(Category.ELECTRONICS)
                .title("SONY HEADPHONES")
                .description("30% off Sony WH-1000xm5 ")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(50)
                .price(300)
                .image("https://t4.ftcdn.net/jpg/04/08/25/53/240_F_408255305_xsvVFQXZXqDuOHbbIPlWkwJP30F9O8ZC.jpg")
                .build();
        Coupon coupon6 = Coupon.builder()
                .company(company6)
                .category(Category.CLOTHING)
                .title("EXCLUSIVE SHOES!")
                .description("Pair of Limited edition shoes, available only for coupons owners!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(1)))
                .amount(10)
                .price(1000)
                .image("https://t4.ftcdn.net/jpg/05/83/89/05/240_F_583890527_IOqfFZCl3yjpuQT96GLGB0YlFOMbjKE5.jpg")
                .build();
        Coupon coupon7 = Coupon.builder()
                .company(company10)
                .category(Category.HEALTH)
                .title("HEAD & SHOULDERS")
                .description("1 + 1 on all HEAD $ SHOULDERS shampoos")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(3)))
                .amount(100)
                .price(20)
                .image("https://m.media-amazon.com/images/I/81iHe10YLXL.jpg")
                .build();

        Customer customer1 = Customer.builder()
                .firstName("Gal")
                .lastName("Stauber")
                .email("Gal@gmail.com")
                .password("1234")
                .coupons(List.of(coupon4, coupon3))
                .build();

        Customer customer2 = Customer.builder()
                .firstName("Yoav")
                .lastName("Malihi")
                .email("Yoav@gmail.com")
                .password("1234")
                .coupons(List.of(coupon2))
                .build();

        Customer customer3 = Customer.builder()
                .firstName("Yuval")
                .lastName("Assif")
                .email("Yuval@gmail.com")
                .password("1234")
                .coupons(List.of(coupon6))
                .build();

        Customer customer4 = Customer.builder()
                .firstName("Tom")
                .lastName("Ofir")
                .email("Tom@gmail.com")
                .password("1234")
                .coupons(List.of(coupon4, coupon5))
                .build();

        Customer customer5 = Customer.builder()
                .firstName("Asaf")
                .lastName("Peer")
                .email("Asaf@gmail.com")
                .password("1234")
                .coupons(List.of(coupon1))
                .build();

        Customer customer6 = Customer.builder()
                .firstName("Nir")
                .lastName("Lavy")
                .email("Nir@gmail.com")
                .password("1234")
                .build();

        Customer customer7 = Customer.builder()
                .firstName("Roee")
                .lastName("Geva")
                .email("Roee@gmail.com")
                .password("1234")
                .build();

        Customer customer8 = Customer.builder()
                .firstName("Sivan")
                .lastName("Reznikov")
                .email("Sivan@gmail.com")
                .password("1234")
                .build();

        Customer customer9 = Customer.builder()
                .firstName("Yael")
                .lastName("Ron")
                .email("Yael@gmail.com")
                .password("1234")
                .build();

        Customer customer10 = Customer.builder()
                .firstName("Shiri")
                .lastName("Gutman")
                .email("Shiri@gmail.com")
                .password("1234")
                .coupons(List.of(coupon2))
                .build();

        company2.setCoupons(List.of(coupon1));
        company4.setCoupons(List.of(coupon2));
        company5.setCoupons(List.of(coupon3));
        company8.setCoupons(List.of(coupon4));
        company1.setCoupons(List.of(coupon5));
        company6.setCoupons(List.of(coupon6));
        company10.setCoupons(List.of(coupon7));
        List<Company> companies = List.of(company1, company2, company3, company4, company5, company6,
                company7, company8, company9, company10);
        List<Customer> customers = List.of(customer1, customer2, customer3, customer4, customer5,
                customer6, customer7, customer8, customer9, customer10);
        companyRepository.saveAll(companies);
        customerRepository.saveAll(customers);
        System.out.println(Art.COMPANIES);
        companyRepository.findAll().forEach(System.out::println);
        System.out.println(Art.COUPONS);
        couponRepository.findAll().forEach(System.out::println);
        System.out.println(Art.CUSTOMERS);
        customerRepository.findAll().forEach(System.out::println);


    }

}
