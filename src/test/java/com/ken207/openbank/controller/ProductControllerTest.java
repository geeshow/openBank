package com.ken207.openbank.controller;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.dto.ProductDto;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ProductControllerTest extends BaseControllerTest {

    @Test
    @TestDescription("상품 생성 정상 테스트")
    public void createProduct() throws Exception {
        //given
        String productName = "온라인 보통예금";
        String productCode = "130123";
        SubjectCode subjectCode = SubjectCode.REGULAR;
        Double rate = 1.2;
        String startDate = OBDateUtils.getToday();
        String endDate = OBDateUtils.MAX_DATE;

        ProductDto.Create productRequestDto = ProductDto.Create.builder()
                .name(productName)
                .productCode(productCode)
                .subjectCode(subjectCode)
                .basicRate(rate)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        //when & then
        mockMvc.perform(post("/api/product")
                .header(HttpHeaders.AUTHORIZATION, this.getBearerToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(productRequestDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("name").value(productRequestDto.getName()))
                .andExpect(jsonPath("productCode").value(productRequestDto.getProductCode()))
                .andExpect(jsonPath("subjectCode").value(productRequestDto.getSubjectCode().toString()))
                .andExpect(jsonPath("startDate").value(productRequestDto.getStartDate()))
                .andExpect(jsonPath("endDate").value(productRequestDto.getEndDate()))
                .andExpect(jsonPath("basicRate").value(productRequestDto.getBasicRate()))
                .andDo(document("create-product",
                        getLinksOfProduct(),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("name").description("name of new product"),
                                fieldWithPath("productCode").description("representive code of new product"),
                                fieldWithPath("subjectCode").description("product type. 13:regular savings account, 24:fixed deposit, 31:installment savings"),
                                fieldWithPath("basicRate").description("basic interest rate"),
                                fieldWithPath("startDate").description("start date for sale"),
                                fieldWithPath("endDate").description("end date for sale")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        getResponseFieldsOfProduct()
                ));
    }

    private LinksSnippet getLinksOfProduct() {
        return links(
                linkWithRel("self").description("link to self"),
                linkWithRel("product-list").description("link to query products"),
                linkWithRel("create").description("link to create a product"),
                linkWithRel("adjust").description("link to adjust a existing product"),
                linkWithRel("profile").description("link to profile."));
    }

    private ResponseFieldsSnippet getResponseFieldsOfProduct() {
        return responseFields(
                fieldWithPath("name").description("name of new product"),
                fieldWithPath("productCode").description("Code of new product"),
                fieldWithPath("subjectCode").description("product Type. 13:regular savings account, 24:fixed deposit, 31:installment savings"),
                fieldWithPath("startDate").description("start date for sale"),
                fieldWithPath("endDate").description("end date for sale"),
                fieldWithPath("basicRate").description("basic interest rate"),
                fieldWithPathAsSelf(),
                fieldWithPathAsQuery(),
                fieldWithPathAsCreate(),
                fieldWithPathAsAdjust(),
                fieldWithPathAsProfile()
        );
    }

    private FieldDescriptor fieldWithPathAsSelf() {
        return fieldWithPath("_links.self.href").description("link to self.");
    }
    private FieldDescriptor fieldWithPathAsQuery() {
        return fieldWithPath("_links.product-list.href").description("link to query accountes.");
    }
    private FieldDescriptor fieldWithPathAsCreate() {
        return fieldWithPath("_links.create.href").description("link to create a product.");
    }
    private FieldDescriptor fieldWithPathAsAdjust() {
        return fieldWithPath("_links.adjust.href").description("link to adjust an existing product.");
    }
    private FieldDescriptor fieldWithPathAsProfile() {
        return fieldWithPath("_links.profile.href").description("link to profile.");
    }
}