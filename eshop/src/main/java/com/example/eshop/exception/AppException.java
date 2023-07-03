package com.example.eshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class AppException {

    @ExceptionHandler(value={NoHandlerFoundException.class})
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    public ApiError badRequest(Exception e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        return new ApiError(400, HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @ExceptionHandler(value={NotFoundException.class})
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public ApiError notFound(Exception e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        return new ApiError(404, HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    @ExceptionHandler(value={WrongStatusException.class, UnprocessableEntityException.class})
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiError wrongStatus(Exception e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        return new ApiError(422, HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
    }
}
