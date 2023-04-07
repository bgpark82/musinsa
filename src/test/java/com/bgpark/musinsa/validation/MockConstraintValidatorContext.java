package com.bgpark.musinsa.validation;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorContext;

class MockConstraintValidatorContext implements ConstraintValidatorContext {
    @Override
    public void disableDefaultConstraintViolation() {

    }

    @Override
    public String getDefaultConstraintMessageTemplate() {
        return null;
    }

    @Override
    public ClockProvider getClockProvider() {
        return null;
    }

    @Override
    public ConstraintViolationBuilder buildConstraintViolationWithTemplate(String messageTemplate) {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }

}
