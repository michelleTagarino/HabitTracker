/*
    Habit Tracker: Record the statistics of a user's habits and its completions

    Copyright (C) 2016  Michelle Tagarino  tagarino@ualberta.ca

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
*/

package habittracker.cs.ualberta.ca.tagarino_habittracker;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

/**********************************************
 *  CREATED BY MICHELLE TAGARINO on 16-09-08. *
 **********************************************/

/*
This class displays the home screen of the app. The user may press the
VIEW HABITS button at the bottom to view a list of all incompleted and
completed habits.
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewHabitsButton = (Button) findViewById(R.id.viewHabitsButton);
        viewHabitsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setResult(RESULT_OK);

                Intent intent = new Intent(MainActivity.this,HabitListActivity.class);
                startActivity(intent);
            }
        });
    }

    // Create app bar at the top
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    // Add menu item
    public void completedHabits(MenuItem menuItem){
        Intent intent = new Intent(MainActivity.this,CompletedHabitsActivity.class);
        startActivity(intent);
    }
}