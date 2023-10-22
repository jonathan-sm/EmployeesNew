package Employees.book.service;

import Employees.book.Employee;
import Employees.book.exceptions.EmployeeNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class  DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeMaxSalary(int departmentId) {
        return employeeService.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Не удалось найти сотрудника с максимальной зп в отделе " + departmentId));
    }

    @Override
    public Employee getEmployeeMinSalary(int departmentId) {
        return employeeService.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Не удалось найти сотрудника с минимальной зп в отделе " + departmentId));
    }

    @Override
    public List<Employee> getEmployees(int departmentId) {
        return employeeService.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployee() {
        return employeeService.getEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public String getSumSalary(int departmentId) {
        try {
            int sum = 0;

            for (Employee employee : getEmployees(departmentId)) {
                sum += employee.getSalary();
            }

            return new ObjectMapper().writerWithDefaultPrettyPrinter()
                    .withRootName("salaryDepartmentSum")
                    .writeValueAsString(sum);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
