package com.bgpark.musinsa.domain.recommendation.ui;

import com.bgpark.musinsa.domain.recommendation.domain.RecommendationHistory;
import com.bgpark.musinsa.domain.recommendation.application.RecommendationHistoryService;
import com.bgpark.musinsa.domain.recommendation.dto.RecommendationSaveRequest;
import com.bgpark.musinsa.domain.recommendation.dto.RecommendationSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author 박병길
 */
@RestController
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationHistoryService recommendationHistoryService;

    /**
     * 추천 상품 조회 시, 추천 히스토리를 기록하는 API
     * @param request
     * @return ResponseEntity
     */
    @PostMapping("/musinsa/recommendation")
    public ResponseEntity<RecommendationSaveResponse> save(@Valid @RequestBody RecommendationSaveRequest request) {
        final RecommendationHistory history = recommendationHistoryService.save(request);
        return ResponseEntity.ok(RecommendationSaveResponse.of(history));
    }
}

