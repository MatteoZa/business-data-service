package com.aerztekasse.business.data.service.controller.mapper;

import com.aerztekasse.business.data.service.controller.model.Error;
import com.aerztekasse.business.data.service.exception.PlaceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler({PlaceNotFoundException.class})
    public ResponseEntity handlePlaceNotFoundException(PlaceNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Error.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(exception.getLocalizedMessage())
                        .build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleRuntimeException(RuntimeException exception) {
        log.error(exception.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Error.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Something went wrong")
                        .build());
    }

}
