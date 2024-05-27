package jpaShop.shop.global.apiException;

import jpaShop.shop.global.apiException.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleException(final Exception exception) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("INTERNAL_SERVER_ERROR") // 예외 코드
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()) // HTTP 상태 코드
                .message(exception.getMessage()) // 예외 메시지
                .build();

        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    ResponseEntity<ErrorResponse> handleRequestParameterException(MissingServletRequestParameterException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("MISSING_PARAMETER_ERROR_CODE") // 예외 코드
                .status(HttpStatus.BAD_REQUEST.value()) // HTTP 상태 코드
                .message("요청 파라미터가 누락되었습니다.") // 예외 메시지
                .build();

        return createErrorResponse(HttpStatus.BAD_REQUEST, errorResponse);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, ErrorResponse errorResponse) {
        return ResponseEntity.status(status).body(errorResponse);
    }

}
