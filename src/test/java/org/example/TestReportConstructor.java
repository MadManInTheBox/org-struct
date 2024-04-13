package org.example;

import org.example.report.AbstractReportConstructor;

import java.util.List;

public class TestReportConstructor extends AbstractReportConstructor {

    private List<String> result;

    @Override
    public void construct() {
        result = reportEntries.stream().map(reportEntry ->
                        String.format(reportEntry.message(),
                                reportEntry.employee().getFirstName() + " " + reportEntry.employee().getLastName(),
                                reportEntry.employee().getId(),
                                reportEntry.delta()))
                .toList();
    }

    public List<String> getResult() {
        return this.result;
    }
}
