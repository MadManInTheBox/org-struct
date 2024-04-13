package org.example;

import org.example.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvEmployeeReader implements EmployeeReader {
    private static final String DELIMITER = ",";
    private final String path;

    public CsvEmployeeReader(String path) {
        this.path = path;
    }

    @Override
    public List<Employee> readEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                var attributes = line.split(DELIMITER);
                var id = attributes[0];
                var firstName = attributes[1];
                var lastName = attributes[2];
                var salary = Double.parseDouble(attributes[3].trim());
                var managerId = attributes.length > 4 ? attributes[4] : null;
                var employee = new Employee(id, firstName, lastName, salary, managerId);

                employees.add(employee);
            }
        } catch (IOException ioe) {
            System.err.println("Error while parsing CSV file");
        }
        return employees;
    }
}
