package Employees.book.controller;

import Employees.book.Employee;
import Employees.book.service.DepartmentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}/employees")
    public List<Employee> getEmployees(@PathVariable("id") int departmentId) {
        return service.getEmployees(departmentId);
    }

    @GetMapping(value = "/{id}/salary/sum", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSumSalary(@PathVariable("id") int departmentId) {
        return service.getSumSalary(departmentId);
    }

    @GetMapping(value = "/{id}/salary/max")
    public Employee getEmployeeMaxSalary(@PathVariable("id") int departmentId) {
        return service.getEmployeeMaxSalary(departmentId);
    }

    @GetMapping(value = "/{id}/salary/min")
    public Employee getEmployeeMinSalary(@PathVariable("id") int departmentId) {
        return service.getEmployeeMinSalary(departmentId);
    }

    @GetMapping(value = "/employees")
    public Map<Integer, List<Employee>> getEmployee() {
        return service.getEmployee();
    }

    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler(RuntimeException e) {
        return e.getMessage();
    }
}
