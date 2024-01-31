package GDSCKNU.CitySavior.global.response.model;

import GDSCKNU.CitySavior.global.exception.error.ErrorEnumBase;
import GDSCKNU.CitySavior.global.exception.error.SuccessEnumBase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
    private final int code;
    private final String message;
    private T data;

    public static ApiResponse<Void> success(SuccessEnumBase success) {
        return new ApiResponse<>(success.getHttpStatusCode(), success.getMessage());
    }

    public static <T> ApiResponse<T> success(SuccessEnumBase success, T data) {
        return new ApiResponse<T>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    public static ApiResponse<Void> error(ErrorEnumBase error) {
        return new ApiResponse<>(error.getHttpStatusCode(), error.getMessage());
    }

    public static <T> ApiResponse<T> error(ErrorEnumBase error, String message) {
        return new ApiResponse<>(error.getHttpStatusCode(), message);
    }

    public static <T> ApiResponse<T> error(ErrorEnumBase error, String message, T stackTraceElements) {
        return new ApiResponse<>(error.getHttpStatusCode(), message, stackTraceElements);
    }
}