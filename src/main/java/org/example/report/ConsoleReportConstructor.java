package org.example.report;

public class ConsoleReportConstructor extends AbstractReportConstructor {

    @Override
    public void construct() {
        reportEntries.forEach(reportEntry -> {
            System.out.printf(reportEntry.message(),
                    reportEntry.employee().getFirstName() + " " + reportEntry.employee().getLastName(),
                    reportEntry.employee().getId(),
                    reportEntry.delta());
        });
    }
}
