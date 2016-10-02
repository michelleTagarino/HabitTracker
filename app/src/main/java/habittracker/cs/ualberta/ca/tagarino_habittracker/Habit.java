package habittracker.cs.ualberta.ca.tagarino_habittracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**********************************************
 *  CREATED BY MICHELLE TAGARINO on 16-09-18. *
 **********************************************/

/*
The Habit class is meant to model the types of habits of the user.
There are different types of habits, Incompleted, Completed, Important,
and Reminder, that will be extended from this class, hence why it is abstract.
*/

public abstract class Habit {

    protected String name;
    protected Date date;
    protected ArrayList<String> weekday;
    protected int count;

    public Habit(String nameArg) {
        this.name = nameArg;
        this.date = new Date();
        this.weekday.add("Sunday");
        this.count = 0;
    }

    public Habit(String nameArg, ArrayList<String> weekdayArg) {
        this.name = nameArg;
        this.date = new Date();
        this.weekday = weekdayArg;
        this.count = 0;
    }

    public abstract Boolean isComplete();

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

}
