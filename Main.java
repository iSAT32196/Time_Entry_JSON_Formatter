import java.util.Scanner;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main{
    static Scanner scnr = new Scanner (System.in);
    static Employee newEmployee;
    private static String startDate;
    private static String endDate;
    private static ArrayList<ArrayList<Time_entry>> currentPayPeriod = new ArrayList<ArrayList<Time_entry>>(); 

    public static void main(String[] args) throws IOException{
        System.out.println("Welcome to this tool to help format entering timesheet info! Please answer the following questions to get started: ");
        System.out.println("******************");

        initialInfo();

        System.out.println("******************");
        System.out.println();
        System.out.println("Thank you " + newEmployee.getEmployeeName() + ", now it's time to fill out what hours you worked, and what type of time they should be reported as. Please answer the questions below: ");
        System.out.println();
        
        System.out.println("******************");
        System.out.println();

        System.out.print("Pleae enter the start date for this pay period (typically a Sunday) (using . or - between numbers): ");
        startDate = scnr.nextLine();

        String newStringStartDate = " ";
        for(int i=0; i < startDate.length(); ++i){
            char current = startDate.charAt(i);
            char forwardSlash = '/';
            char backSlash = '\\';
            
            if((current == forwardSlash) || (current == backSlash)){
                newStringStartDate += '.';
            }

            else{
                newStringStartDate += startDate.charAt(i);
            }
        }
        startDate = newStringStartDate;

        System.out.print("Please enter the end date for this pay period (typically a Saturday)(using . or - between numbers): ");
        endDate = scnr.nextLine();

        String newStringEndDate = " ";
        for(int i=0; i < endDate.length(); ++i){
            char current = endDate.charAt(i);
            char forwardSlash = '/';
            char backSlash = '\\';
            
            if((current == forwardSlash) || (current == backSlash)){
                newStringEndDate += '.';
            }

            else{
                newStringEndDate += endDate.charAt(i);
            }
        }
        endDate = newStringEndDate;
        
        System.out.println();

        char moreDates = 'Y';
        char moreDatesSelection;     

        while(moreDates == 'Y'){
            currentPayPeriod.add(setDateAndHours());
            System.out.println();
            System.out.print("Did you have more dates to add in this pay period? Press Y for yes, or N for no: ");
            moreDatesSelection = scnr.next().charAt(0);
            scnr.nextLine();
            moreDatesSelection = Character.toUpperCase( moreDatesSelection);
            moreDates =  moreDatesSelection;

        }

        System.out.println("******************");
        System.out.println("Next up, writing the reported days and times to a file. One moment please.");
        printFile();
        System.out.println("File should be created in the time_submissions folder. Please make sure to double check the file, and that everything in it is correct, before sending to your manager for approval");
        

    }

    public static void initialInfo(){
        newEmployee = new Employee();
        newEmployee.setName(scnr);
        newEmployee.setEmployeeID(scnr);
        newEmployee.setContactInfo(scnr);

    }
    

    public static ArrayList<Time_entry> setDateAndHours(){
        ArrayList<Time_entry> specificDate = new ArrayList<>();
        Time_entry newDate = new Time_entry();
        
        newDate.setDay(scnr);
        newDate.setHoursAndType(scnr);

        specificDate.add(newDate);

        return specificDate;
    }

    public static void printFile() throws IOException{
        String folder = "time_submissions/";
        int i;
        int j;
        int k;

        FileOutputStream fileOutput = new FileOutputStream(folder + newEmployee.getEmployeeName() + "_" + startDate + " - " + endDate + ".json");
        PrintWriter filePrinter = new PrintWriter(fileOutput);

        filePrinter.println("[");
        filePrinter.println("   {");
        filePrinter.println("       \"Employee Name\": \"" + newEmployee.getEmployeeName() + "\",") ;
        filePrinter.println("       \"Employee ID\": \"" + newEmployee.getEmployeeID() + "\",");
        filePrinter.println("       \"Employee Contact Info\": \"" + newEmployee.getContactInfo() + "\",");
        filePrinter.println("       \"Pay Period Start\": \"" + startDate + "\",");
        filePrinter.println("       \"Pay Period End\": \"" + endDate + "\",");
        filePrinter.println();


        //Trying to put all the same types of times together and then total

        ArrayList<String> typesOfHours = new ArrayList<>();
        ArrayList<Double> totalHours = new ArrayList<>();
        
        for (i = 0; i < currentPayPeriod.size(); ++i) {
            ArrayList<Time_entry> dateEntries = currentPayPeriod.get(i);
        
            for (j = 0; j < dateEntries.size(); ++j) {
                Time_entry entry = dateEntries.get(j);
                ArrayList<Hours_type> hoursList = entry.getHoursAndType();
        
                for (k = 0; k < hoursList.size(); ++k) {
                    Hours_type hoursAndType = hoursList.get(k);
                    String currentType = hoursAndType.getType();
                    double currentHours = hoursAndType.getHours();
        
                    // Check if we've seen this type before
                    int index = typesOfHours.indexOf(currentType);
        
                    if (index == -1) {
                        // Type not yet tracked — add it
                        typesOfHours.add(currentType);
                        totalHours.add(currentHours);
                    } else {
                        // Type already exists — update total
                        double newTotal = totalHours.get(index) + currentHours;
                        totalHours.set(index, newTotal);
                    }
                }
            }
        }

        for (i = 0; i < typesOfHours.size(); ++i) {
            String type = typesOfHours.get(i);
            double total = totalHours.get(i);
            filePrinter.println("       \"" + type + "\": " + total + ",");
        }

        double allHoursTotal = 0.0;
        for (double hours : totalHours) {
            allHoursTotal += hours;
        }

        filePrinter.println("       \"Total Hours\": " + allHoursTotal);


        //End Trying to put all the same types of times together and then total


        
        filePrinter.println("   },");
        filePrinter.println();

        

        for(i = 0; i < currentPayPeriod.size(); ++i){
            ArrayList<Time_entry> dateEntries = currentPayPeriod.get(i);

                for(j = 0; j < dateEntries.size(); j++){
                    Time_entry entry = dateEntries.get(j);
                    filePrinter.println("   {");
                    filePrinter.println("       \"Date\": \"" + entry.getDay() + "\",");

                    ArrayList<Hours_type> hoursList = entry.getHoursAndType();
                    for(k = 0; k < hoursList.size(); k++){
                        Hours_type hoursAndType = hoursList.get(k);
                        filePrinter.print("           \"" + hoursAndType.getType() + "\"" + ": " + hoursAndType.getHours());
                        
                        if((k == (hoursList.size() - 1))){
                            filePrinter.println();
                        }

                        else{
                            filePrinter.println(",");
                        }

                        
                    }

                    if((i == (currentPayPeriod.size() - 1))){
                        filePrinter.println("   }");
                    }

                    else{
                        filePrinter.println("   },");
                    }
                    
                    filePrinter.println();
                }
        }
        filePrinter.println("]");

        filePrinter.close();

    }


}
