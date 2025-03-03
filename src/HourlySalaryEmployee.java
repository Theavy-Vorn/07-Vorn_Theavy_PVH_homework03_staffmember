public class HourlySalaryEmployee extends StaffMember{
    private int hourWorked;
    private double rate;
    private String  type="HourlySalaryEmployee";

    public HourlySalaryEmployee(String name, String address, double rate, int hourWorked) {
        super(name, address);
        this.rate = rate;
        this.hourWorked = hourWorked;
    }

    public int getHourWorked() {
        return hourWorked;
    }

    public void setHourWorked(int hourWorked) {
        this.hourWorked = hourWorked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public double pay() {
        return hourWorked * rate;
    }

    @Override
    public String toString() {
        return "HourlySalaryEmployee{" +
                "hourWorked=" + hourWorked +
                ", rate=" + rate +
                '}';
    }
}
