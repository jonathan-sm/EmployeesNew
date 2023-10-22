package Employees.book.service;

import Employees.book.Employee;
import Employees.book.exceptions.EmployeeAlreadyAddedException;
import Employees.book.exceptions.EmployeeNotFoundException;
import Employees.book.exceptions.EmployeeStorageIsFullException;
import Employees.book.exceptions.EmployeeValidateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
    public class EmployeeServiceImpl implements EmployeeService {
        private final int MAX_EMPLOYEES_IN_COMPANY = 12;
        private final Map<String, Employee> employees = new HashMap<>();

        @Override
        public Employee add(String firstName, String lastName, int departmentId, int salary) {
            if (employees.size() == MAX_EMPLOYEES_IN_COMPANY) {
                throw new EmployeeStorageIsFullException("Превышен лимит сотрудников в фирме");
            }

            validateUserFullname(firstName, lastName);

            String employeeKey = getKey(firstName, lastName);

            if (employees.containsKey(employeeKey)) {
                throw new EmployeeAlreadyAddedException(String.format("Сотрудник \"%s %s\" уже добавлен.", firstName, lastName));
            }

            Employee newEmployee = new Employee(firstName, lastName, departmentId, salary);

            employees.put(employeeKey, newEmployee);

            return newEmployee;
        }

        @Override
        public Employee remove(String firstName, String lastName) {
            validateUserFullname(firstName, lastName);

            String employeeKey = getKey(firstName, lastName);

            if (!employees.containsKey(employeeKey)) {
                throw new EmployeeNotFoundException("Сотрудник в фирме не найден");
            }

            return employees.remove(employeeKey);
        }

        @Override
        public Employee find(String firstName, String lastName) {
            validateUserFullname(firstName, lastName);

            String employeeKey = getKey(firstName, lastName);

            if (!employees.containsKey(employeeKey)) {
                throw new EmployeeNotFoundException("Сотрудник в фирме не найден");
            }

            return employees.get(employeeKey);
        }

        @Override
        public List<Employee> getEmployees() {
            return new ArrayList<>(employees.values());
        }

        private String getKey(String firstName, String lastName) {
            return firstName + lastName;
        }

        private void validateUserFullname(String firstName, String lastName) {
            if (!StringUtils.capitalize(firstName.toLowerCase()).equals(firstName)) {
                throw new EmployeeValidateException("Имя должно начинаться с заглавной буквы, а все остальные со строчной");
            }
            if (!StringUtils.capitalize(lastName.toLowerCase()).equals(lastName)) {
                throw new EmployeeValidateException("Фамилия должна начинаться с заглавной буквы, а все остальные со строчной");
            }
            if (!StringUtils.isAlpha(firstName)) {
                throw new EmployeeValidateException("Имя должно содержать только буквы");
            }
            if (!StringUtils.isAlpha(lastName)) {
                throw new EmployeeValidateException("Фамилия должна содержать только буквы");
            }
        }

        @PostConstruct
        public void init() {
            add("Андрей", "Иванов", 5, 110824);
            add("Павел", "Васильев", 3, 152372);
            add("Яков", "Петров", 5, 159068);
            add("Лев", "Смирнов", 3, 195984);
            add("Федор", "Михайлов", 4, 168573);
            add("Михаил", "Федоров", 4, 186333);
            add("Мирослав", "Соколов", 2, 143059);
            add("Петр", "Яковлев", 1, 133136);
            add("Василий", "Попов", 1, 198743);
            add("Иван", "Андреев", 2, 126535);
        }
}
