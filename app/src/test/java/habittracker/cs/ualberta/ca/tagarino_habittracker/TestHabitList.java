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
        Habit testHabit = new Habit(habit);
        habitList.addHabit(testHabit);
        assertTrue("Habit List Size",habitList.size() == 1);
        assertTrue("Test habit contained", habitList.contains(testHabit));
    }

    @Test
    public void deleteHabitListTest() {

        HabitList habitList = new HabitList();
        String habit = "Eat all the donuts.";
        Habit testHabit = new Habit(habit);
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
        Habit testHabit = new Habit("Sleep all day and never wake up.");
        habitList.addHabit(testHabit);
        assertTrue("HabitList is not updating...", this.updated);
        updated = false;
        habitList.deleteHabit(testHabit);
        assertTrue("HabitList is still not updating...", this.updated);
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
        habitList.addHabit(new Habit("Sleep all day and never wake up."));
        assertFalse("HabitList is not updating...", this.updated);
    }
}