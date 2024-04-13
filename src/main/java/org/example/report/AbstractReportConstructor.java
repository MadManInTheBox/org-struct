package org.example.report;

import org.example.model.Employee;
import org.example.model.ReportEntry;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReportConstructor {
    private final String TOO_LITTLE_INCOME = "Too little income for employee %s with id %s. Should earn %s more\n";
    private final String TOO_MUCH_INCOME = "Too much income for employee %s with id %s. Should earn %s less\n";
    private final String TOO_LONG_REPORTING_LINE = "Too long reporting line for %s with id %s. Should be %s less\n";

    protected final List<ReportEntry> reportEntries = new ArrayList<>();

    public void appendTooLittleIncome(Employee employee, Double delta) {
        var reportEntry = new ReportEntry(employee, TOO_LITTLE_INCOME, delta);
        reportEntries.add(reportEntry);
    }

    public void appendTooMuchIncome(Employee employee, Double delta) {
        var reportEntry = new ReportEntry(employee, TOO_MUCH_INCOME, delta);
        reportEntries.add(reportEntry);
    }

    public void appendTooLongReportingLine(Employee employee, Integer delta) {
        var reportEntry = new ReportEntry(employee, TOO_LONG_REPORTING_LINE, delta);
        reportEntries.add(reportEntry);
    }

    public abstract void construct();
}
