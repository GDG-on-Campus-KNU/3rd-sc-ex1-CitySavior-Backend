package GDSCKNU.CitySavior.global.util;

import GDSCKNU.CitySavior.global.response.model.ApiResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseAuth<T> {

    private final ResponseEntity<T> responseEntity;

    public static <T> ResponseEntity<ApiResponse<T>> success(HttpStatus httpStatus, ResponseCookie responseCookie,
                                                             String accessToken,
                                                             ApiResponse<T> apiResponse) {

        return ResponseEntity
                .status(httpStatus)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> logout(HttpStatus httpStatus, ApiResponse<T> apiResponse) {

        return ResponseEntity
                .status(httpStatus)
                .header(HttpHeaders.SET_COOKIE, setBlankResponseCookie().toString())
                .<ApiResponse<T>>body(apiResponse);
    }

    private static ResponseCookie setBlankResponseCookie() {
        return ResponseCookie.from("refresh-token", "")
                .maxAge(0)
                .path("/")
                .build();
    }
}
