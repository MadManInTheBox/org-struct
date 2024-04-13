package org.example;

import org.example.report.ConsoleReportConstructor;
import org.example.report.AbstractReportConstructor;

public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No CSV-file path was provided");
            System.exit(1);
        }

        AbstractReportConstructor reportConstructor = new ConsoleReportConstructor();
        EmployeeReader employeeReader = new CsvEmployeeReader(args[0]);

        new EmployeesProcessor(reportConstructor, employeeReader)
                .init()
                .findEmployeesWhoEarnTooLittle()
                .findEmployeesWhoEarnTooMuch()
                .findLongReportingLines()
                .buildReport();
    }
}
