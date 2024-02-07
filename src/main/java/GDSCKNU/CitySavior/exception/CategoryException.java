package GDSCKNU.CitySavior.exception;

import GDSCKNU.CitySavior.domain.Category;
import GDSCKNU.CitySavior.exception.error.CategoryError;
import GDSCKNU.CitySavior.exception.error.MemberError;
import GDSCKNU.CitySavior.global.exception.model.ExceptionBase;

public class CategoryException extends ExceptionBase {

    public CategoryException(CategoryError categoryError) {
        super(categoryError, categoryError.getMessage());
    }
}
