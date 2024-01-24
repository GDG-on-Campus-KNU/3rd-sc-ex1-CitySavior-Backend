package GDSCKNU.CitySavior.validator;


import static org.assertj.core.api.Assertions.*;

import GDSCKNU.CitySavior.dto.ReportRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryValidatorTest {

    private static Validator validator;

    @BeforeEach
    void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Category에 없는 값을 입력했을 때 검증에 실패한다.")
    public void CategoryTest() throws Exception {
        //given
        ReportRequestDto reportRequestDto = new ReportRequestDto(
                12.123,
                12.123,
                "testDescription",
                "invalidCategory",
                5);

        //when
        Set<ConstraintViolation<ReportRequestDto>> violations = validator.validate(reportRequestDto);

        //then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations).extracting("message").containsOnly("올바른 카테고리가 아닙니다.");
    }
}