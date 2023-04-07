package com.bgpark.musinsa.domain.recommendation;

import com.bgpark.musinsa.domain.recommendation.application.RecommendationHistoryService;
import com.bgpark.musinsa.domain.recommendation.domain.RecommendationHistory;
import com.bgpark.musinsa.domain.recommendation.dto.RecommendationSaveRequest;
import com.bgpark.musinsa.domain.recommendation.ui.RecommendationController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RecommendationController.class)
class RecommendationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private RecommendationHistoryService recommendationHistoryService;

    @ParameterizedTest
    @NullAndEmptySource
    void null_혹은_빈_이메일(String email) throws Exception {
        RecommendationSaveRequest request = RecommendationSaveRequest.builder()
                .name("김무신사")
                .gender("MALE")
                .email(email)
                .build();

        mvc.perform(post("/musinsa/recommendation")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.invalidField").value("email"))
                .andExpect(jsonPath("$.invalidValue").value(email))
                .andExpect(jsonPath("$.message").value("must not be blank"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"));
    }

    @ParameterizedTest
    @ValueSource(strings = "email")
    void 유효하지_않은_이메일(String email) throws Exception {
        RecommendationSaveRequest request = RecommendationSaveRequest.builder()
                .name("김무신사")
                .gender("MALE")
                .email(email)
                .build();

        mvc.perform(post("/musinsa/recommendation")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(jsonPath("$.invalidField").value("email"))
                .andExpect(jsonPath("$.invalidValue").value(email))
                .andExpect(jsonPath("$.message").value("must be a well-formed email address"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void null_혹은_빈_성별(String gender) throws Exception {
        RecommendationSaveRequest request = RecommendationSaveRequest.builder()
                .name("김무신사")
                .gender(gender)
                .email("kimmusinsa@musinsa.com")
                .build();

        mvc.perform(post("/musinsa/recommendation")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(jsonPath("$.invalidField").value("gender"))
                .andExpect(jsonPath("$.invalidValue").value(gender))
                .andExpect(jsonPath("$.message").value("must be a valid gender"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"etc", "male", "female", "unchecked"})
    void 유효하지_않은_성별(String gender) throws Exception {
        RecommendationSaveRequest request = RecommendationSaveRequest.builder()
                .name("김무신사")
                .gender(gender)
                .email("kimmusinsa@musinsa.com")
                .build();

        mvc.perform(post("/musinsa/recommendation")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.invalidField").value("gender"))
                .andExpect(jsonPath("$.invalidValue").value(gender))
                .andExpect(jsonPath("$.message").value("must be a valid gender"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"MALE", "FEMALE", "UNCHECKED"})
    void 유효한_성별(String gender) throws Exception {
        given(recommendationHistoryService.save(any())).willReturn(RecommendationHistory.create("김무신사", "kimmusinsa@musinsa", gender));
        RecommendationSaveRequest request = RecommendationSaveRequest.builder()
                .name("김무신사")
                .gender(gender)
                .email("kimmusinsa@musinsa.com")
                .build();

        mvc.perform(post("/musinsa/recommendation")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
