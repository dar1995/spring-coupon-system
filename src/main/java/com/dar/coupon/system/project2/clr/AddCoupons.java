package com.dar.coupon.system.project2.clr;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.repositories.CompanyRepository;
import com.dar.coupon.system.project2.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(5)
public class AddCoupons  implements CommandLineRunner {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;
    private Company company;
    @Override
    public void run(String... args) throws Exception {

        company = companyRepository.findById(1).get();
        Coupon coupon1 = Coupon.builder()
                .company(company)
                .category(Category.ELECTRONICS)
                .title("Electronics Summer Sale")
                .description("Get up to 30% off on computers, smartphones, and other electronics!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(3)))
                .amount(500)
                .price(250)
                .image("https://images.pexels.com/photos/2528118/pexels-photo-2528118.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        Coupon coupon2 = Coupon.builder()
                .company(company)
                .category(Category.ELECTRONICS)
                .title("Cellphone Upgrade Offer")
                .description("Upgrade your smartphone with the latest models and save 20%!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(3)))
                .amount(100)
                .price(150)
                .image("https://images.pexels.com/photos/887751/pexels-photo-887751.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        companyService.addCoupon(1, coupon1);
        companyService.addCoupon(1, coupon2);

        company = companyRepository.findById(3).get();
        Coupon coupon3 = Coupon.builder()
                .company(company)
                .category(Category.VACATION)
                .title("Beach Vacation Package")
                .description("Enjoy a relaxing beach vacation with Isrotel and get 20% off on your stay!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(2)))
                .amount(50)
                .price(500)
                .image("https://images.pexels.com/photos/1450353/pexels-photo-1450353.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        Coupon coupon4 = Coupon.builder()
                .company(company)
                .category(Category.VACATION)
                .title("Luxurious Spa Retreat")
                .description("Indulge in a luxurious spa retreat at Isrotel and save 15% on your booking!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(3)))
                .amount(30)
                .price(350)
                .image("https://images.pexels.com/photos/269110/pexels-photo-269110.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        companyService.addCoupon(3, coupon3);
        companyService.addCoupon(3, coupon4);

        company = companyRepository.findById(4).get();

        Coupon coupon5 = Coupon.builder()
                .company(company)
                .category(Category.HEALTH)
                .title("Health Supplements Sale")
                .description("Get 20% off on all health supplements at iHerb!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(1)))
                .amount(100)
                .price(40)
                .image("https://images.pexels.com/photos/8844557/pexels-photo-8844557.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        Coupon coupon6 = Coupon.builder()
                .company(company)
                .category(Category.HEALTH)
                .title("Beauty Products Discount")
                .description("Save 15% on all beauty products at iHerb!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(2)))
                .amount(50)
                .price(80)
                .image("https://images.pexels.com/photos/5632328/pexels-photo-5632328.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        companyService.addCoupon(4, coupon5);
        companyService.addCoupon(4, coupon6);

        company = companyRepository.findById(5).get();

        Coupon coupon7 = Coupon.builder()
                .company(company)
                .category(Category.FOOD)
                .title("Tuesday Special - Buy One Get One Free")
                .description("Buy any combo meal on Tuesday and get another combo meal of equal or lesser value for free!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(1)))
                .amount(100)
                .price(35)
                .image("https://images.pexels.com/photos/6697264/pexels-photo-6697264.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        Coupon coupon8 = Coupon.builder()
                .company(company)
                .category(Category.FOOD)
                .title("Weekend Wings Combo")
                .description("Enjoy our delicious wings combo this weekend: 12 pieces of hot wings, a large coleslaw, and a regular soda for only $12!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(3)))
                .amount(150)
                .price(25)
                .image("https://images.pexels.com/photos/16892379/pexels-photo-16892379/free-photo-of-crispy-chicken-wings.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        companyService.addCoupon(5, coupon7);
        companyService.addCoupon(5, coupon8);

        company = companyRepository.findById(6).get();

        Coupon coupon9 = Coupon.builder()
                .company(company)
                .category(Category.CLOTHING)
                .title("Athletic Gear Sale")
                .description("Get up to 50% off on all athletic gear, including shoes, clothing, and accessories. Don't miss out on this limited-time offer!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(200)
                .price(100)
                .image("https://images.pexels.com/photos/2463155/pexels-photo-2463155.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        Coupon coupon10 = Coupon.builder()
                .company(company)
                .category(Category.CLOTHING)
                .title("Back-to-School Special")
                .description("Get 30% off on all backpacks and school essentials. Start the new school year in style with Adidas!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(3)))
                .amount(150)
                .price(150)
                .image("https://images.pexels.com/photos/1102874/pexels-photo-1102874.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        companyService.addCoupon(6, coupon9);
        companyService.addCoupon(6, coupon10);

        company = companyRepository.findById(7).get();

        Coupon coupon11 = Coupon.builder()
                .company(company)
                .category(Category.ELECTRONICS)
                .title("iPhone Sale")
                .description("Get $100 off on the latest iPhone models. Upgrade your smartphone today and enjoy advanced features and performance.")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(50)
                .price(200)
                .image("https://images.pexels.com/photos/13570143/pexels-photo-13570143.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        Coupon coupon12 = Coupon.builder()
                .company(company)
                .category(Category.ELECTRONICS)
                .title("iPad Pro Promotion")
                .description("Purchase an iPad Pro and get a free Apple Pencil. Experience the ultimate productivity and creativity with this bundle offer.")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(3)))
                .amount(30)
                .price(100)
                .image("https://images.pexels.com/photos/1334597/pexels-photo-1334597.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        companyService.addCoupon(7, coupon11);
        companyService.addCoupon(7, coupon12);

        company = companyRepository.findById(8).get();

        Coupon coupon13 = Coupon.builder()
                .company(company)
                .category(Category.VACATION)
                .title("Summer Vacation Offer")
                .description("Book your flight for a summer vacation destination and get 20% off on your ticket. Enjoy a memorable trip with El Al's top-notch services.")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(1)))
                .amount(100)
                .price(350)
                .image("https://images.pexels.com/photos/870170/pexels-photo-870170.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        Coupon coupon14 = Coupon.builder()
                .company(company)
                .category(Category.VACATION)
                .title("Business Class Upgrade")
                .description("Upgrade your Economy Class ticket to Business Class and experience luxury and comfort during your flight with El Al.")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(2)))
                .amount(50)
                .price(150)
                .image("https://images.pexels.com/photos/5778455/pexels-photo-5778455.jpeg?auto=compress&cs=tinysrgb&w=1600")
                .build();

        companyService.addCoupon(8, coupon13);
        companyService.addCoupon(8, coupon14);
    }



}
