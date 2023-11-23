package pl.dudi.departmentservice.exception;

public class DepartmentCodeAlreadyExistException extends RuntimeException {
    public DepartmentCodeAlreadyExistException(String message) {
        super(message);
    }
}
