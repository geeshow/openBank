package com.ken207.openbank.service;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.ProductEntity;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.dto.ProductDto;
import com.ken207.openbank.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    @TestDescription("상품 신규 정상 테스트")
    public void createProduct() throws Exception {
        //given
        String name = "온라인 보토예금";
        String productCode = "130001";
        SubjectCode subjectCode = SubjectCode.REGULAR;
        double rate = 1.2;
        String startDate = OBDateUtils.getToday();
        String endDate = OBDateUtils.MAX_DATE;

        ProductDto.Create productRequestDto = ProductDto.Create.builder()
                .name(name)
                .productCode(productCode)
                .subjectCode(subjectCode)
                .basicRate(rate)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        //when
        Long productId = productService.createProduct(productRequestDto);
        ProductEntity newProduct = productRepository.findById(productId).get();

        //then
        assertEquals(name, newProduct.getName());
        assertEquals(productCode, newProduct.getProductCode());
        assertEquals(subjectCode, newProduct.getSubjectCode());
        assertEquals(rate, newProduct.getBasicRate().getRate(), 0);
        assertEquals(startDate, newProduct.getStartDate());
        assertEquals(endDate, newProduct.getEndDate());

    }
}