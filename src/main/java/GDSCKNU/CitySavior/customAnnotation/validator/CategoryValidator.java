package GDSCKNU.CitySavior.customAnnotation.validator;

import static GDSCKNU.CitySavior.exception.error.CategoryError.CATEGORY_NOT_FOUND_ERROR;
import static GDSCKNU.CitySavior.global.exception.error.GlobalError.INTERNAL_DATABASE_ERROR;

import GDSCKNU.CitySavior.customAnnotation.CategoryCheck;
import GDSCKNU.CitySavior.exception.CategoryException;
import GDSCKNU.CitySavior.global.exception.error.GlobalError;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import org.apache.http.HttpStatus;

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

        throw new CategoryException(CATEGORY_NOT_FOUND_ERROR);
    }
}
