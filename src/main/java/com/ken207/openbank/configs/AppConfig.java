package com.ken207.openbank.configs;

import com.ken207.openbank.accounts.MemberRole;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {

            @Autowired
            MemberService memberService;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                Set<MemberRole> roles = new HashSet();
                roles.add(MemberRole.ADMIN);
                roles.add(MemberRole.USER);

                MemberEntity member = MemberEntity.builder()
                        .email("ken@email.com")
                        .password("ken207")
                        .roles(roles)
                        .build();

                memberService.saveMember(member);

            }
        };
    }
}
