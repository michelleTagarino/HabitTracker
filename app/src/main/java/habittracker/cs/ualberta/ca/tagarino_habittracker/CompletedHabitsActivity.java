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
The purpose of this class is to display a list of completed habits.
The user may delete a habit if they so choose, or complete the habit again.
To display the completed habits in a list, this activity uses an ArrayAdapter.
*/

public class CompletedHabitsActivity extends MainActivity {

    private static final String FILENAME = "fileCompleted.sav";

    private ListView listView;
    private Collection<Habit> habits;
    private ArrayList<Habit> list;
    private ArrayAdapter<Habit> habitAdapter;
    private String habitInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_habits);

        listView = (ListView) findViewById(R.id.completedhabitsListView);

        habits = CompletedHabitsController.getHabitList().getHabits();

        list = new ArrayList<>(habits);

        // Go through list to see which habits have been completed
        // If it has NOT been completed yet, remove the habit from the list
        ArrayList<Habit> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isComplete()) {
                newList.add(list.get(i));
            }
        }

        list.clear();
        list.addAll(newList);

        habitAdapter = new ArrayAdapter<>(CompletedHabitsActivity.this, android.R.layout.simple_list_item_1, list);

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
    }

    @Override
    protected void onStart() {

        super.onStart();

        //loadFromFile();

        // Go through list to see which habits have been completed
        // If it has NOT been completed yet, remove the habit from the list
        ArrayList<Habit> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isComplete()) {
                newList.add(list.get(i));
            }
        }

        list.clear();
        list.addAll(newList);

        habitAdapter = new ArrayAdapter<>(CompletedHabitsActivity.this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(habitAdapter);
        habitAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {

        super.onResume();

        // When a habit item is clicked long, a dialog box appears.
        // Here, the user can either a delete a habit or complete the habit again.
        // The dialog box also includes information about the habit, incuding:
        //          - Habit Name, Date Created, Weekday(s) for Completion, and Times Completed
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Create a new dialog box for specific habit
                AlertDialog.Builder adb = new AlertDialog.Builder(CompletedHabitsActivity.this);

                adb.setCancelable(true);

                final int finalPosition = position;

                Habit habit = list.get(finalPosition);

                String habitName = habit.getName();

                Date habitDate = habit.getDate();

                // The next three lines of code were referenced from:
                // https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
                // on September 30, 2016
                SimpleDateFormat formatter, weekFormatter;

                formatter = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = formatter.format(habitDate);

                // Get the first 3 letters of the weekday the habit was completed
                weekFormatter = new SimpleDateFormat("EEE");
                String strWeekday = weekFormatter.format(habitDate);

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

                // This long string should display all the attributes of the habit invoked
                adb.setMessage(habitInfo);

                adb.setCancelable(true);
                // Add Cancel button to exit the dialog box
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                // Add Delete button to delete habit
                adb.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Habit habit = list.get(finalPosition);
                        CompletedHabitsController.getHabitList().deleteHabit(habit);
                        HabitListController.getHabitList().deleteHabit(habit);
                    }
                });
                // Add Complete Again button to increment habit completions
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
            Collection<Habit> habits = CompletedHabitsController.getHabitList().getHabits();
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
}