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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**********************************************
 *  CREATED BY MICHELLE TAGARINO on 16-09-29. *
 **********************************************/

/*
The purpose of this class is to display Habit instances in a HabitList instance
by use of an ArrayAdapter. The user may long click on an item to access the habit's options,
where they will be able choose whether to delete the habit, or complete the habit when
they press the 'More...' button.
*/

public class HabitListActivity extends MainActivity {

    private static final String FILENAME = "file.sav";

    private ListView listView;
    private ArrayList<Habit> list;
    private ArrayAdapter<Habit> habitAdapter;
    private Collection<Habit> habits;

    private ArrayList<String> weekdayList;
    private final String[] WEEKDAY_ITEMS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private String adbMessage = "Choose MORE... to Complete Habit";
    private String habitInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.habits_list);

        listView = (ListView) findViewById(R.id.habitsListView);

        habits = HabitListController.getHabitList().getHabits();

        list = new ArrayList<>(habits);

        habitAdapter = new ArrayAdapter<>(HabitListActivity.this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(habitAdapter);

        // This listener should update the ListView with new habits added
        HabitListController.getHabitList().addListener(new Listener() {
            @Override
            public void update() {
                list.clear();
                Collection<Habit> habits = HabitListController.getHabitList().getHabits();
                list.addAll(habits);
                habitAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();

        //loadFromFile();

        habitAdapter = new ArrayAdapter<>(HabitListActivity.this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(habitAdapter);
        habitAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {

        super.onResume();

        // When one of the habit items in the array adapter is clicked for a long time,
        // a dialog box will appear and provide the user the option to delete or view
        // the habit details (Name of habit, Date created, Weekday(s) for completion, and Times completed.
        // Of course the user has the option to exit this dialog box without cause by clicking Cancel.
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(HabitListActivity.this);
                adb.setMessage(adbMessage);
                adb.setCancelable(true);
                final int finalPosition = position;

                // Add Delete button to delete the habit invoked
                adb.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Habit habit = list.get(finalPosition);
                        HabitListController.getHabitList().deleteHabit(habit);
                    }
                });
                // Add Cancel button to exit the dialog box
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                // Add More... button to view the habit's stats and the option to complete it
                adb.setPositiveButton("More...", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder adb = new AlertDialog.Builder(HabitListActivity.this);

                        // Save the habit's information --> (Name, Date, Weekday, Times completed)
                        Habit habit = list.get(finalPosition);

                        String habitName = habit.getName();

                        Date habitDate = habit.getDate();

                        // The next two lines of code were referenced from:
                        // https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
                        // on September 30, 2016
                        SimpleDateFormat formatter;
                        formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String strDate = formatter.format(habitDate);

                        // Convert the string array into a comma separated string
                        String habitDay  = habit.getWeekday().toString();
                        String regex = "\\[|\\]";
                        String strHabitDay = habitDay.replaceAll(regex,"").replaceAll(",",", ");

                        int habitCount = habit.getCountCompleted();

                        habitInfo = "Habit Name: "+habitName
                                + "\n\nDate Created: "+strDate
                                + "\n\nDay(s) to Complete:\n"+strHabitDay
                                + "\n\nTimes Completed: "+habitCount;

                        adb.setMessage(habitInfo);

                        adb.setCancelable(true);

                        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });

                        adb.setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Habit habit = list.get(finalPosition);
                                habit.incrementCountCompleted(); // Increment the times the habit was completed
                            }
                        });
                        adb.show();
                    }
                });
                adb.show();
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveInFile();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveInFile();
    }

    // Code referenced from LonelyTwitter application:
    // (https://github.com/sensible-heart/lonelyTwitter) on Sept. 28, 2016
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            ArrayList<Habit> newList = new ArrayList<>();

            Gson gson = new Gson();

            //Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept.22,2016
            Type listType = new TypeToken<ArrayList<Habit>>() {}.getType();
            newList.clear();
            newList = gson.fromJson(in, listType);

            list.clear();
            list.addAll(newList);

        } catch (FileNotFoundException e) {
            list = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    // Code referenced from LonelyTwitter application:
    // (https://github.com/sensible-heart/lonelyTwitter) on Sept. 28, 2016
    private void saveInFile() {
        try {
            list.clear();
            Collection<Habit> habits = HabitListController.getHabitList().getHabits();
            list.addAll(habits);

            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(list, writer);
            writer.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    // Clear text in EditText item automatically
    public void clearEditText(View v) {
        final EditText textView = (EditText) findViewById(R.id.addHabitNameText);
        textView.setText(null);
    }

    // When the Add Habit button is invoked:
    // A HabitListController is created; this will allow the ability to control the items in the HabitList
    public void addHabitAction(View v) {

        final HabitListController habitListController = new HabitListController();

        final CompletedHabitsController completedHabitsController = new CompletedHabitsController();

        final EditText textView = (EditText) findViewById(R.id.addHabitNameText);

        weekdayList = new ArrayList<>(); // Will hold the weekdays the habit should be completed

        AlertDialog.Builder adb = new AlertDialog.Builder(HabitListActivity.this);

        // Code referenced from https://developer.android.com/guide/topics/ui/dialogs.html on Oct. 1, 2016
        adb.setTitle(R.string.habit_weekday).setMultiChoiceItems(WEEKDAY_ITEMS, null, new DialogInterface.OnMultiChoiceClickListener() {

            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    weekdayList.add(WEEKDAY_ITEMS[which]);
                } else if (weekdayList.contains(WEEKDAY_ITEMS[which])) {
                    weekdayList.remove(WEEKDAY_ITEMS[which]);
                }
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Habit habit = new Habit(textView.getText().toString(), weekdayList);
                habitListController.addHabit(habit);
                completedHabitsController.addHabit(habit);
            }
        }).create();
        adb.show();
    }
}