package habittracker.cs.ualberta.ca.tagarino_habittracker;

/**
 * Created by michelletagarino on 16-10-01.
 */
public class IncompletedHabit extends Habit {

    public IncompletedHabit(String nameArg) { super(nameArg); }
    public IncompletedHabit(String nameArg,String weekdayArg){ super(nameArg,weekdayArg); }

    @Override
    public Boolean isComplete() {
        return Boolean.FALSE;
    }
}
