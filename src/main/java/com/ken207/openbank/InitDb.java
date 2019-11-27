package com.ken207.openbank;

import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.domain.Product;
import com.ken207.openbank.domain.consts.ConstProduct;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.domain.enums.EmployeeType;
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
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            em.persist(new Branch("서울본점", BranchType.본점));
            em.persist(new Branch("부산지점", BranchType.지점));
            em.persist(new Branch("대구지점", BranchType.지점));
            em.persist(new Branch("대전지점", BranchType.지점));
            em.persist(new Branch("광주지점", BranchType.지점));
            em.persist(new Branch("경기지점", BranchType.지점));
            em.persist(new Branch("제주지점", BranchType.지점));
            em.persist(new Branch("강북지점", BranchType.지점));

            Branch headOffice = em.createQuery("select b from Branch b where b.branchType=:branchType", Branch.class)
                    .setParameter("branchType", BranchType.본점)
                    .getSingleResult();
            em.persist(new Employee("인터넷뱅킹", EmployeeType.인터넷뱅킹, headOffice));

            em.persist(new Product("보통예금", ConstProduct.SUBJECT_RG, 1.5));
            em.persist(new Product("정기예금", ConstProduct.SUBJECT_FX, 2.5));
            em.persist(new Product("정기적금", ConstProduct.SUBJECT_IT, 3.5));
        }
    }


}
