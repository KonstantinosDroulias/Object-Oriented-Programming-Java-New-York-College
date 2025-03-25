package weeksInClassLessons.week04.payrollSystemV3;

public class Manager extends Employee {
    protected double salary;
    protected double bonus;

    public Manager(int ssn, String name, String email, double salary, double bonus) {
        super(ssn, name, email);
        this.salary = salary;
        this.bonus = bonus;
    }

    public double calculatePay() {
        return salary + bonus;
    }

    public String toString() {
        return super.toString() + "Manager, pay: " + calculatePay();
    }
}
