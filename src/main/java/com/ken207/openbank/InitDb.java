package com.ken207.openbank;

import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
            em.persist(new Branch("서울본점"));
            em.persist(new Branch("부산지점"));
            em.persist(new Branch("대구지점"));
            em.persist(new Branch("대전지점"));
            em.persist(new Branch("광주지점"));
            em.persist(new Branch("경기지점"));
            em.persist(new Branch("제주지점"));
            em.persist(new Branch("강북지점"));
        }
    }


}
