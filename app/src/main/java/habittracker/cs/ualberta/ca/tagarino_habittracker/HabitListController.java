package habittracker.cs.ualberta.ca.tagarino_habittracker;

/**********************************************
 *  CREATED BY MICHELLE TAGARINO on 16-10-01. *
 **********************************************/

/*
This class provides data flow between the HabitList class and
the HabitListActivity class.
*/

public class HabitListController {

    private static HabitList habitList = null;

    static public HabitList getHabitList() {
        if (habitList == null) {
            habitList = new HabitList();
        }
        return habitList;
    }

    public void addHabit(Habit habit) {
        getHabitList().addHabit(habit);
    }
}
