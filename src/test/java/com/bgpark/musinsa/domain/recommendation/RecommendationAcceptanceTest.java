package com.bgpark.musinsa.domain.recommendation;

import com.bgpark.musinsa.domain.recommendation.dto.RecommendationSaveRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecommendationAcceptanceTest {

    @LocalServerPort
    private int port;

    @Test
    void 추천_히스토리를_기록한다() {
        String name = "김무신사";
        String gender = "MALE";
        String email = "kimmusinsa@musinsa.com";
        RecommendationSaveRequest request = RecommendationSaveRequest.builder()
                .name(name)
                .gender(gender)
                .email(email)
                .build();

        ExtractableResponse<Response> response = given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .log().all()
                .when().post("/musinsa/recommendation")
                .then().log().all()
                .extract();

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getString("name")).isEqualTo(name),
                () -> assertThat(response.jsonPath().getString("email")).isEqualTo(email),
                () -> assertThat(response.jsonPath().getLong("requestCount")).isEqualTo(1L)
        );
    }
}
