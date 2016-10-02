package habittracker.cs.ualberta.ca.tagarino_habittracker;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestHabit {

    @Test
    public void getNameTest() {
        String incompletedHabitName = "Shake a twig.";
        Habit incompletedHabit = new IncompletedHabit("Shake a twig.");
        assertTrue(incompletedHabitName.equals(incompletedHabit.getName()));

        String completedHabitName = "Play with sloths.";
        Habit completedHabit = new CompletedHabit("Play with sloths.");
        assertTrue(completedHabitName.equals(completedHabit.getName()));
    }

    @Test
    public void getWeekdayTest() {
        String incompletedWeekday = "Tuesday";
        Habit incompletedHabit = new IncompletedHabit("Drink fire","Tuesday");
        assertTrue(incompletedWeekday.equals(incompletedHabit.getWeekday()));

        String completedWeekday = "Tuesday";
        Habit completedHabit = new CompletedHabit("Drink fire","Tuesday");
        assertTrue(completedWeekday.equals(completedHabit.getWeekday()));
    }

    //Tests to see if a habit has the correct amount of times it was completed
    @Test
    public void getCountTest() {
        Habit incompletedHabit = new IncompletedHabit("Eat bread and nap.");
        int incompletedCount = incompletedHabit.getCountCompleted();
        assertEquals(incompletedCount,0);

        Habit completedHabit = new CompletedHabit("Eat bread and nap forevermore.");
        int completedCount = completedHabit.getCountCompleted();
        assertEquals(completedCount,1);
    }

    // The next two methods test to see if the Habit Name entered
    // was the string the user entered and not arbitrary 'garbage'
    @Test
    public void testHabitName() {
        String habitName = "Let's rap like Drake.";
        Habit habit = new IncompletedHabit(habitName);
        assertTrue("Habit Name is not equal", habitName.equals(habit.getName()));
    }

    @Test
    public void testHabitNameToString() {
        String habitName = "Let's rap like Drake.";
        Habit habit = new IncompletedHabit(habitName);
        assertTrue("Habit Name.toString is not equal", habitName.toString().equals(habit.getName()));
    }
}