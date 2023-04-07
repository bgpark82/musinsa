package com.bgpark.musinsa.domain.recommendation.domain;

import com.bgpark.musinsa.domain.common.BaseTime;
import com.bgpark.musinsa.domain.common.StringEncryptConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.bgpark.musinsa.domain.common.Constant.DEFAULT_REQUEST_COUNT;
import static com.bgpark.musinsa.domain.common.Constant.INCREAMENT_UNIT;

/**
 * 추천 상품 조회 시, 추천 히스토리를 기록하는 엔티티
 * @author 박병길
 */
@Entity
@Getter
@Table(name = "recommendation_history")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendationHistory extends BaseTime {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Convert(converter = StringEncryptConverter.class)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "request_count")
    private int requestCount;

    public static RecommendationHistory create(String name, String email, String gender) {
        return new RecommendationHistory(name, email, gender, DEFAULT_REQUEST_COUNT);}

    public RecommendationHistory(String name, String email, String gender, int requestCount)  {
        this.name = name;
        this.email = email;
        this.gender = Gender.valueOf(gender);
        this.requestCount = requestCount;
    }

    public void increaseCount() {
        requestCount += INCREAMENT_UNIT;
    }
}
