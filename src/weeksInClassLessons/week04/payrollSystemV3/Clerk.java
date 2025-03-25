package weeksInClassLessons.week04.payrollSystemV3;

public class Clerk extends Employee {
    protected double salary;
    protected int overtimeHours;
    public static double overtimeAmount = 20.0;

    public Clerk(int ssn, String name, String email, double salary, int overtimeHours) {
        super(ssn, name, email);
        this.salary = salary;
        this.overtimeHours = overtimeHours;
    }

    public double calculatePay() {
        return salary + (overtimeHours + overtimeAmount);
    }

    public String toString() {
        return super.toString() + " Clerk " + calculatePay();
    }

}
