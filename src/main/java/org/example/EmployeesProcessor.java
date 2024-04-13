package org.example;

import org.example.model.Employee;
import org.example.report.AbstractReportConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeesProcessor {
    private final Integer MAX_REPORTING_LINE_SIZE = 4;
    private final Double MAX_SALARY_THRESHOLD = 1.5;
    private final Double MIN_SALARY_THRESHOLD = 1.2;

    private final AbstractReportConstructor reportConstructor;
    private final EmployeeReader employeeReader;
    private Map<String, Employee> employeeMap = new HashMap<>();

    public EmployeesProcessor(AbstractReportConstructor reportConstructor, EmployeeReader employeeReader) {
        this.reportConstructor = reportConstructor;
        this.employeeReader = employeeReader;
    }

    public EmployeesProcessor init() {
        readEmployees();
        initSubordinates();

        return this;
    }

    public EmployeesProcessor findEmployeesWhoEarnTooLittle() {
        employeeMap.values().forEach(employee -> {
            var salary = employee.getSalary();
            var subordinatesAvgSalary = employee.getSubordinates().stream().mapToDouble(Employee::getSalary).average();
            if (subordinatesAvgSalary.isEmpty()) {
                return;
            }
            var expectedSalary = subordinatesAvgSalary.getAsDouble() * MIN_SALARY_THRESHOLD;
            if (salary < expectedSalary) {
                reportConstructor.appendTooLittleIncome(employee, expectedSalary - salary);
            }
        });

        return this;
    }

    public EmployeesProcessor findEmployeesWhoEarnTooMuch() {
        employeeMap.values().forEach(employee -> {
            var salary = employee.getSalary();
            var subordinatesAvgSalary = employee.getSubordinates().stream().mapToDouble(Employee::getSalary).average();
            if (subordinatesAvgSalary.isEmpty()) {
                return;
            }
            var expectedSalary = subordinatesAvgSalary.getAsDouble() * MAX_SALARY_THRESHOLD;
            if (salary > expectedSalary) {
                reportConstructor.appendTooMuchIncome(employee, salary - expectedSalary);
            }
        });

        return this;
    }

    public EmployeesProcessor findLongReportingLines() {
        employeeMap.values().forEach(employee -> {
            var reportingLineLength = 0;
            var currentEmployee = employee;
            while (currentEmployee.getManagerId() != null) {
                currentEmployee = employeeMap.get(currentEmployee.getManagerId());
                reportingLineLength++;
            }
            if (reportingLineLength > MAX_REPORTING_LINE_SIZE) {
                reportConstructor.appendTooLongReportingLine(employee, reportingLineLength - MAX_REPORTING_LINE_SIZE);
            }
        });

        return this;
    }

    public void buildReport() {
        this.reportConstructor.construct();
    }

    private void readEmployees() {
        employeeMap = employeeReader.readEmployees().stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
    }

    private void initSubordinates() {
        employeeMap.values().forEach(employee -> {
            var managerId = employee.getManagerId();
            if (managerId == null) {
                return;
            }
            employeeMap.get(managerId).addSubordinate(employee);
        });
    }
}
