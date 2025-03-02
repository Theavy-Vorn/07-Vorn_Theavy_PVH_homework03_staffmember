abstract class StaffMember {
    private static int idCounter = 1; // Static counter for auto-increment
    private int id;
    private String name;
    private String address;

    public StaffMember(String name, String address) {
        this.id = idCounter++; // Assign the current counter value, then increment
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public abstract double pay();
}
