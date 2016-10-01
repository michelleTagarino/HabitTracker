package habittracker.cs.ualberta.ca.tagarino_habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class HabitListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habits_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflating the menu adds one item to the app bar
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
