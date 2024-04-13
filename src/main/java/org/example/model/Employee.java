package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private Double salary;
    private final String managerId;
    private List<Employee> subordinates = new ArrayList<>();

    public Employee(String id, String firstName, String lastName, Double salary, String managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getSalary() {
        return salary;
    }

    public String getManagerId() {
        return managerId;
    }

    public List<Employee> getSubordinates() {
        return List.copyOf(subordinates);
    }

    public void addSubordinate(Employee subordinate) {
        this.subordinates.add(subordinate);
    }
}
