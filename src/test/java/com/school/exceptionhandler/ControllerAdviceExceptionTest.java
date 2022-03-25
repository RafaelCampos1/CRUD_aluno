package com.school.exceptionhandler;

import com.school.enums.ErrorDescription;
import com.school.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.validation.BindingResultUtils.getBindingResult;
@Slf4j
class ControllerAdviceExceptionTest {

    @InjectMocks
    ControllerAdviceException controllerAdviceException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void resourceAlreadyExists() {
        ResponseEntity<MessageExceptionHandler> messageExceptionSameCpf = controllerAdviceException.resourceAlreadyExists(
                new ConflictException(ErrorDescription.SAME_CPF)
        );
        ResponseEntity<MessageExceptionHandler> messageExceptionSameEmail = controllerAdviceException.resourceAlreadyExists(
                new ConflictException(ErrorDescription.SAME_EMAIL)
        );
        assertNotNull(messageExceptionSameCpf);
        assertNotNull(messageExceptionSameEmail);
    }

    @Test
    void entityNotFound() {
        ResponseEntity<MessageExceptionHandler> messageException = controllerAdviceException.entityNotFound(
                new NotFoundException(ErrorDescription.STUDENT_NOT_FOUND)
        );
        assertNotNull(messageException);
    }

    @Test
    void argumentsNotValid() {
        Student student = new Student();
        BindingResult result = new BeanPropertyBindingResult(student, "student");
        FieldError error = new FieldError("student","fistName","cant be null");
        result.addError(error);
        ResponseEntity<MessageExceptionHandler> restErrorResponse = controllerAdviceException.argumentsNotValid(new MethodArgumentNotValidException(mock(MethodParameter.class),result));
        assertNotNull(restErrorResponse);
        assertEquals(restErrorResponse.getStatusCode(),HttpStatus.BAD_REQUEST);
    }


}