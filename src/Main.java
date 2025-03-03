import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import static java.awt.Color.BLUE;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<StaffMember> staff = new ArrayList<>();
    public static void main(String[] args) {
        staff.add(new Volunteer("Theavy", "PP",0));
        staff.add(new SalariedEmployee("Maraka", "Kandal", 10000, 20));
        staff.add(new HourlySalaryEmployee("Theary", "Takeo", 50, 10));
        int opt;
        do{
            System.out.println("============================ Choose One Option ====================================");
            System.out.println("1. Insert Employee");
            System.out.println("2. Display Employee");
            System.out.println("3. Update Employee Information");
            System.out.println("4. Remove Employee");
            System.out.println("5. Exit");

            System.out.print("Choose one options : ");
            opt = sc.nextInt();

            switch (opt) {
                case 1:
                    insertEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    removeEmployee();
                    break;
                case 5:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option ! Please input number (1-5).");
            }
        }while(opt != 5);
        sc.close();
    }

    private static void insertEmployee() {

        System.out.println("================================ Insert Employee ================================");

        System.out.println("Select Employee Type:");
        System.out.println("1. Volunteer");
        System.out.println("2. Salaried Employee");
        System.out.println("3. Hourly Employee");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
        int choice =0;
        try{
           choice = sc.nextInt();
        }catch(Exception e){
            System.out.println("Invalid input! Please enter a number (1-4).");
            sc.nextLine();
            return;
        }
        sc.nextLine();
        String name, address;
        double salary = 0;

        try {
            System.out.print("Enter Employee Name: ");
            name = sc.nextLine();
        }catch (Exception e){
            System.out.println("Invalid input! Please enter letter.");
            return;
        }

        try {
            System.out.print("Enter Employee Address: ");
            address = sc.nextLine();
        }catch (Exception e){
            System.out.println("Invalid input! Please enter letter.");
            return;
        }

        switch (choice) {
            case 1:

                try {
                    System.out.print("Enter Employee Salary: ");
                    salary = sc.nextDouble();
                }
                catch (Exception e){
                    System.out.println("Invalid input! Please enter letter.");
                }

                staff.add(new Volunteer(name, address,salary));
                break;
            case 2:
                System.out.print("Enter Salary: ");
                double bonus = 0;
                try {
                    salary = sc.nextDouble();
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a numeric value for Salary.");
                    sc.nextLine();
                    return;
                }
                System.out.print("Enter Bonus: ");
                try {
                    bonus = sc.nextDouble();

                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine();
                    return;
                }
                sc.nextLine();

                staff.add(new SalariedEmployee(name, address, salary, bonus));
                System.out.println("Employee added successfully!");
                break;

            case 3:
                double rate = 0;
                int hours = 0;
                try{
                    System.out.print("Enter Hourly Rate: ");
                    rate = sc.nextDouble();
                    System.out.print("Enter Hours Worked: ");
                    hours = sc.nextInt();
                }catch(Exception e){
                    System.out.println("Invalid input! Please enter number.");
                    sc.nextLine();
                    return;
                }
                sc.nextLine();
                staff.add(new HourlySalaryEmployee(name, address, rate, hours));
                break;

            default:
                System.out.println("Invalid choice! Employee not added.");
                return;
        }

        System.out.println("Employee added successfully!");
    }

    private static void displayEmployees() {

        System.out.println("============================= Display Employees ===============================");

        CellStyle centerAlign = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(9, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        table.setColumnWidth(0, 30, 30);
        table.setColumnWidth(1, 5, 10);
        table.setColumnWidth(2, 10, 15);
        table.setColumnWidth(3, 20, 20);
        table.setColumnWidth(4, 10, 15);
        table.setColumnWidth(5, 10, 15);
        table.setColumnWidth(6, 10, 15);
        table.setColumnWidth(7, 10, 15);
        table.setColumnWidth(8, 10, 15);


        String BLUE_TEXT = "\u001B[34m";
        String RESET = "\u001B[0m";

        table.addCell(BLUE_TEXT + "Type" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "ID" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Name" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Address" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Salary" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Bonus" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Rate" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Hours" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Payment" + RESET, centerAlign);



        for (StaffMember employee : staff) {
            String type = "-";

            if (employee instanceof SalariedEmployee) {
                type = "Salaried Employee";
            } else if (employee instanceof HourlySalaryEmployee) {
                type = "Hourly Employee";
            } else if (employee instanceof Volunteer) {
                type = "Volunteer";
            }

            table.addCell(type, centerAlign);
            table.addCell(String.valueOf(employee.getId()), centerAlign);
            table.addCell(employee.getName(), centerAlign);
            table.addCell(employee.getAddress(), centerAlign);

            String salary = "-";
            String bonus = "-";
            String rate = "-";
            String hours = "-";
            String payment = String.valueOf(employee.pay());

            if (employee instanceof SalariedEmployee) {
                SalariedEmployee sa = (SalariedEmployee) employee;
                salary = String.valueOf(sa.getSalary());
                bonus = String.valueOf(sa.getBonus());
            } else if (employee instanceof HourlySalaryEmployee) {
                HourlySalaryEmployee ho = (HourlySalaryEmployee) employee;
                rate = String.valueOf(ho.getRate());
                hours = String.valueOf(ho.getHourWorked());
            }

            table.addCell(salary, centerAlign);
            table.addCell(bonus, centerAlign);
            table.addCell(rate, centerAlign);
            table.addCell(hours, centerAlign);
            table.addCell(payment, centerAlign);
        }
        System.out.println(table.render());
    }



    private static void updateEmployee() {
        System.out.println("============================ Update Employee ===============================");
//        System.out.print("Enter Employee ID to update: ");
//        int idup = sc.nextInt();
//        sc.nextLine();
//        boolean found = false;
//        for (StaffMember employee : staff) {
//            if (employee.getId() == idup) {
//                System.out.println("Enter name : ");
//                String name = sc.nextLine();
//                System.out.println("Enter address : ");
//                String address = sc.nextLine();
//                System.out.println("Enter salary : ");
//                double salary = sc.nextDouble();
//            }
//        }
//
//        if (!found) {
//            System.out.println("Employee ID not found!\n");
//        }


        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Optional<StaffMember> employee = staff.stream()
                .filter(s -> s.getId() == id)
                .findFirst();

        if (employee.isEmpty()) {
            System.out.println("Employee Not Found!");
            return;
        }

        StaffMember staff = employee.get();

        System.out.println("\t1. Name \t2. Address");
        if (staff instanceof SalariedEmployee) {
            System.out.println("\t3. Salary");
            System.out.println("\t4. Bonus");
        } else if (staff instanceof HourlySalaryEmployee) {
            System.out.println("\t3. Hours Worked");
            System.out.println("\t4. Rate Worked");
        }
        System.out.println("\t0. Cancel");

        System.out.print("Choose column you want to update: ");
        int col = sc.nextInt();
        sc.nextLine();

        switch (col) {
            case 1:
                System.out.print("Enter New Name: ");
                String newName = sc.nextLine();
                staff.setName(newName);
                System.out.println("Name updated successfully!");
                break;

            case 2:
                System.out.print("Enter New Address: ");
                String newAddress = sc.nextLine();
                staff.setAddress(newAddress);
                System.out.println("Address updated successfully!");
                break;

            case 3:
                if (staff instanceof SalariedEmployee salariedEmployee) {
                    System.out.print("Enter New Salary: ");
                    double newSalary = sc.nextDouble();
                    salariedEmployee.setSalary(newSalary);
                    System.out.println("Salary updated successfully!");
                } else {
                    System.out.println("Invalid option! This employee does not have a salary.");
                }
                break;

            case 4:
                if (staff instanceof HourlySalaryEmployee hourlyEmployee) {
                    System.out.print("Enter New Hours Worked: ");
                    int newHours = sc.nextInt();
                    hourlyEmployee.setHourWorked(newHours);
                    System.out.println("Hours worked updated successfully!");
                } else {
                    System.out.println("Invalid option! This employee does not have hourly work.");
                }
                break;

            case 0:
                System.out.println("Operation cancelled!");
                break;

            default:
                System.out.println("Invalid option! Please choose a valid number.");
        }

    }



    private static void removeEmployee() {
        System.out.println("============================ Remove Employee ===============================");
        System.out.print("Enter Employee ID to remove: ");

        int id = sc.nextInt();
        sc.nextLine();

        Optional<StaffMember> employeeToRemove = staff.stream()
                .filter(s -> s.getId() == id)
                .findFirst();

        if (employeeToRemove.isPresent()) {
            staff.remove(employeeToRemove.get());
            System.out.println("Employee removed successfully!");
        } else {
            System.out.println("Employee Not Found!");
        }
    }


}