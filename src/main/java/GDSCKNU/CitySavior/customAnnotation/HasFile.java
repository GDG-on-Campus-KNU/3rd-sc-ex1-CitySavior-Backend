package GDSCKNU.CitySavior.customAnnotation;

import GDSCKNU.CitySavior.validator.HasFileValidator;
import jakarta.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HasFileValidator.class)
public @interface HasFile {

    String message() default "파일이 존재하지 않습니다.";
    Class[] groups() default {};
    Class[] payload() default {};

}
