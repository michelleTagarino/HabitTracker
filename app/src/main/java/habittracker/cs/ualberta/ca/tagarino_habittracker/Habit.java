package habittracker.cs.ualberta.ca.tagarino_habittracker;

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
    protected String weekday;
    protected int count;

    public Habit(String nameArg) {
        this.name = nameArg;
        this.date = new Date();
        this.weekday = "Sunday";
        this.count = 0;
    }

    public Habit(String nameArg, String weekdayArg) {
        this.name = nameArg;
        this.date = new Date();
        this.weekday = weekdayArg;
        this.count = 0;
    }

    //public abstract String getWeekday();

    public abstract Boolean isComplete();

    public String getName() {
        return this.name;
    }

    public String getWeekday() {
        return this.weekday;
    }

    public int getCountCompleted(){ return this.count; }


    /*
    public int getWeekday(){
        Date currentDate = getDate();

        //Code referenced from https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html Sept. 12, 2016
        Calendar calendarObject = Calendar.getInstance();
        calendarObject.setTime(currentDate);
        this.weekday = calendarObject.get(Calendar.DAY_OF_WEEK); //1 is Sunday...Saturday is 7

        return this.weekday;
    }
    */
}
