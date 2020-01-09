package com.ken207.openbank.init;

import com.ken207.openbank.common.AppSecurityProperties;
import com.ken207.openbank.domain.Member;
import com.ken207.openbank.service.MemberService;
import com.ken207.openbank.user.MemberRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DefaultMemberInitializer implements ApplicationRunner
{

    @Autowired
    MemberService memberService;

    @Autowired
    AppSecurityProperties appSecurityProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member admin = Member.builder()
                .email(appSecurityProperties.getAdminUsername())
                .password(appSecurityProperties.getAdminPassword())
                .roles(Set.of(MemberRole.ADMIN, MemberRole.USER))
                .build();

        memberService.createUser(admin);

        Member user = Member.builder()
                .email(appSecurityProperties.getUserUsername())
                .password(appSecurityProperties.getUserPassword())
                .roles(Set.of(MemberRole.ADMIN, MemberRole.USER))
                .build();

        memberService.createUser(user);
    }

}