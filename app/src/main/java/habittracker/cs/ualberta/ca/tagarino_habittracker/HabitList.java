package habittracker.cs.ualberta.ca.tagarino_habittracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.Collection;

/**********************************************
 *  CREATED BY MICHELLE TAGARINO on 16-09-29. *
 **********************************************/

public class HabitList {

    protected ArrayList<Habit> habitList;

    public HabitList(){
        habitList = new ArrayList<Habit>();
    }

    public Collection<Habit> getHabits() {
        return habitList;
    }

    public void addHabit(Habit newHabit) {
        habitList.add(newHabit);
    }

    public void deleteHabit(Habit habit) {
        habitList.remove(habit);
    }
}