package habittracker.cs.ualberta.ca.tagarino_habittracker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CompletedHabitsActivity extends MainActivity {

    private String habitInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_habits);

        ListView listView = (ListView) findViewById(R.id.completedhabitsListView);

        Collection<Habit> habits = HabitListController.getHabitList().getHabits();

        final ArrayList<Habit> list = new ArrayList<>(habits);

        // Go through list to see which habits have been completed
        // If it has NOT been completed yet, remove the habit from the list
        for (int i = 0; i < list.size()-1; i++) {
            if (!list.get(i).isComplete()) {
                list.remove(i);
            }
        }

        final ArrayAdapter<Habit> habitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(habitAdapter);

        CompletedHabitsController.getHabitList().addListener(new Listener() {
            @Override
            public void update() {
                list.clear();
                Collection<Habit> habits = CompletedHabitsController.getHabitList().getHabits();
                list.addAll(habits);
                habitAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(CompletedHabitsActivity.this);

                adb.setCancelable(true);

                final int finalPosition = position;

                Habit habit = list.get(finalPosition);

                String habitName = habit.getName();

                Date habitDate = habit.getDate();

                // The next three lines of code were referenced from:
                // https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
                // on September 30, 2016
                SimpleDateFormat formatter,weekFormatter;

                formatter = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = formatter.format(habitDate);

                weekFormatter = new SimpleDateFormat("EEE");
                String strWeekday = formatter.format(habitDate);

                // Convert the string array into a comma separated string
                String habitDay = habit.getWeekday().toString();
                String regex = "\\[|\\]";
                String strHabitDay = habitDay.replaceAll(regex, "").replaceAll(",", ", ");

                int habitCount = habit.getCountCompleted();

                habitInfo = "Habit Name: " + habitName
                        + "\n\nDate Rendered: " + strDate
                        + "\n\nDay(s) to Complete:\n" + strHabitDay
                        + "\n\nTimes Completed: " + habitCount
                        + "\n\nLast Weekday Completed: " + strWeekday;

                adb.setMessage(habitInfo);
                adb.setCancelable(true);
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                adb.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Habit habit = list.get(finalPosition);
                        CompletedHabitsController.getHabitList().deleteHabit(habit);
                        HabitListController.getHabitList().deleteHabit(habit);
                    }
                });
                adb.setPositiveButton("Complete Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Habit habit = list.get(finalPosition);
                        habit.incrementCountCompleted();
                    }
                });
                adb.show();
                return false;
            }
        });
    }
}