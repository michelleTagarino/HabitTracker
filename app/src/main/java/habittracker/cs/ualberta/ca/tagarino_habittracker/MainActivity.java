package habittracker.cs.ualberta.ca.tagarino_habittracker;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewHabitsButton = (Button) findViewById(R.id.viewHabitsButton);
        viewHabitsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Toast.makeText(MainActivity.this,"View Habits",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,HabitListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public void completedHabits(MenuItem menuItem){
        Toast.makeText(this,"Completed Habits",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,CompletedHabitsActivity.class);
        startActivity(intent);
    }
/*
    public void editHabits(MenuItem menuItem) {
        Toast.makeText(this, "Edit Habits", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, )
        startActivity(intent);
    }
*/
}