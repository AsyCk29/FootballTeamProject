package com.team.configs;

import com.team.utils.Rest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @RestControllerAdvice anotation u @Valid anotation u ile birlikte kullanılır
 * RestControler metotlara gelen dataların validasyon işlemlerinde oluşabilecek
 * muhtemel exceptionlar yakalanır ve kullanıcıya anlamlı datalar döndürülür.
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return Rest.fail(ex.getFieldErrors(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Exception ex) {
        return Rest.fail(ex.getMessage(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
