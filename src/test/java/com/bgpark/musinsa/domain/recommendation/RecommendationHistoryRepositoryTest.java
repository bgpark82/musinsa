package com.bgpark.musinsa.domain.recommendation;

import com.bgpark.musinsa.domain.recommendation.domain.RecommendationHistory;
import com.bgpark.musinsa.security.AES256Encrypter;
import com.bgpark.musinsa.security.AES256Properties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({AES256Encrypter.class, AES256Properties.class})
class RecommendationHistoryRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @ParameterizedTest
    @ValueSource(strings = "kimmusinsa@musinsa.com")
    void 이메일_유니크_조건_확인(String sameEmail) {
        em.persist(RecommendationHistory.create("김무신사", sameEmail, "MALE"));
        em.clear();
        em.flush();

        assertThrows(PersistenceException.class,
                () -> em.persist(RecommendationHistory.create("김무신사", sameEmail, "MALE")));
    }

    @Test
    void 이름_null_허용_불가() {
        assertThrows(PersistenceException.class,
                () -> em.persist(RecommendationHistory.create(null, "kimmusinsa@musinsa.com", "MALE")));
    }

    @Test
    void 이메일_null_허용_불가() {
        assertThrows(PersistenceException.class,
                () -> em.persist(RecommendationHistory.create("김무신사", null, "MALE")));
    }
}
