package Employees.book.utils;

import Employees.book.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeGenerator {
    public static Employee getFirstEmployee() {
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 55000;
        int departmentId = 1;

        return new Employee(firstName, lastName, departmentId, salary);
    }

    public static Employee getSecondEmployee() {
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 125000;
        int departmentId = 1;

        return new Employee(firstName, lastName, departmentId, salary);
    }

    public static Employee getThirdEmployee() {
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 255000;
        int departmentId = 2;

        return new Employee(firstName, lastName, departmentId, salary);
    }

    public static List<Employee> getAll() {
        return Arrays.asList(getFirstEmployee(), getSecondEmployee(), getThirdEmployee());
    }
}

