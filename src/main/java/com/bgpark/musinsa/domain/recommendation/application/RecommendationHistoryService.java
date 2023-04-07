package com.bgpark.musinsa.domain.recommendation.application;

import com.bgpark.musinsa.domain.recommendation.dto.RecommendationSaveRequest;
import com.bgpark.musinsa.domain.recommendation.domain.RecommendationHistory;
import com.bgpark.musinsa.domain.recommendation.domain.RecommendationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 박병길
 */
@Component
@RequiredArgsConstructor
public class RecommendationHistoryService {

    private final RecommendationHistoryRepository recommendationHistoryRepository;

    @Transactional
    public RecommendationHistory save(RecommendationSaveRequest request) {
        final RecommendationHistory history = recommendationHistoryRepository
                .findByEmail(request.getEmail())
                .orElse(request.toHistory());

        history.increaseCount();

        return recommendationHistoryRepository.save(history);
    }
}
