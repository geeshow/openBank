package com.ken207.openbank.init;

import com.ken207.openbank.consts.ConstEmployee;
import com.ken207.openbank.controller.CustomerController;
import com.ken207.openbank.domain.BranchEntity;
import com.ken207.openbank.domain.EmployeeEntity;
import com.ken207.openbank.domain.products.ProductRgEntity;
import com.ken207.openbank.consts.ConstProduct;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.domain.enums.EmployeeType;
import com.ken207.openbank.repository.EmployeeRepository;
import com.ken207.openbank.repository.ProductRepository;
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
    EmployeeRepository employeeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //기본 처리자 생성
        BranchEntity branchEntity = new BranchEntity("인터넷뱅킹", "555-12-3456", "000", "1588-1234", BranchType.인터넷);
        EmployeeEntity employeeEntity = new EmployeeEntity(ConstEmployee.INTERNET, "인터넷사용자", EmployeeType.인터넷뱅킹, branchEntity);
        employeeRepository.save(employeeEntity);

        //기본 상품 생성
        productRepository.save(new ProductRgEntity("보통예금", ConstProduct.SUBJECT_RG, 1.5));
        productRepository.save(new ProductRgEntity("정기예금", ConstProduct.SUBJECT_FX, 2.5));
        productRepository.save(new ProductRgEntity("정기적금", ConstProduct.SUBJECT_IT, 3.5));

        customerController.start();
    }

}
