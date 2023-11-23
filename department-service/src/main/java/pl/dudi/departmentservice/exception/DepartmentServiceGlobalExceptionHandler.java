package pl.dudi.departmentservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DepartmentServiceGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepartmentCodeAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> handleDepartmentCodeAlreadyExistException(
        DepartmentCodeAlreadyExistException exception,
        WebRequest webRequest
    ) {
        String errorMessage = "EMAIL_ALREADY_EXIST";
        ErrorDetails errorDetails = createErrorDetails(exception, webRequest,errorMessage);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFoundException(
        DepartmentNotFoundException exception,
        WebRequest webRequest
    ) {
        String errorMessage = "EMPLOYEE_NOT_FOUND";
        ErrorDetails errorDetails = createErrorDetails(exception, webRequest,errorMessage);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(
            fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalEmployeeServiceException(
        Exception exception,
        WebRequest webRequest
    ) {
        String errorMessage = "INTERNAL_SERVER_ERROR";
        ErrorDetails errorDetails = createErrorDetails(exception, webRequest, errorMessage
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDetails createErrorDetails(Exception exception, WebRequest webRequest, String errorMessage) {
        return new ErrorDetails(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            errorMessage
        );
    }
}
