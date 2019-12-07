package com.ken207.openbank;

import com.ken207.openbank.consts.ConstEmployee;
import com.ken207.openbank.controller.api.CustomerApiController;
import com.ken207.openbank.domain.BranchEntity;
import com.ken207.openbank.domain.EmployeeEntity;
import com.ken207.openbank.domain.ProductEntity;
import com.ken207.openbank.consts.ConstProduct;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.domain.enums.EmployeeType;
import com.ken207.openbank.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        System.out.println("InitDb init ############################");
 //       initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private final CustomerApiController customerApiController;
        private final BranchRepository branchRepository;

        public void dbInit() {

            branchRepository.save(new BranchEntity("본점", "111-12-12345", "001", "02-1234-1234", BranchType.본점));
            branchRepository.save(new BranchEntity("서울지점", "222-12-23456", "002", "02-4321-1234",BranchType.지점));
            branchRepository.save(new BranchEntity("부산지점", "333-12-3456", "003", "051-1111-1234",BranchType.지점));

            String employeeCodeOfIb = ConstEmployee.INTERNET;
            em.persist(new EmployeeEntity(employeeCodeOfIb,"인터넷사용자", EmployeeType.인터넷뱅킹,
                    new BranchEntity("인터넷뱅킹", "555-12-3456", "000", "1588-1234",BranchType.인터넷)));

            em.persist(new ProductEntity("보통예금", ConstProduct.SUBJECT_RG, 1.5));
            em.persist(new ProductEntity("정기예금", ConstProduct.SUBJECT_FX, 2.5));
            em.persist(new ProductEntity("정기적금", ConstProduct.SUBJECT_IT, 3.5));

            customerApiController.start();
        }
    }


}
