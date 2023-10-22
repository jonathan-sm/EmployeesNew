package Employees.book.utils;

import Employees.book.Employee;
import Employees.book.exceptions.EmployeeNotFoundException;
import Employees.book.service.DepartmentServiceImpl;
import Employees.book.service.EmployeeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Employees.book.utils.EmployeeGenerator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void getEmployeeMaxSalaryInDepartment_Successful() {
        //Подготовка данных
        int departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getEmployees()).thenReturn(getAll());
        Employee expectedEmployee = getSecondEmployee();

        //Тест
        Employee actualEmployee = departmentService.getEmployeeMaxSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void getEmployeeMaxSalaryInDepartment_ThrowEmployeeNotFound() {
        //Подготовка данных
        int departmentId = 4;

        //Подготовка ожидаемого результата
        when(employeeService.getEmployees()).thenReturn(getAll());
        String message = "Не удалось найти сотрудника с максимальной зп в отделе ";

        //Тест
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmployeeMaxSalary(departmentId), message);
    }

    @Test
    void getEmployeeMinSalaryInDepartment_Successful() {
        //Подготовка данных
        int departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getEmployees()).thenReturn(getAll());
        Employee expectedEmployee = getFirstEmployee();

        //Тест
        Employee actualEmployee = departmentService.getEmployeeMaxSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void getEmployeeMinSalaryInDepartment_ThrowEmployeeNotFound() {
        //Подготовка данных
        int departmentId = 4;

        //Подготовка ожидаемого результата
        when(employeeService.getEmployees()).thenReturn(getAll());
        String message = "Не удалось найти сотрудника с максимальной зп в отделе ";

        //Тест
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmployeeMaxSalary(departmentId), message);
    }

    @Test
    public void getEmployeesInDepartment_Successful() {
        //Подготовка данных
        int department = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getEmployees()).thenReturn(getAll());
        List<Employee> expectedEmployees = Arrays.asList(getFirstEmployee(), getSecondEmployee());

        //Тест
        List<Employee> actualEmployees = departmentService.getEmployees(department);
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void getAllEmployees_Successful() {
        //Подготовка ожидаемого результата
        when(employeeService.getEmployees()).thenReturn(getAll());
        List<Employee> expectedEmployeesFirstDepartment = Arrays.asList(getFirstEmployee(), getSecondEmployee());
        List<Employee> expectedEmployeesSecondDepartment = List.of(getThirdEmployee());
        Map<Integer, List<Employee>> expectedEmployeesMap = new HashMap<>();
        expectedEmployeesMap.put(1, expectedEmployeesFirstDepartment);
        expectedEmployeesMap.put(2, expectedEmployeesSecondDepartment);

        //Тест
        Map<Integer, List<Employee>> actualEmployeesMap = departmentService.getEmployee();
        assertEquals(expectedEmployeesMap, actualEmployeesMap);
    }

    @Test
    public void getSumSalary_Successful() throws JsonProcessingException {
        //Подготовка данных
        int department = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getEmployees()).thenReturn(getAll());
        int sumSalary = getFirstEmployee().getSalary() + getSecondEmployee().getSalary();

        String expectedValue = new ObjectMapper().writerWithDefaultPrettyPrinter()
                .withRootName("salaryDepartmentSum")
                .writeValueAsString(sumSalary);

        //Тест
        String actualValue = departmentService.getSumSalary(department);
        assertEquals(expectedValue, actualValue);
    }
}