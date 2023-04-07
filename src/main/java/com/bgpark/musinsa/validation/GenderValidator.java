package com.bgpark.musinsa.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

import static com.bgpark.musinsa.domain.common.Constant.*;

/**
 * @author 박병길
 */
public class GenderValidator implements ConstraintValidator<Gender, String> {

    private static final List<String> genders = Arrays.asList(MALE, FEMALE, UNCHECKED);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return genders.contains(value);
    }
}
