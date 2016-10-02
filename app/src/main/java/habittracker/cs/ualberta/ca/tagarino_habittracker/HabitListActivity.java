package habittracker.cs.ualberta.ca.tagarino_habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class HabitListActivity extends MainActivity {

    private EditText bodyText;

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
                Toast.makeText(HabitListActivity.this,
                        "Delete "+list.get(position).toString(),
                        Toast.LENGTH_SHORT).show();
                Habit habit = list.get(position);
                HabitListController.getHabitList().deleteHabit(habit);
                return false;
            }
        });

/*
        Button addHabitButton = (Button) findViewById(R.id.addHabitButton);
        addHabitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();

            }
        });
        */
    }

    public void addHabitAction(View v) {
        Toast.makeText(this, "Adding a habit.", Toast.LENGTH_SHORT).show();
        HabitListController habitListController = new HabitListController();
        EditText textView = (EditText) findViewById(R.id.addHabitNameText);
        habitListController.addHabit(new IncompletedHabit(textView.getText().toString()));
    }
}
