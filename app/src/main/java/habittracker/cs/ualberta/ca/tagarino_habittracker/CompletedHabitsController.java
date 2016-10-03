package habittracker.cs.ualberta.ca.tagarino_habittracker;

/**
 * Created by michelletagarino on 16-10-02.
 */
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
