package habittracker.cs.ualberta.ca.tagarino_habittracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**********************************************
 *  CREATED BY MICHELLE TAGARINO on 16-09-18. *
 **********************************************/

/*
The Habit class is meant to model the types of habits of the user.
This class is a regular class. All instances of this class have its own attributes
that include the Habit Name, Habit Date, Weekday for Completion, and Times Completed.
All new instances obviously have a count of 0 since they have not been completed yet.
*/

public class Habit {

    private String name;
    private Date date;
    private ArrayList<String> weekday = new ArrayList<>();
    private int count;

    public Habit(String nameArg) {
        this.name = nameArg;
        this.date = new Date();
        this.weekday.add("Sun");
        this.count = 0;
    }

    public Habit(String nameArg, ArrayList<String> weekdayArg) {
        this.name = nameArg;
        this.date = new Date();
        this.weekday = weekdayArg;
        this.count = 0;
    }

    public Boolean isComplete() {
        if (this.count == 0) return Boolean.FALSE;
        else return Boolean.TRUE;
    }

    public String toString() {
        return getName().toString();
    }

    public String getName() {
        return this.name;
    }

    public Date getDate() {
        return this.date;
    }

    public ArrayList<String> getWeekday() {
        return this.weekday;
    }

    public int getCountCompleted() {
        return this.count;
    }

    public void incrementCountCompleted() { count++; }

}
