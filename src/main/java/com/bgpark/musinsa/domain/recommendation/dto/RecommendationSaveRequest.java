package com.bgpark.musinsa.domain.recommendation.dto;

import com.bgpark.musinsa.domain.recommendation.domain.RecommendationHistory;
import com.bgpark.musinsa.validation.Gender;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 추천 히스토리 기록 요청 DTO
 * @author 박병길
 */
@Getter
@Builder
public class RecommendationSaveRequest {

    @NotBlank
    private String name;

    @Gender
    private String gender;

    @Email
    @NotBlank
    private String email;

    public RecommendationHistory toHistory() {
        return RecommendationHistory.create(name, email, gender);
    }
}
