package Employees.book.utils;

import Employees.book.Employee;
import Employees.book.exceptions.EmployeeAlreadyAddedException;
import Employees.book.exceptions.EmployeeNotFoundException;
import Employees.book.exceptions.EmployeeStorageIsFullException;
import Employees.book.exceptions.EmployeeValidateException;
import Employees.book.service.EmployeeServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTests {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Test
    public void add_Successful() {
        //Подготовка данных
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 55000;
        int departmentId = 1;

        //Подготовка ожидаемого результата
        Employee expectedResult = new Employee(firstName, lastName, departmentId, salary);

        //Тест
        Employee actualEmployee = employeeService.add(firstName, lastName, departmentId, salary);
        Assertions.assertEquals(expectedResult, actualEmployee);
    }

    @Test
    public void add_ThrowStorageIsFull() {
        //Подготовка данных
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 55000;

        //Подготовка ожидаемого результата
        String message = "Превышен лимит сотрудников в фирме";
        int maxEmployees = 12;

        //Тест
        Assertions.assertThrows(EmployeeStorageIsFullException.class, () -> {
            for (int i = 0; i < maxEmployees + 1; i++) {
                employeeService.add(firstName + StringUtils.repeat("a", i), lastName, i % 6, salary);
            }
        }, message);
    }

    @Test
    public void add_ThrowValidation() {
        //Подготовка данных
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 55000;
        int departmentId = 1;

        //Подготовка ожидаемого результата
        String messageFirstNameNotCapitalized = "Имя должно начинаться с заглавной буквы, а все остальные со строчной";
        String messageLastNameNotCapitalized = "Фамилия должна начинаться с заглавной буквы, а все остальные со строчной";
        String messageFirstNameNotContainsOnlyLetters = "Имя должно содержать только буквы";
        String messageLastNameNotContainsOnlyLetters = "Фамилия должна содержать только буквы";

        // Тест
        // Тест исключения при начале имени с маленькой буквы
        Assertions.assertThrows(EmployeeValidateException.class,
                () -> employeeService.add(firstName.toLowerCase(), lastName, departmentId, salary),
                messageFirstNameNotCapitalized);

        // Тест исключения при начале фамилии с маленькой буквы
        Assertions.assertThrows(EmployeeValidateException.class,
                () -> employeeService.add(firstName, lastName.toLowerCase(), departmentId, salary),
                messageLastNameNotCapitalized);

        // Тест исключения при содержании в имени некорректных символов
        Assertions.assertThrows(EmployeeValidateException.class,
                () -> employeeService.add(firstName + 1, lastName, departmentId, salary),
                messageFirstNameNotContainsOnlyLetters);

        // Тест исключения при содержании в фамилии некорректных символов
        Assertions.assertThrows(EmployeeValidateException.class,
                () -> employeeService.add(firstName, lastName + 1, departmentId, salary),
                messageLastNameNotContainsOnlyLetters);
    }

    @Test
    public void add_ThrowAlreadyAdded() {
        //Подготовка данных
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 55000;
        int departmentId = 1;

        //Подготовка ожидаемого результата
        String message = String.format("Сотрудник \"%s %s\" уже добавлен.", firstName, lastName);

        //Тест
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.add(firstName, lastName, departmentId, salary);
            employeeService.add(firstName, lastName, departmentId, salary);
        }, message);
    }

    @Test
    public void remove_Successful() {
        //Подготовка данных
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 55000;
        int departmentId = 1;
        employeeService.add(firstName, lastName, departmentId, salary);

        //Подготовка ожидаемого результата
        Employee expectedResult = new Employee(firstName, lastName, departmentId, salary);

        //Тест
        Employee actualEmployee = employeeService.remove(firstName, lastName);
        Assertions.assertEquals(expectedResult, actualEmployee);
    }

    @Test
    public void remove_ThrowEmployeeNotFound() {
        //Подготовка данных
        String firstName = "Иван";
        String lastName = "Иванов";

        //Подготовка ожидаемого результата
        String message = "Сотрудник в фирме не найден";

        //Тест
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.remove(firstName, lastName),
                message
        );
    }

    @Test
    public void find_Successful() {
        //Подготовка данных
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 55000;
        int departmentId = 1;
        employeeService.add(firstName, lastName, departmentId, salary);

        //Подготовка ожидаемого результата
        Employee expectedResult = new Employee(firstName, lastName, departmentId, salary);

        //Тест
        Employee actualEmployee = employeeService.find(firstName, lastName);
        Assertions.assertEquals(expectedResult, actualEmployee);
    }

    @Test
    public void find_ThrowEmployeeNotFound() {
        //Подготовка данных
        String firstName = "Иван";
        String lastName = "Иванов";

        //Подготовка ожидаемого результата
        String message = "Сотрудник в фирме не найден";

        //Тест
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.find(firstName, lastName),
                message
        );
    }

    @Test
    public void getAll_Successful() {
        //Подготовка данных
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 55000;
        int departmentId = 1;
        employeeService.add(firstName, lastName, departmentId, salary);

        //Подготовка ожидаемого результата
        List<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(new Employee(firstName, lastName, departmentId, salary));

        //Тест
        List<Employee> actualEmployee = employeeService.getEmployees();
        Assertions.assertEquals(expectedResult, actualEmployee);
    }

}
