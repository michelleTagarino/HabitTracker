package habittracker.cs.ualberta.ca.tagarino_habittracker;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestHabitList {

    @Test
    public void emptyHabitsListTest() {
        HabitList habitList = new HabitList();
        Collection<Habit> habits = habitList.getHabits();
        assertTrue("Empty Habit List",habits.size() == 0);
    }

    @Test
    public void addHabitListTest() {
        HabitList habitList = new HabitList();
        String habit = "Eat all the donuts.";
        Habit testHabit = new IncompletedHabit(habit);
        habitList.addHabit(testHabit);
        Collection<Habit> habits = habitList.getHabits();
        assertTrue("Habit List Size",habits.size() == 1);
        assertTrue("Test habit contained", habits.contains(testHabit));
    }

    @Test
    public void deleteHabitListTest() {

        HabitList habitList = new HabitList();
        String habit = "Eat all the donuts.";
        Habit testHabit = new IncompletedHabit(habit);
        habitList.addHabit(testHabit);
        Collection<Habit> habits = habitList.getHabits();
        assertTrue("Habit List Size",habits.size() == 1);
        assertTrue(habits.contains(testHabit));

        habitList.deleteHabit(testHabit);
        habits = habitList.getHabits();
        assertTrue("Habit list size after delete", habits.size() == 0);
        assertFalse("Test habit not contained", habits.contains(testHabit));
    }
}