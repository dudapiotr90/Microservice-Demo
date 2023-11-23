package pl.dudi.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentCodeAlreadyExistException extends RuntimeException {
    public DepartmentCodeAlreadyExistException(String message) {
        super(message);
    }
}
