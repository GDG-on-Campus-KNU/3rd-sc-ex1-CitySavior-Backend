package GDSCKNU.CitySavior.validator.multipart;

import GDSCKNU.CitySavior.annotation.HasFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

public class HasFileValidator implements ConstraintValidator<HasFile, MultipartFile> {

    private HasFile annotation;

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if(value != null && !value.isEmpty())
            return true;

        throw new MultipartException("파일이 존재하지 않습니다.");
    }
}
