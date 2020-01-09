package com.ken207.openbank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken207.openbank.common.AppSecurityProperties;
import com.ken207.openbank.common.RestDocsConfiguration;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.service.MemberService;
import com.ken207.openbank.user.MemberRole;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.Jackson2JsonParser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@EnableJpaAuditing
@Import(RestDocsConfiguration.class)
@ActiveProfiles("test")
@Ignore
public class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    MemberService memberService;

    @Autowired
    AppSecurityProperties appSecurityProperties;

    public String getBearerToken() throws Exception {
        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", appSecurityProperties.getUserUsername());
        params.add("password", appSecurityProperties.getUserPassword());

        this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(appSecurityProperties.getDefaultClientId(), appSecurityProperties.getDefaultClientSecret()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params)
        );
        ResultActions perform = this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(appSecurityProperties.getDefaultClientId(), appSecurityProperties.getDefaultClientSecret()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params)
        );
        String responseBody = perform.andReturn().getResponse().getContentAsString();
        Jackson2JsonParser parser = new Jackson2JsonParser();
        return "Bearer " + parser.parseMap(responseBody).get("access_token").toString();
    }


    public String getAdminBearerToken() throws Exception {
        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", appSecurityProperties.getAdminUsername());
        params.add("password", appSecurityProperties.getAdminPassword());

        this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(appSecurityProperties.getDefaultClientId(), appSecurityProperties.getDefaultClientSecret()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params)
        );
        ResultActions perform = this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(appSecurityProperties.getDefaultClientId(), appSecurityProperties.getDefaultClientSecret()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params)
        );
        String responseBody = perform.andReturn().getResponse().getContentAsString();
        Jackson2JsonParser parser = new Jackson2JsonParser();
        return "Bearer " + parser.parseMap(responseBody).get("access_token").toString();
    }
}
