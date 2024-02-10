package GDSCKNU.CitySavior.validator.category;

import GDSCKNU.CitySavior.annotation.CategoryCheck;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class CategoryValidator implements ConstraintValidator<CategoryCheck, String> {

    private CategoryCheck annotation;

    @Override
    public void initialize(CategoryCheck constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if (enumValues != null && Arrays.stream(enumValues)
                .anyMatch(enumValue ->
                        value.equals(enumValue.toString()) ||
                                (this.annotation.ignoreCase() && value.equalsIgnoreCase(enumValue.toString())))) {
            return true;
        }

        return false;
    }
}
