package habittracker.cs.ualberta.ca.tagarino_habittracker;

import java.util.ArrayList;
import java.util.Collection;

/**********************************************
 *  CREATED BY MICHELLE TAGARINO on 16-09-29. *
 **********************************************/

/*
This class will hold all the completed habits (habit with completed counts > 0).
This class will be used by the CompletedHabitsActivity class and CompletedHabitsController
class to display as an ArrayAdapter to the user.
*/

public class CompletedHabits {

    protected ArrayList<Habit> habitList;
    protected ArrayList<Listener> listeners;

    public CompletedHabits(){
        habitList = new ArrayList<>();
        listeners = new ArrayList<>();
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
        notifyListeners();
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
