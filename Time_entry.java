import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Character;

public class Time_entry {
    private String day;
    private ArrayList<Hours_type> hoursArray = new ArrayList<>();

    public Time_entry(){
        day = " ";
    }

    public void setDay(Scanner scnr){
        System.out.print("Please input the date you're reporting time for (using . or - between numbers): ");
        day = scnr.nextLine();

        String newStringDay = " ";
        for(int i=0; i < day.length(); ++i){
            char current = day.charAt(i);
            char forwardSlash = '/';
            char backSlash = '\\';
            
            if((current == forwardSlash) || (current == backSlash)){
                newStringDay += '.';
            }

            else{
                newStringDay += day.charAt(i);
            }
        }
        day = newStringDay;
    }

    public void setHoursAndType(Scanner scnr){
        char repeat = 'Y';
        char selection;

        while(repeat == 'Y'){
            Hours_type currentDay = new Hours_type();
            currentDay.setHours(scnr);
            currentDay.setTypeTimeUsed(scnr);
            hoursArray.add(currentDay);
            System.out.println();
            System.out.print("Did you have more time to add for this day? Press Y for yes, or N for no: ");
            selection = scnr.next().charAt(0);
            scnr.nextLine();
            selection = Character.toUpperCase(selection);
            repeat = selection;
        }
    }

   public String getDay(){
    return day;
   }

   public ArrayList<Hours_type> getHoursAndType(){
    return hoursArray;
   }
   
}
