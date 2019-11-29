package com.ken207.openbank;

import com.ken207.openbank.consts.ConstBranch;
import com.ken207.openbank.customer.CustomerIbkApiConstroller;
import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.domain.Product;
import com.ken207.openbank.consts.ConstProduct;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.domain.enums.EmployeeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        System.out.println("InitDb init ############################");
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private final CustomerIbkApiConstroller customerIbkApiConstroller;

        public void dbInit() {
            em.persist(new Branch(ConstBranch.HEAD_ID10, "본점", BranchType.본점));
            em.persist(new Branch(ConstBranch.SEOUL_ID12, "서울지점", BranchType.지점));
            em.persist(new Branch(ConstBranch.BUSAN_ID11,"부산지점", BranchType.지점));

            em.persist(new Employee("인터넷사용자", EmployeeType.인터넷뱅킹, new Branch(ConstBranch.INTERNET_ID20,"인터넷뱅킹", BranchType.인터넷)));

            em.persist(new Product("보통예금", ConstProduct.SUBJECT_RG, 1.5));
            em.persist(new Product("정기예금", ConstProduct.SUBJECT_FX, 2.5));
            em.persist(new Product("정기적금", ConstProduct.SUBJECT_IT, 3.5));

            List<Employee> employees = em.createQuery("select e from Employee e where e.employeeType=:employeeType", Employee.class)
                    .setParameter("employeeType", EmployeeType.인터넷뱅킹).getResultList();

            if ( employees.size() > 0 ) {
                customerIbkApiConstroller.setInternetBankEmployee(employees.get(0));
            }
        }
    }


}
