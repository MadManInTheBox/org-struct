package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeesProcessorTest {

    @Test
    void testTooLittleIncome() {
        var testReportConstructor = new TestReportConstructor();
        var testEmployeeReader = new TestEmployeeReader();

        new EmployeesProcessor(testReportConstructor, testEmployeeReader)
                .init()
                .findEmployeesWhoEarnTooLittle()
                .buildReport();

        var result = testReportConstructor.getResult();
        assertEquals(1, result.size());
        assertEquals("Too little income for employee Joe Doe with id 123. Should earn 1.0 more\n", result.getFirst());
    }

    @Test
    void testTooMuchIncome() {
        var testReportConstructor = new TestReportConstructor();
        var testEmployeeReader = new TestEmployeeReader();

        new EmployeesProcessor(testReportConstructor, testEmployeeReader)
                .init()
                .findEmployeesWhoEarnTooMuch()
                .buildReport();

        var result = testReportConstructor.getResult();
        assertEquals(1, result.size());
        assertEquals("Too much income for employee Martin Chekov with id 124. Should earn 250.0 less\n", result.getFirst());
    }
}