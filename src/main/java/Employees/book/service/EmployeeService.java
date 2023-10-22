package Employees.book.service;

import Employees.book.Employee;

import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int departmentId, int salary);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    List<Employee> getEmployees();
}

