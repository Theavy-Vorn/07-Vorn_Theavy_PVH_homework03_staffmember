public class Volunteer extends StaffMember {
    private double salary;

    public Volunteer(String name, String address, double salary) {
        super(name, address);
        this.salary = salary;
    }
    @Override
    public double pay(){
        return 0;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "salary=" + salary +
                '}';
    }
}
