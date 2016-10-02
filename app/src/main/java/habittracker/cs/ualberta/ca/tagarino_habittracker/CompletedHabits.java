package habittracker.cs.ualberta.ca.tagarino_habittracker;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by michelletagarino on 16-10-02.
 */
public class CompletedHabits {

    protected ArrayList<Habit> completedHabits;
    protected ArrayList<Listener> listeners;

    public CompletedHabits(){
        completedHabits = new ArrayList<Habit>();
        listeners = new ArrayList<Listener>();
    }

    public Collection<Habit> getHabits() {
        return completedHabits;
    }

    private void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }

    public void addCompletedHabit(Habit newHabit) {
        completedHabits.add(newHabit);
        notifyListeners();
    }

    public void deleteCompletedHabit(Habit habit) {
        completedHabits.remove(habit);
        notifyListeners();
    }

    public int size() {
        return completedHabits.size();
    }

    public boolean contains(Habit habit) {
        return completedHabits.contains(habit);
    }

    public void addListener(Listener l) {
        listeners.add(l);
    }

    public void removeListener(Listener l) { listeners.remove(l); }
}
