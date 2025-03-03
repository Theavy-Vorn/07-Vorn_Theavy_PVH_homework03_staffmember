public class Volunteer extends StaffMember {
    private double salary;
    private String type="Volunteer";
    public Volunteer(String name, String address, double salary) {
        super(name, address);
        this.salary = salary;
    }
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double pay(){
        return salary;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "salary=" + salary +
                '}';
    }
}
