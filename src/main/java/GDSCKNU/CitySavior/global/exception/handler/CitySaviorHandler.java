package GDSCKNU.CitySavior.global.exception.handler;

import GDSCKNU.CitySavior.global.exception.error.GlobalError;
import GDSCKNU.CitySavior.global.exception.model.ExceptionBase;
import GDSCKNU.CitySavior.global.response.model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CitySaviorHandler {

    @ExceptionHandler(ExceptionBase.class)
    protected ResponseEntity<ApiResponse<Void>> handleCitySaviorException(HttpServletRequest request,
                                                                          final ExceptionBase e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ApiResponse.error(e.getErrorEnumBase(), e.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ApiResponse<Void>> handleNullPointerException(HttpServletRequest request,
                                                                           final NullPointerException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(GlobalError.REQUEST_NOT_FOUND, GlobalError.REQUEST_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<ApiResponse<Void>> handleSQLException(HttpServletRequest request, final SQLException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(GlobalError.REQUEST_NOT_FOUND,
                        GlobalError.INTERNAL_DATABASE_ERROR.getMessage()));
    }
}
