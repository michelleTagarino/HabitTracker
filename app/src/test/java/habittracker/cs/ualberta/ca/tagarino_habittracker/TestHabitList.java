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
        assertTrue("Empty Habit List",habitList.size() == 0);
    }

    @Test
    public void addHabitListTest() {
        HabitList habitList = new HabitList();
        String habit = "Eat all the donuts.";
        Habit testHabit = new IncompletedHabit(habit);
        habitList.addHabit(testHabit);
        assertTrue("Habit List Size",habitList.size() == 1);
        assertTrue("Test habit contained", habitList.contains(testHabit));
    }

    @Test
    public void deleteHabitListTest() {

        HabitList habitList = new HabitList();
        String habit = "Eat all the donuts.";
        Habit testHabit = new IncompletedHabit(habit);
        habitList.addHabit(testHabit);
        assertTrue("Habit List Size",habitList.size() == 1);
        assertTrue(habitList.contains(testHabit));

        habitList.deleteHabit(testHabit);
        assertTrue("Habit list size after delete", habitList.size() == 0);
        assertFalse("Test habit not contained", habitList.contains(testHabit));
    }

    boolean updated = false;
    @Test
    public void testNotifyListeners() {
        HabitList habitList = new HabitList();
        updated = false;
        Listener l = new Listener() {
            public void update() {
                TestHabitList.this.updated = true;
            }
        };
        habitList.addListener(l);
        habitList.addHabit(new IncompletedHabit("Sleep all day and never wake up."));
        assertTrue("HabitList is not updating...",this.updated);
    }

    @Test
    public void testRemoveListeners() {
        HabitList habitList = new HabitList();
        updated = false;
        Listener l = new Listener() {
            public void update() {
                TestHabitList.this.updated = true;
            }
        };
        habitList.addListener(l);
        habitList.removeListener(l);
        habitList.addHabit(new IncompletedHabit("Sleep all day and never wake up."));
        assertFalse("HabitList is not updating...", this.updated);

    }





/*
    @Test
    public void chooseHabitListTest() {
        HabitList habitList = new HabitList();
        String habit = "Eat all the donuts.";
        Habit testHabit = new IncompletedHabit(habit);
        habitList.addHabit(testHabit);

        //Testing for 1 habit
        for (int i = 0; i < 10; i++) {
            Habit habits = habitList.chooseHabit();
            assertTrue("Student is not null", habit != null);
            assertTrue("Didn't choose the habit intended.", habits.equals(testHabit));
        }

        String habit2 = "Relish in a food coma.";
        Habit testHabit2 = new IncompletedHabit(habit2);
        habitList.addHabit(testHabit2);

    }
*/
}