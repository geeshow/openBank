package com.ken207.openbank.controller;

import com.ken207.openbank.common.RestDocsConfiguration;
import com.ken207.openbank.controller.BaseControllerTest;
import com.ken207.openbank.domain.enums.BranchType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class IndexControllerTest extends BaseControllerTest {

    @Test
    public void indexAccess() throws Exception {
        this.mockMvc.perform(get("/api"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("index",
                        links(
                                linkWithRel("regular-account").description("link to regular account list."),
                                linkWithRel("customers").description("link to customer list."),
                                linkWithRel("branches").description("link to branch list."),
                                linkWithRel("profile").description("link to profile.")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("_links.regular-account.href").description("link to regular account list."),
                                fieldWithPath("_links.customers.href").description("link to customer list."),
                                fieldWithPath("_links.branches.href").description("link to branch list."),
                                fieldWithPath("_links.profile.href").description("link to profile.")
                        )))
        ;
    }
}
