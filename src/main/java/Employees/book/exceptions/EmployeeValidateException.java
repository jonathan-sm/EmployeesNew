package Employees.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeValidateException extends HttpStatusCodeException {
    public EmployeeValidateException(String statusText) {
        super(HttpStatus.BAD_REQUEST, statusText);
    }
}
