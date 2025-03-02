import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.Scanner;
import static java.awt.Color.BLUE;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<StaffMember> staff = new ArrayList<>();
    public static void main(String[] args) {
        char opt;
        do{
            System.out.println("============================ Choose One Option ====================================");
            System.out.println("A. Insert Employee");
            System.out.println("B. Display Employee");
            System.out.println("C. Update Employee Information");
            System.out.println("D. Remove Employee");
            System.out.println("E. Exit");

            System.out.print("Choose one options : ");
            opt = sc.next().toUpperCase().charAt(0);

            switch (opt) {
                case 'A':
                    insertEmployee();
                    break;
                case 'B':
                    displayEmployees();
                    break;
                case 'C':
                    updateEmployee();
                    break;
                case 'D':
                    removeEmployee();
                    break;
                case 'E':
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option ! Please input letter (A-E).");
            }
        }while(opt != 'E');
        sc.close();
    }

    private static void insertEmployee() {
        System.out.println("================================ Insert Employee ================================");

        // Employee Type Selection
        System.out.println("Select Employee Type:");
        System.out.println("1. Volunteer");
        System.out.println("2. Salaried Employee");
        System.out.println("3. Hourly Employee");
        System.out.print("Enter your choice (1-3): ");
        int choice =0;
        try{
           choice = sc.nextInt();
        }catch(Exception e){
            System.out.println("Invalid input! Please enter a number (1-3).");
            sc.nextLine();
            return;

        }

        sc.nextLine();

        String name;
        while (true) {
            System.out.print("Enter Employee Name: ");
            name = sc.nextLine();
            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid name! Please input letters only.");
            } else {
                break;
            }
        }

        System.out.print("Enter Employee Address: ");
        String address = sc.nextLine();


        switch (choice) {
            case 1:
                staff.add(new Volunteer(name, address,0));
                break;

            case 2:
                System.out.print("Enter Salary: ");
                double salary = 0;
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
                    System.out.println("Invalid input! Please enter numeber.");
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
        if (staff.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("============================= Display Employees ===============================");

        CellStyle centerAlign = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(8, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);

        // Set column widths
        table.setColumnWidth(0, 5, 10);  // ID
        table.setColumnWidth(1, 10, 15); // Name
        table.setColumnWidth(2, 10, 15); // Address
        table.setColumnWidth(3, 10, 15); // Salary
        table.setColumnWidth(4, 10, 15); // Bonus
        table.setColumnWidth(5, 10, 15); // Rate
        table.setColumnWidth(6, 10, 15); // Hours
        table.setColumnWidth(7, 10, 15); // Payment


        String BLUE_TEXT = "\u001B[34m";
        String RESET = "\u001B[0m";


        table.addCell(BLUE_TEXT + "ID" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Name" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Address" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Salary" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Bonus" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Rate" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Hours" + RESET, centerAlign);
        table.addCell(BLUE_TEXT + "Payment" + RESET, centerAlign);


        for (StaffMember employee:staff) {
            table.addCell(String.valueOf(employee.getId()), centerAlign); // ID
            table.addCell(employee.getName(), centerAlign);
            table.addCell(employee.getAddress(), centerAlign);


            String salary = "-";
            String bonus = "-";
            String rate = "-";
            String hours = "-";
            String payment = String.valueOf(employee.pay()); // Always display payment

            // Handle different employee types
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

    }

    private static void removeEmployee() {}


}