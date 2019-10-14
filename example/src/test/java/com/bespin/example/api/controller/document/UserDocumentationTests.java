package com.bespin.example.api.controller.document;

import com.bespin.example.api.controller.WebController;
import com.bespin.example.api.service.SampleService;
import com.bespin.example.api.service.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.bespin.example.api.controller.document.utils.ApiDocumentUtils.getDocumentRequest;
import static com.bespin.example.api.controller.document.utils.ApiDocumentUtils.getDocumentResponse;
import static com.bespin.example.api.controller.document.utils.DocumentFormatGenerator.getDateFormat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Project : group.example
 * Class : com.bespin.example.api.controller.document.UserDocumentationTests
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(WebController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@AutoConfigureRestDocs
public class UserDocumentationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SampleService sampleService;

    @Test
    public void findAll() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        //given
        List<UserDto.Response> responseList = Arrays.asList(
                UserDto.Response.builder()
                        .userId("123")
                        .userEmail("123")
                        .userName("123")
                        .department("123")
                        .createTime(ts)
                        .updateTime(ts)
                        .isActive(true)
                        .isUse(true)
                        .build(),
                UserDto.Response.builder()
                        .userId("1234")
                        .userEmail("1234")
                        .userName("1234")
                        .department("123")
                        .createTime(ts)
                        .updateTime(ts)
                        .isActive(true)
                        .isUse(true)
                        .build()
        );

        given(sampleService.findAll())
                .willReturn(responseList);

        //when
        ResultActions result = this.mockMvc.perform(
                get("/user")
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("user-find-all",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(beneathPath("data").withSubsectionId("data"),
                                fieldWithPath("users[].userId").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("users[].userEmail").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("users[].userName").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("users[].department").type(JsonFieldType.STRING).description("부서"),
                                fieldWithPath("users[].createTime").type(JsonFieldType.STRING).attributes(getDateFormat()).description("생성"),
                                fieldWithPath("users[].updateTime").type(JsonFieldType.STRING).attributes(getDateFormat()).description("업뎃"),
                                fieldWithPath("users[].isActive").type(JsonFieldType.BOOLEAN).description("삭제여부"),
                                fieldWithPath("users[].isUse").type(JsonFieldType.BOOLEAN).description("사용")
                        )
                ));
    }

    @Test
    public void findById() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        //given
        given(sampleService.findByUserId("1234"))
                .willReturn(UserDto.Response.builder()
                        .userId("1234")
                        .userEmail("1234")
                        .userName("1234")
                        .department("123")
                        .createTime(ts)
                        .updateTime(ts)
                        .isActive(true)
                        .isUse(true)
                        .build()
                );

        //when
        ResultActions result = this.mockMvc.perform(
                get("/user/{userId}", "1234")
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("user-find",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("userId").description("아이디")
                        ),
                        responseFields(beneathPath("data").withSubsectionId("data"),
                                fieldWithPath("user.userId").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("user.userEmail").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("user.userName").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("user.department").type(JsonFieldType.STRING).description("부서"),
                                fieldWithPath("user.createTime").type(JsonFieldType.STRING).attributes(getDateFormat()).description("생성"),
                                fieldWithPath("user.updateTime").type(JsonFieldType.STRING).attributes(getDateFormat()).description("업뎃"),
                                fieldWithPath("user.isActive").type(JsonFieldType.BOOLEAN).description("삭제여부"),
                                fieldWithPath("user.isUse").type(JsonFieldType.BOOLEAN).description("사용")
                        )
                ));
    }

    @Test
    public void add() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        //given
        UserDto.Response response = UserDto.Response.builder()
                .userId("1234")
                .userEmail("1234")
                .userName("1234")
                .department("123")
                .createTime(ts)
                .updateTime(ts)
                .isActive(true)
                .isUse(true)
                .build();

        given(sampleService.add(any(UserDto.Create.class)))
                .willReturn(response);

        //when
        UserDto.Create create = new UserDto.Create();
        create.setUserId("1234");
        create.setUserEmail("1234");
        create.setUserName("1234");
        create.setDepartment("1234");
        create.setIsUse(true);
        create.setIsActive(true);
        ResultActions result = this.mockMvc.perform(
                post("/user")
                        .content(objectMapper.writeValueAsString(create))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("user-add",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("userEmail").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("userName").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("department").type(JsonFieldType.STRING).description("부서"),
                                fieldWithPath("isUse").type(JsonFieldType.BOOLEAN).description("사용여부"),
                                fieldWithPath("isActive").type(JsonFieldType.BOOLEAN).description("삭제여부")
                        ),
                        responseFields(beneathPath("data").withSubsectionId("data"),
                                fieldWithPath("user.userId").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("user.userEmail").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("user.userName").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("user.department").type(JsonFieldType.STRING).description("부서"),
                                fieldWithPath("user.createTime").type(JsonFieldType.STRING).attributes(getDateFormat()).description("생성"),
                                fieldWithPath("user.updateTime").type(JsonFieldType.STRING).attributes(getDateFormat()).description("업뎃"),
                                fieldWithPath("user.isActive").type(JsonFieldType.BOOLEAN).description("삭제여부"),
                                fieldWithPath("user.isUse").type(JsonFieldType.BOOLEAN).description("사용")
                        )
                ));
    }

    @Test
    public void delete_by_id() throws Exception {

        //given
        doNothing()
                .when(sampleService)
                .delete("1234");

        //when
        ResultActions result = this.mockMvc.perform(
                delete("/user/{id}", "1234")
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("user-delete",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("id").description("아이디")
                        )
                ));
    }

    @Test
    public void update() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        //given
        UserDto.Response response = UserDto.Response.builder()
                .userId("1234")
                .userEmail("1234")
                .userName("1234")
                .department("123")
                .createTime(ts)
                .updateTime(ts)
                .isActive(true)
                .isUse(true)
                .build();

        given(sampleService.update(eq("1234"), any(UserDto.Update.class)))
                .willReturn(response);

        //when
        UserDto.Update update = new UserDto.Update();

        update.setUserEmail("1234");
        update.setUserName("1234");
        update.setDepartment("1234");
        update.setIsActive(true);
        update.setIsUse(true);


        ResultActions result = this.mockMvc.perform(
                put("/user/{userId}", "1234")
                        .content(objectMapper.writeValueAsString(update))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("user-update",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("userId").description("아이디")
                        ),
                        requestFields(
                                fieldWithPath("userEmail").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("userName").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("department").type(JsonFieldType.STRING).description("부서"),
                                fieldWithPath("isActive").type(JsonFieldType.BOOLEAN).description("삭제여부"),
                                fieldWithPath("isUse").type(JsonFieldType.BOOLEAN).description("사용")
                        ),
                        responseFields(
                                beneathPath("data").withSubsectionId("data"),
                                fieldWithPath("user.userId").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("user.userEmail").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("user.userName").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("user.department").type(JsonFieldType.STRING).description("부서"),
                                fieldWithPath("user.createTime").type(JsonFieldType.STRING).attributes(getDateFormat()).description("생성"),
                                fieldWithPath("user.updateTime").type(JsonFieldType.STRING).attributes(getDateFormat()).description("업뎃"),
                                fieldWithPath("user.isActive").type(JsonFieldType.BOOLEAN).description("삭제여부"),
                                fieldWithPath("user.isUse").type(JsonFieldType.BOOLEAN).description("사용")
                        )
                ));
    }
}