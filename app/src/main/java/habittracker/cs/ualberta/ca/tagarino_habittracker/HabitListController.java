package habittracker.cs.ualberta.ca.tagarino_habittracker;

/**
 * Created by michelletagarino on 16-10-02.
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
