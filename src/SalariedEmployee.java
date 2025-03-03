public class SalariedEmployee extends StaffMember{
    private double salary;
    private double bonus;
    private String type="SalariedEmployee";

    public SalariedEmployee( String name, String address, double salary, double bonus) {
        super(name, address);
        this.salary = salary;
        this.bonus = bonus;
    }

    public double getSalary() {
        return salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double pay() {
        return salary + bonus;
    }

    @Override
    public String toString() {
        return "SalariedEmployee{" +
                "salary=" + salary +
                ", bonus=" + bonus +
                '}';
    }
}
