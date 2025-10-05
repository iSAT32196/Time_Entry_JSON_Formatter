import java.util.Scanner;

public class Hours_type {
    private double hours;
    private String type;

    public Hours_type(){
        hours = 0.0;
        type = " ";
    }

    public void setHours(Scanner scnr){
        System.out.print("Please input how many hours you worked on this date for one category: ");
        hours = scnr.nextDouble();
        scnr.nextLine();
    }

    public void setTypeTimeUsed(Scanner scnr){
        System.out.print("Please input the category for the time inputted (example: Hours Worked, Hours Worked Over Schedule, Sick, Vacation, Personal, JUSPL, etc.): ");
        type = scnr.nextLine();

    }

    public double getHours(){
        return hours;
    }

    public String getType(){
        return type;
    }

}
