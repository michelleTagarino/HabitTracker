package habittracker.cs.ualberta.ca.tagarino_habittracker;

/**
 * Created by michelletagarino on 16-10-01.
 */
public class CompletedHabit extends Habit {

    private final int COUNT_COMPLETED = 1;

    public CompletedHabit(String nameArg) {
        super(nameArg);
        count = COUNT_COMPLETED;
    }
    public CompletedHabit(String nameArg,String weekdayArg){
        super(nameArg,weekdayArg);
        count = COUNT_COMPLETED;
    }

    @Override
    public Boolean isComplete() {
        return Boolean.TRUE;
    }

    public int getCountCompleted() { return count; }

    public void incrementCountCompleted() { count++; }
}
