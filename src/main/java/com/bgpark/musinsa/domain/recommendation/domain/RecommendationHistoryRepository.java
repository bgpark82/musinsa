package com.bgpark.musinsa.domain.recommendation.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author 박병길
 */
public interface RecommendationHistoryRepository extends JpaRepository<RecommendationHistory, Long> {
    Optional<RecommendationHistory> findByEmail(String email);
}
