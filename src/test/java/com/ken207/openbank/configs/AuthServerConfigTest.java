package com.ken207.openbank.configs;

import com.ken207.openbank.user.MemberRole;
import com.ken207.openbank.common.AppSecurityProperties;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthServerConfigTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberService memberService;

    @Autowired
    AppSecurityProperties appSecurityProperties;

    @Test
    @TestDescription("인증 토근을 발급 받는 테스트")
    public void getAuthToken() throws Exception {
        //given
        MemberEntity member = MemberEntity.builder()
                .email(appSecurityProperties.getUserUsername())
                .password(appSecurityProperties.getUserPassword())
                .roles(Set.of(MemberRole.USER))
                .build();
        memberService.createUser(member);

        String clientId = appSecurityProperties.getDefaultClientId();
        String clientSecret = appSecurityProperties.getDefaultClientSecret();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", appSecurityProperties.getUserUsername());
        params.add("password", appSecurityProperties.getUserPassword());

        this.mockMvc.perform(post("/oauth/token")
                    .with(httpBasic(clientId, clientSecret))
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .params(params)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());

    }

}