package habittracker.cs.ualberta.ca.tagarino_habittracker;

/**
 * Created by michelletagarino on 16-10-02.
 */
public class CompletedHabitsController {

    private static CompletedHabits completedHabits = null;

    static public CompletedHabits getCompletedHabits() {
        if (completedHabits == null) {
            completedHabits = new CompletedHabits();
        }
        return completedHabits;
    }

    public void addHabit(Habit habit) {
        getCompletedHabits().addCompletedHabit(habit);
    }
}
