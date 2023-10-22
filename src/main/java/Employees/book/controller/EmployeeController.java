package Employees.book.controller;

import Employees.book.Employee;
import Employees.book.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName, @RequestParam(name = "departmentId") int departmentId, @RequestParam(name = "salary") int salary) {
        return employeeService.add(firstName, lastName, departmentId, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping({"/get-employees", "/"})
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler(RuntimeException e) {
        return e.getMessage();
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public String httpExceptionHandler(HttpStatusCodeException e) {
        return String.format("Code: %s, Exception: %s", e.getStatusCode(), e.getMessage());
    }
}
