# Time_Entry_JSON_Formatter

# Preconfiguration

For this to work, you need to have Java's jdk installed. 

You can see if you already have it installed by opening Command Prompt, and typing in "java --version" (without the ""). If it shows you a version number, you're all set! If not, you'll need to download it.

You can find it here:
https://www.oracle.com/java/technologies/downloads/#jdk24-windows

You'll probably need the x64 installer

Once that is installed, just double click "Run_Time_Entry.bat", and it should open a terminal for you to interact with the program with

# To Note

As this is currently, I don't think this is something that would be widely useful, however it does produce a standardized looking document for reporting time. It was made as a way to make my own time look decent when turning it into management. The program is basically to just format text to then submit. 

# How to Use

The program will walk you through entering your name, employee number, and contact info. It'll then ask for the start date for the current pay period, and end date.

After that, you enter a loop of putting in the current day you're reporting time for. Within that loop, it'll ask how many hours you worked that day of one category, and then what that category is. It'll then ask if there's any more time to report for that day, and if you type in Y, it'll ask again how many hours, and what category it should be in.

Once you're done reporting hours for a specific day (by putting in N when it asks if you have more to put in for that day), it'll then ask if there's another day in this pay period you need to input time for, and then repeats the above loop.

Once you're done putting in days and hours, it'll output a .json file into the "time_submissions" folder, using your name and the start and end date of the pay period as the name.

## Notable Problems

This currently does not have any way to validate any data being put into it, and so if you put something that doesn't make sense the program will either error out or just print whatever you put in. If you put in a / in a date, it will change that to a period.

If you do make a mistake, there's no way to double check that currently, other than after the json file is made. 
