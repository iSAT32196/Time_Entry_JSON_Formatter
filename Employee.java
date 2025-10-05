import java.util.Scanner;

public class Employee {
    private String employeeName;
    private int employeeID;
    private String contactInfo;


    public Employee(){
        employeeName = " ";
        employeeID = -1;
        contactInfo = " ";
    }

    public void setName(Scanner scnr){
        System.out.print("Please input your full name: ");
        employeeName = scnr.nextLine();
    }

    public void setEmployeeID(Scanner scnr){
        System.out.print("Please input your employeeID: ");
        employeeID = scnr.nextInt();
        scnr.nextLine();
    }

    public void setContactInfo(Scanner scnr){
        System.out.print("Please input contact info in case we need to contact you (email or phone number): ");
        contactInfo = scnr.nextLine();
        System.out.println();
    }

    public String getEmployeeName(){
        return employeeName;
    }

    public int getEmployeeID(){
        return employeeID;
    }

    public String getContactInfo(){
        return contactInfo;
    }
    


}
