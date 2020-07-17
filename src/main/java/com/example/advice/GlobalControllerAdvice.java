package com.example.advice;

import com.example.exception.BusinessException;
import com.example.exception.http.HttpException;
import com.example.exception.http.InternalServerErrorException;
import com.example.exception.http.UnauthorizedException;
import com.example.util.DatePattern;
import com.example.util.Result;
import com.example.util.propertyeditors.CustomLocalDateEditor;
import com.example.util.propertyeditors.CustomLocalDateTimeEditor;
import com.example.util.propertyeditors.CustomLocalTimeEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestControllerAdvice
public class GlobalControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler
    public Result catchInternalServerErrorException(InternalServerErrorException e) {
        logger.debug("", e);
        return Result.buildFailure(e.getHttpStatus().value(), e.getMessage());
    }

    @ExceptionHandler
    public void catchUnauthorizedException(UnauthorizedException e, HttpServletResponse response) {
        logger.debug("Unauthorized: {}", e.getMessage());
        if (e.getMessage() == null) {
            response.setStatus(e.getHttpStatus().value());
            return;
        }
        try {
            response.sendError(e.getHttpStatus().value(), e.getMessage());
        } catch (IOException ignored) {
            // ignored
        }
    }

    @ExceptionHandler
    public void catchHttpException(HttpException e, HttpServletResponse response) {
        logger.debug("", e);
        if (e.getMessage() == null) {
            response.setStatus(e.getHttpStatus().value());
            return;
        }
        try {
            response.sendError(e.getHttpStatus().value(), e.getMessage());
        } catch (IOException ignored) {
            // ignored
        }
    }

    @ExceptionHandler
    public Result catchNotValidException(MethodArgumentNotValidException e) {
        FieldError error = e.getBindingResult().getFieldError();
        return Result.buildFailure(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error != null ? error.getDefaultMessage() : e.getMessage());
    }

    @ExceptionHandler
    public Result catchNotBindException(BindException e) {
        FieldError error = e.getBindingResult().getFieldError();
        return Result.buildFailure(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error != null ? error.getDefaultMessage() : e.getMessage());
    }

    @ExceptionHandler
    public Result catchBusinessException(BusinessException e) {
        logger.error("", e);
        return Result.buildFailure(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage());
    }

    @ExceptionHandler
    public Result catchAllException(Exception e) {
        logger.error("", e);
        return Result.buildFailure(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Convert string date parameters into LocalDateTime, LocalDate, LocalTime
        binder.registerCustomEditor(LocalDateTime.class, new CustomLocalDateTimeEditor(DatePattern.DATE_TIME));
        binder.registerCustomEditor(LocalDate.class, new CustomLocalDateEditor(DatePattern.DATE));
        binder.registerCustomEditor(LocalTime.class, new CustomLocalTimeEditor(DatePattern.TIME));
    }

}
