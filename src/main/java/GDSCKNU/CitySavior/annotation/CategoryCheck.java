package GDSCKNU.CitySavior.annotation;

import GDSCKNU.CitySavior.validator.CategoryValidator;
import jakarta.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryValidator.class)
public @interface CategoryCheck {

    String message() default "올바른 카테고리가 아닙니다.";
    Class[] groups() default {};
    Class[] payload() default {};

    Class<? extends java.lang.Enum<?>> enumClass();

    boolean ignoreCase() default false;

}
