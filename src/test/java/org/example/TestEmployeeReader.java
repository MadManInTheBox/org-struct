package org.example;

import org.example.model.Employee;

import java.util.List;

public class TestEmployeeReader implements EmployeeReader {
    @Override
    public List<Employee> readEmployees() {
        return List.of(
                new Employee("123", "Joe", "Doe", 1199.0, null),
                new Employee("124", "Martin", "Chekov", 1000.0, "123"),
                new Employee("300", "Alice", "Hasacat", 500.0, "124")
        );
    }
}
