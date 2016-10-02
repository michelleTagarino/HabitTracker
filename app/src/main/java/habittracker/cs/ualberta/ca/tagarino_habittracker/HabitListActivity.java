package habittracker.cs.ualberta.ca.tagarino_habittracker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class HabitListActivity extends MainActivity {

    private ArrayList<String> weekdayList;
    final String[] weekdayItems = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String habitInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.habits_list);

        ListView listView = (ListView) findViewById(R.id.habitsListView);

        Collection<Habit> habits = HabitListController.getHabitList().getHabits();

        final ArrayList<Habit> list = new ArrayList<Habit>(habits);

        final ArrayAdapter<Habit> habitAdapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(habitAdapter);

        HabitListController.getHabitList().addListener(new Listener() {
            @Override
            public void update() {
                list.clear();
                Collection<Habit> habits = HabitListController.getHabitList().getHabits();
                list.addAll(habits);
                habitAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(HabitListActivity.this);
                adb.setMessage("Delete "+list.get(position).toString()+"?");
                adb.setCancelable(true);
                final int finalPosition = position;

                adb.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Habit habit = list.get(finalPosition);
                        HabitListController.getHabitList().deleteHabit(habit);
                    }
                });

                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                adb.setPositiveButton("More...", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(HabitListActivity.this, "Pressed More...", Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder adb = new AlertDialog.Builder(HabitListActivity.this);

                        //save the object's (habit's) information --> (name, date, weekday, completionStatus)
                        Habit habit = list.get(finalPosition);

                        String habitName = habit.getName();

                        Date habitDate = habit.getDate();

                        // The next two lines of code taken from:
                        // http://stackoverflow.com/questions/4772425/change-date-format-in-a-java-string
                        // on September 30, 2016
                        //String strCurrentDate = "Wed, 18 Apr 2012";

                        SimpleDateFormat formatter;
                        formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String strDate = formatter.format(habitDate);

                        // Separate the string array into a comma separated string
                        String habitDay  = habit.getWeekday().toString();
                        String regex = "\\[|\\]";
                        String strHabitDay = habitDay.replaceAll(regex,"").replaceAll(",",", ");

                        int habitCount   = habit.getCountCompleted();

                        habitInfo = "Habit Name: "+habitName
                                + "\n\nDate Rendered: "+strDate
                                + "\n\nDay(s) to Complete:\n"+strHabitDay
                                + "\n\nTimes Completed: "+habitCount;

                        adb.setMessage(habitInfo);
                        adb.setCancelable(true);
                        adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });
                        adb.show();
                    }
                });
                adb.show();
                return false;
            }
        });
    }

    public void addHabitAction(View v) {
        //Toast.makeText(this, "Adding a habit.", Toast.LENGTH_SHORT).show();
        final HabitListController habitListController = new HabitListController();
        final EditText textView = (EditText) findViewById(R.id.addHabitNameText);

        weekdayList = new ArrayList<>();
        AlertDialog.Builder adb = new AlertDialog.Builder(HabitListActivity.this);
        adb.setTitle(R.string.habit_weekday).setMultiChoiceItems(weekdayItems, null, new DialogInterface.OnMultiChoiceClickListener() {

            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    // If the user checked the item, add it to the selected items
                    weekdayList.add(weekdayItems[which]);
                } else if (weekdayList.contains(weekdayItems[which])) {
                    // Else, if the item is already in the array, remove it
                    weekdayList.remove(weekdayItems[which]);
                }
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                habitListController.addHabit(new IncompletedHabit(textView.getText().toString(), weekdayList));
            }
        }).create();
        adb.show();
    }
}
