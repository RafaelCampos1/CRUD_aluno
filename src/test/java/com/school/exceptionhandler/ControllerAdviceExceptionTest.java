package com.school.exceptionhandler;

import com.school.enums.ErrorDescription;
import com.school.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
@Slf4j
class ControllerAdviceExceptionTest {

    @InjectMocks
    ControllerAdviceException controllerAdviceException;

    private MessageExceptionHandler messageExceptionHandler;

    @Mock
    private BindingResult bindingResult;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        messageExceptionHandler = new MessageExceptionHandler(new Date(), 400, "not found");
    }

    @Test
    void testAttributesOnMessageHandler(){
        assertNotNull(messageExceptionHandler.getMessage());
        assertNotNull(messageExceptionHandler.getStatus());
        assertNotNull(messageExceptionHandler.getTimestamp());
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