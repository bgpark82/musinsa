package com.bgpark.musinsa.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class GenderValidatorTest {

    private GenderValidator validator = new GenderValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void null_혹은_빈_성별(String gender) {
        boolean isValid = validator.isValid(gender, new MockConstraintValidatorContext());
        assertThat(isValid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"gender", "male", "female", "unchecked"})
    void 유효하지_않은_성별(String gender) {
        boolean isValid = validator.isValid(gender, new MockConstraintValidatorContext());
        assertThat(isValid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"MALE", "FEMALE", "UNCHECKED"})
    void 유효한_성별(String gender) {
        boolean isValid = validator.isValid(gender, new MockConstraintValidatorContext());
        assertThat(isValid).isTrue();
    }
}
