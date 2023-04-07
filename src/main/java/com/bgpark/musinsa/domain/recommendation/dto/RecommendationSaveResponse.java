package com.bgpark.musinsa.domain.recommendation.dto;

import com.bgpark.musinsa.domain.common.Pattern;
import com.bgpark.musinsa.domain.recommendation.domain.RecommendationHistory;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 추천 히스토리 기록 응답 DTO
 * @author 박병길
 */
@Getter
@Builder
public class RecommendationSaveResponse {

    private Long id;

    private String name;

    private String email;

    private int requestCount;

    @JsonFormat(pattern = Pattern.DATE_TIME)
    private LocalDateTime createdDateTime;

    @JsonFormat(pattern = Pattern.DATE_TIME)
    private LocalDateTime updatedDateTime;

    public static RecommendationSaveResponse of(RecommendationHistory history) {
        return RecommendationSaveResponse.builder()
                .id(history.getId())
                .name(history.getName())
                .email(history.getEmail())
                .requestCount(history.getRequestCount())
                .createdDateTime(history.getCreatedDateTime())
                .updatedDateTime(history.getUpdatedDateTime())
                .build();
    }
}
