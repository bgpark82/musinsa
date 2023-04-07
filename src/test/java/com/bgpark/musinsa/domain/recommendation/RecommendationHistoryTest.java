package com.bgpark.musinsa.domain.recommendation;

import com.bgpark.musinsa.domain.recommendation.domain.RecommendationHistory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecommendationHistoryTest {

    @Test
    void 개수_증가() {
        RecommendationHistory history = RecommendationHistory.create("김무신사", "kimmusinsa@musinsa.com", "MALE");

        history.increaseCount();

        assertThat(history.getRequestCount()).isEqualTo(1);
    }
}
