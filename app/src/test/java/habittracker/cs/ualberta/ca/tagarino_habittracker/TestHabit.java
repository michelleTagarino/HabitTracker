package habittracker.cs.ualberta.ca.tagarino_habittracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestHabit {

    @Test
    public void getNameTest() {
        String habitName = "Shake a twig.";
        Habit habit = new Habit("Shake a twig.");
        assertTrue(habitName.equals(habit.getName()));
    }

    @Test
    public void getWeekdayTest() {
        ArrayList<String> weekday = new ArrayList<>();
        weekday.add("Tuesday");
        Habit habit = new Habit("Drink fire",weekday);
        assertTrue(weekday.equals(habit.getWeekday()));
    }

    //Tests to see if a habit has the correct amount of times it was completed
    @Test
    public void getCountTest() {
        Habit habit = new Habit("Eat bread and nap.");
        int count = habit.getCountCompleted();
        assertEquals(count,0);

        habit.incrementCountCompleted();
        count = habit.getCountCompleted();
        assertEquals(count,1);
    }

    // The next two methods test to see if the Habit Name entered
    // was the string the user entered and not arbitrary 'garbage'
    @Test
    public void testHabitName() {
        String habitName = "Let's rap like Drake.";
        Habit habit = new Habit(habitName);
        assertTrue("Habit Name is not equal", habitName.equals(habit.getName()));
    }

    @Test
    public void testHabitNameToString() {
        String habitName = "Let's rap like Drake.";
        Habit habit = new Habit(habitName);
        assertTrue("Habit Name.toString is not equal", habitName.toString().equals(habit.getName()));
    }
}