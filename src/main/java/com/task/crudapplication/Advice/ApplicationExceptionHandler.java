package com.task.crudapplication.Advice;


import com.task.crudapplication.Exceptions.BookNotFound;
import com.task.crudapplication.Exceptions.MembershipNotFound;
import com.task.crudapplication.Exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@ResponseStatus(HttpStatus.BAD_REQUEST)
@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, String> errMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errMap.put(error.getField(), error.getDefaultMessage());
        });

        return errMap;
    }
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFound.class)
    public Map<String, String> handleBusinessException(UserNotFound ex) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("Error message", ex.getMessage());
        return errMap;
    }
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MembershipNotFound.class)
    public Map<String, String> handleBusinessException(MembershipNotFound ex) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("Error message", ex.getMessage());
        return errMap;
    }
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BookNotFound.class)
    public Map<String, String> handleBusinessException(BookNotFound ex) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("Error message", ex.getMessage());
        return errMap;
    }


}
