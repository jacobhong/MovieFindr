package droid.behavior.moviefindr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DisplayMoviesActivity extends ActionBarActivity {
    private static final String URL= "http://www.omdbapi.com/?";
    private static final String POSTERAPIREQUEST = "http://img.omdbapi.com?apikey=[eee89624]&";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        ArrayList<Movie> message = extras.getParcelableArrayList("movies");

        ListAdapter theAdapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, message);

        ListView movieList = (ListView) findViewById(R.id.displayMoviesView);
        movieList.setAdapter(theAdapter);
        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String moviePicked = "you selected " + String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(), moviePicked, Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
