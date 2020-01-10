package com.ken207.openbank.controller;

import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.dto.request.BranchRequest;
import com.ken207.openbank.repository.BranchRepository;
import com.ken207.openbank.repository.MemberRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.stream.IntStream;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BranchControllerTest extends BaseControllerTest {

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @TestDescription("정상적으로 지점을 생성하는 테스트")
    public void createBranch() throws Exception {
        //given
        String branchName = "테스트지점";
        String businessNumber = "123-12-12345";
        String taxOfficeCode = "112";
        String telNumber = "02-1234-1234";
        BranchType branchType = BranchType.지점;
        BranchRequest branchRequest = BranchRequest.builder()
                .name(branchName)
                .businessNumber(businessNumber)
                .taxOfficeCode(taxOfficeCode)
                .telNumber(telNumber)
                .build();

        //when & then
        mockMvc.perform(post("/api/branch")
                    .header(HttpHeaders.AUTHORIZATION, this.getBearerToken())
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(branchRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("regDateTime").exists())
                .andExpect(jsonPath("name").value(branchName))
                .andExpect(jsonPath("businessNumber").value(businessNumber))
                .andExpect(jsonPath("taxOfficeCode").value(taxOfficeCode))
                .andExpect(jsonPath("telNumber").value(telNumber))
                .andExpect(jsonPath("branchType").value(branchType.toString()))
                .andDo(document("create-branch",
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("query-branches").description("link to query branches"),
                                linkWithRel("update-branch").description("link to update an existing branch"),
                                linkWithRel("profile").description("link to profile.")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("name").description("Name of new branch"),
                                fieldWithPath("businessNumber").description("business number of new branch"),
                                fieldWithPath("taxOfficeCode").description("tax office code of new branch"),
                                fieldWithPath("telNumber").description("telephone number of new branch")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identifier of new branch"),
                                fieldWithPath("name").description("name of new branch"),
                                fieldWithPath("businessNumber").description("business number of new branch"),
                                fieldWithPath("taxOfficeCode").description("tax office code of new branch"),
                                fieldWithPath("telNumber").description("telephone number of new branch"),
                                fieldWithPath("regDateTime").description("registration date of new branch"),
                                fieldWithPath("branchType").description("branch type of new branch"),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.query-branches.href").description("link to query branches."),
                                fieldWithPath("_links.update-branch.href").description("link to update existing branch."),
                                fieldWithPath("_links.profile.href").description("link to profile.")
                        )

                ))
        ;
    }


    @Test
    @TestDescription("30개의 지점을 10개씩 두번째 페이지 조회하기")
    public void queryBranches() throws Exception {
        //given
        IntStream.range(0,30).forEach(this::generateBranch);

        //when & then
        this.mockMvc.perform(get("/api/branch")
                .param("page", "1")
                .param("size", "10")
                .param("sort", "name,DESC")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page").exists())
                .andExpect(jsonPath("_embedded.branchResponseList[0]._links.self").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.profile").exists())
                .andDo(document("query-branches",
                        links(
                                linkWithRel("first").description("link to first page"),
                                linkWithRel("prev").description("link to prev page"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("next").description("link to next page"),
                                linkWithRel("last").description("link to last page"),
                                linkWithRel("profile").description("link to profile.")
                        ),
                        requestParameters(
                                parameterWithName("page").description("현재 페이지 번호. 0페이지 부터 시작."),
                                parameterWithName("size").description("한 페이지의 사이즈"),
                                parameterWithName("sort").description("데이터 정렬. ex)name, DESC")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("_embedded.branchResponseList[0].id").description("identifier of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].name").description("name of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].businessNumber").description("businessNumber of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].taxOfficeCode").description("taxOfficeCode of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].telNumber").description("telNumber date of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].regDateTime").description("regDateTime branch of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].branchType").description("branchType branch of branch"),
                                fieldWithPath("_embedded.branchResponseList[0]._links.self.href").description("link to self."),
                                fieldWithPath("_links.first.href").description("link to first."),
                                fieldWithPath("_links.prev.href").description("link to prev."),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.next.href").description("link to next."),
                                fieldWithPath("_links.last.href").description("link to last."),
                                fieldWithPath("_links.profile.href").description("link to profile."),
                                fieldWithPath("page.size").description("size of one page."),
                                fieldWithPath("page.totalElements").description("amount of datas."),
                                fieldWithPath("page.totalPages").description("amount of pages."),
                                fieldWithPath("page.number").description("current page number.")
                        )
                ));
    }


    @Test
    @TestDescription("30개의 지점을 10개씩 두번째 페이지 조회하기")
    public void queryBranchesWithAuthentication() throws Exception {
        //given
        IntStream.range(0,30).forEach(this::generateBranch);

        //when & then
        this.mockMvc.perform(get("/api/branch")
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                        .param("page", "1")
                        .param("size", "10")
                        .param("sort", "name,DESC"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page").exists())
                .andExpect(jsonPath("_embedded.branchResponseList[0]._links.self").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.create-branch").exists())
                .andExpect(jsonPath("_links.profile").exists())
                .andDo(document("query-branches",
                        links(
                                linkWithRel("first").description("link to first page"),
                                linkWithRel("prev").description("link to prev page"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("next").description("link to next page"),
                                linkWithRel("last").description("link to last page"),
                                linkWithRel("create-branch").description("link to create branch."),
                                linkWithRel("profile").description("link to profile.")
                        ),
                        requestParameters(
                                parameterWithName("page").description("현재 페이지 번호. 0페이지 부터 시작."),
                                parameterWithName("size").description("한 페이지의 사이즈"),
                                parameterWithName("sort").description("데이터 정렬. ex)name, DESC")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("_embedded.branchResponseList[0].id").description("identifier of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].name").description("name of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].businessNumber").description("businessNumber of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].taxOfficeCode").description("taxOfficeCode of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].telNumber").description("telNumber date of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].regDateTime").description("regDateTime branch of branch"),
                                fieldWithPath("_embedded.branchResponseList[0].branchType").description("branchType branch of branch"),
                                fieldWithPath("_embedded.branchResponseList[0]._links.self.href").description("link to self."),
                                fieldWithPath("_links.first.href").description("link to first."),
                                fieldWithPath("_links.prev.href").description("link to prev."),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.next.href").description("link to next."),
                                fieldWithPath("_links.last.href").description("link to last."),
                                fieldWithPath("_links.create-branch.href").description("link to create branch."),
                                fieldWithPath("_links.profile.href").description("link to profile."),
                                fieldWithPath("page.size").description("size of one page."),
                                fieldWithPath("page.totalElements").description("amount of datas."),
                                fieldWithPath("page.totalPages").description("amount of pages."),
                                fieldWithPath("page.number").description("current page number.")
                        )
                ));
    }

    @Test
    @TestDescription("지점 한건 조회하기")
    public void getBranch() throws Exception {
        //given
        int index = 200;
        Branch branch = generateBranch(index);

        //when & then
        this.mockMvc.perform(get("/api/branch/{id}", branch.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links.self").hasJsonPath())
                .andExpect(jsonPath("_links.update").doesNotExist())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("regDateTime").exists())
                .andExpect(jsonPath("name").value("지점이름" + index))
                .andExpect(jsonPath("businessNumber").value("bzNum" + index))
                .andExpect(jsonPath("taxOfficeCode").value("00" + index))
                .andExpect(jsonPath("telNumber").value("02-1234-1234"))
                .andExpect(jsonPath("branchType").value(BranchType.지점.toString()))
                .andDo(document("get-branch",
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identifier of branch"),
                                fieldWithPath("name").description("name of branch"),
                                fieldWithPath("businessNumber").description("businessNumber of branch"),
                                fieldWithPath("taxOfficeCode").description("taxOfficeCode of branch"),
                                fieldWithPath("telNumber").description("telNumber date of branch"),
                                fieldWithPath("regDateTime").description("regDateTime branch of branch"),
                                fieldWithPath("branchType").description("branchType branch of branch"),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.update-branch.href").description("link to first."),
                                fieldWithPath("_links.query-branches.href").description("link to prev."),
                                fieldWithPath("_links.profile.href").description("link to profile.")
                        )))
        ;
    }

    @Test
    @TestDescription("지점 수정의 정상처리 확인")
    public void updateBranch() throws Exception {
        //given
        Branch branch = this.generateBranch(333);

        String branchName = "수정된지점명";

        BranchRequest branchRequest = BranchRequest.builder()
                .name(branch.getName())
                .businessNumber("123")
                .taxOfficeCode("222222")
                .telNumber(branch.getTelNumber())
                .build();

        branchRequest.setName(branchName);

        //when & then
        this.mockMvc.perform(put("/api/branch/{id}", branch.getId())
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.objectMapper.writeValueAsString(branchRequest))
                    )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(branchName))
                .andExpect(jsonPath("_links.self").exists())
        ;
    }

    @Test
    @TestDescription("ID값이 없는 경우 오류 발생")
    public void updateBranch405_withoutId() throws Exception {
        //given
        Branch branch = this.generateBranch(230);

        String branchName = "수정된지점명";
        BranchRequest branchRequest = BranchRequest.builder()
                .name(branch.getName())
                .businessNumber(branch.getBusinessNumber())
                .taxOfficeCode(branch.getTaxOfficeCode())
                .telNumber(branch.getTelNumber())
                .build();

        branchRequest.setName(branchName);

        //when & then
        this.mockMvc.perform(put("/api/branch/{id}", "")
                    .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(this.objectMapper.writeValueAsString(branchRequest)))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed())
        ;
    }

    @Test
    @TestDescription("존재하지 않는 지점의 수정인 경우 오류 발생")
    public void updateBranch404() throws Exception {
        //given
        Branch branch = this.generateBranch(230);

        String branchName = "수정된지점명";
        BranchRequest branchRequest = BranchRequest.builder()
                .name(branch.getName())
                .businessNumber(branch.getBusinessNumber())
                .taxOfficeCode(branch.getTaxOfficeCode())
                .telNumber(branch.getTelNumber())
                .build();

        //when & then
        this.mockMvc.perform(put("/api/branch/123123")
                    .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(this.objectMapper.writeValueAsString(branchRequest)))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    @TestDescription("입력값이 비어있는 경우 수정 실패")
    public void updateBranch400() throws Exception {
        //given
        Branch branch = this.generateBranch(230);

        BranchRequest branchRequest = BranchRequest.builder()
                .build();

        //when & then
        this.mockMvc.perform(put("/api/branch/{id}", branch.getId())
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.objectMapper.writeValueAsString(branchRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("_links.index.href").exists())
        ;
    }

    private Branch generateBranch(int index) {
        Branch branch = new Branch("지점이름" + index, "bzNum" + index, "00" + index, "02-1234-1234", BranchType.지점);
        return branchRepository.save(branch);
    }

}