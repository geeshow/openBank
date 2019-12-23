package com.ken207.openbank.init;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.consts.ConstEmployee;
import com.ken207.openbank.controller.CustomerController;
import com.ken207.openbank.domain.BranchEntity;
import com.ken207.openbank.domain.EmployeeEntity;
import com.ken207.openbank.domain.ProductEntity;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.products.ProductRgEntity;
import com.ken207.openbank.consts.ConstProduct;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.domain.enums.EmployeeType;
import com.ken207.openbank.dto.ProductDto;
import com.ken207.openbank.repository.EmployeeRepository;
import com.ken207.openbank.repository.ProductRepository;
import com.ken207.openbank.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataInitializer implements ApplicationRunner {

    @Autowired
    CustomerController customerController;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //기본 처리자 생성
        BranchEntity branchEntity = new BranchEntity("인터넷뱅킹", "555-12-3456", "000", "1588-1234", BranchType.인터넷);
        EmployeeEntity employeeEntity = new EmployeeEntity(ConstEmployee.INTERNET, "인터넷사용자", EmployeeType.인터넷뱅킹, branchEntity);
        employeeRepository.save(employeeEntity);

        //기본 보통예금 상품 생성
        ProductDto.Create regularProductDto = ProductDto.Create.builder()
                .name("보통예금")
                .productCode("130001")
                .subjectCode(SubjectCode.REGULAR)
                .basicRate(1.2)
                .startDate("20190101")
                .endDate(OBDateUtils.MAX_DATE)
                .build();
        productService.createProduct(regularProductDto);

        //기본 정기예금 상품 생성
        ProductDto.Create fixedProductDto = ProductDto.Create.builder()
                .name("정기예금")
                .productCode("240001")
                .subjectCode(SubjectCode.REGULAR)
                .basicRate(2.5)
                .startDate("20190101")
                .endDate(OBDateUtils.MAX_DATE)
                .build();
        productService.createProduct(fixedProductDto);

        //기본 정기적금 상품 생성
        ProductDto.Create itaProductDto = ProductDto.Create.builder()
                .name("정기적금")
                .productCode("310001")
                .subjectCode(SubjectCode.REGULAR)
                .basicRate(3.3)
                .startDate("20190101")
                .endDate(OBDateUtils.MAX_DATE)
                .build();
        productService.createProduct(itaProductDto);

        customerController.start();
    }

}
