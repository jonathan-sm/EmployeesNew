package Employees.book.service;

import Employees.book.Employee;

import java.util.List;
import java.util.Map;

public  interface DepartmentService {
    Employee getEmployeeMaxSalary(int departmentId);
    Employee getEmployeeMinSalary(int departmentId);
    List<Employee> getEmployees(int departmentId);
    Map<Integer, List<Employee>> getEmployee();
    String getSumSalary(int departmentId);
}
