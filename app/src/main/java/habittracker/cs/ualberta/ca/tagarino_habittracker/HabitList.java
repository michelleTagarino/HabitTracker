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
    protected ArrayList<Listener> listeners;

    public HabitList(){
        habitList = new ArrayList<Habit>();
        listeners = new ArrayList<Listener>();
    }

    public Collection<Habit> getHabits() {
        return habitList;
    }

    private void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }

    public void addHabit(Habit newHabit) {
        habitList.add(newHabit);
        notifyListeners();
    }

    public void deleteHabit(Habit habit) {
        habitList.remove(habit);
    }

    public int size() {
        return habitList.size();
    }

    public boolean contains(Habit habit) {
        return habitList.contains(habit);
    }

    public void addListener(Listener l) {
        listeners.add(l);
    }

    public void removeListener(Listener l) { listeners.remove(l); }
}