package Employees.book;


import java.util.Objects;

public class Employee {
        private final String firstName;
        private final String lastName;
        private int department;
        private int salary;

        public Employee(String firstName, String lastName, int department, int salary) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.department = department;
            this.salary = salary;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getDepartment() {
            return department;
        }

        public int getSalary() {
            return salary;
        }

        public void setDepartment(int department) {
            this.department = department;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("First name: %s, Last name: %s", firstName, lastName);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Employee)) return false;

            Employee employee = (Employee) o;

            if (!Objects.equals(firstName, employee.firstName)) return false;
            return Objects.equals(lastName, employee.lastName);
        }

        @Override
        public int hashCode() {
            int result = firstName != null ? firstName.hashCode() : 0;
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            return result;
        }
    }

