package com.ken207.openbank.service;

import com.ken207.openbank.accounts.MemberRole;
import com.ken207.openbank.controller.BaseControllerTest;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MemberServiceTest extends BaseControllerTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void findByUsername() {
        //given
        Set<MemberRole> roles = new HashSet();
        roles.add(MemberRole.ADMIN);
        roles.add(MemberRole.USER);

        String password = "2484";
        String username = "ken2484@email.com";
        MemberEntity user = MemberEntity.builder()
                .email(username)
                .password(password)
                .roles(roles)
                .build();
        this.memberRepository.save(user);

        //when
        UserDetailsService userDetailsService = memberService;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //then
        assertThat(userDetails.getPassword()).isEqualTo(password);
    }

}