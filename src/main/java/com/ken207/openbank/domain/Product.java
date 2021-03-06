package com.ken207.openbank.domain;

import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Product")
@AttributeOverride(name = "id",column = @Column(name = "product_id"))
public class Product extends BaseEntity<Product> {

    private String name;
    private String productCode;

    @Enumerated(EnumType.STRING)
    private SubjectCode subjectCode; //과목코드

    private String startDate;
    private String endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rate_id")
    private Rate basicRate;

    public static Product createProduct(ProductDto.Create productCreateDto) {

        if ( productCreateDto.getEndDate() == null ) {
            productCreateDto.setEndDate("99991231");
        }

        Rate basicRate = Rate.builder()
                .name("기본이율")
                .rate(productCreateDto.getBasicRate())
                .startDate(productCreateDto.getStartDate())
                .endDate(productCreateDto.getEndDate())
                .build();

        Product product = Product.builder()
                .name(productCreateDto.getName())
                .productCode(productCreateDto.getProductCode())
                .subjectCode(productCreateDto.getSubjectCode())
                .startDate(productCreateDto.getStartDate())
                .endDate(productCreateDto.getEndDate())
                .basicRate(basicRate)
                .build();

//        product.addRate(basicRate);

        return product;
    }

//    private void addRate(RateEntity rateEntity) {
//        this.allBasicRate.add(rateEntity);
//    }
//
//    public RateEntity addRate(String changeDate, double changeRate) {
//
//        RateEntity rateEntity = getLastBasicRate();
//        String endDate = rateEntity.getEndDate();
//        rateEntity.setEndDate(OBDateUtils.addDays(changeDate, -1));
//
//        RateEntity newRateEntity = RateEntity.builder()
//                .rate(changeRate)
//                .startDate(changeDate)
//                .endDate(endDate)
//                .build();
//        this.allBasicRate.add(newRateEntity);
//
//        return newRateEntity;
//
//    }
//
//    public RateEntity getLastBasicRate() {
//        return this.allBasicRate.get(this.allBasicRate.size() - 1);
//    }
}
