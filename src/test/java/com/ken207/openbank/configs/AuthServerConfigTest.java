package com.ken207.openbank.configs;

import com.ken207.openbank.accounts.MemberRole;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.controller.BaseControllerTest;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.service.MemberService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    MemberService memberService;

    @Test
    @TestDescription("인증 토근을 발급 받는 테스트")
    public void getAuthToken() throws Exception {
        //given
        Set<MemberRole> roles = new HashSet();
        roles.add(MemberRole.ADMIN);
        roles.add(MemberRole.USER);

        String username = "ken@email.com";
        String password = "ken207";
        MemberEntity member = MemberEntity.builder()
                .email(username)
                .password(password)
                .roles(roles)
                .build();
        memberService.saveMember(member);

        String clientId = "myApp";
        String clientSecret = "pass";

        this.mockMvc.perform(post("/oauth/token")
                    .with(httpBasic(clientId, clientSecret))
                    .param("username", username)
                    .param("password", password)
                    .param("grant_type", "password")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());
        ;
    }

}