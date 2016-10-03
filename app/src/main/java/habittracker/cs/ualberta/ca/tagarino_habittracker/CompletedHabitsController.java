package habittracker.cs.ualberta.ca.tagarino_habittracker;

/**********************************************
 *  CREATED BY MICHELLE TAGARINO on 16-10-01. *
 **********************************************/
public class CompletedHabitsController {

    private static CompletedHabits habitList = null;

    static public CompletedHabits getHabitList() {
        if (habitList == null) {
            habitList = new CompletedHabits();
        }
        return habitList;
    }

    public void addHabit(Habit habit) {
        getHabitList().addHabit(habit);
    }
}
