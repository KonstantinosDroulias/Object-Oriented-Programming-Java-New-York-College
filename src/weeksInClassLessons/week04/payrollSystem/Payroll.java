package weeksInClassLessons.week04.payrollSystem;

public class Payroll {
    public static void main(String[] args) {
        EmployeeList employees = new EmployeeList();
        employees.loadEmployees();
        employees.printPayroll();
    }
}
