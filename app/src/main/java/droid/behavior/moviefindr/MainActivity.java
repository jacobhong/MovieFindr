package droid.behavior.moviefindr;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    public static final String EXTRA_MESSAGE = "droid.behavior.moviefindr.MESSAGE";
    private Button searchButton;
    private ProgressDialog dialogue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SearchTask().execute();
            }
        });
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
        switch(id)
        {
            case R.id.action_search:
               // openSearch();
                return true;
            case R.id.action_settings:
               // openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class SearchTask extends AsyncTask<Void, Void, ArrayList<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogue = ProgressDialog.show(MainActivity.this,
                    "Searching movies", "loading..please wait");
            dialogue.setCancelable(true);
            dialogue.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    cancel(true);
                }
            });
        }

        @Override
        protected ArrayList<Movie> doInBackground(Void... params) {
            final HTTPUtil service = new HTTPUtil();
            final EditText editText = (EditText) findViewById(R.id.edit_message);
            final String message = editText.getText().toString();
            final String json = service.getJSON(message);
            final ParseJSON parse = new ParseJSON();
            return parse.parseJson(json);
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> result) {
            super.onPostExecute(result);
            try
            {
                if (null != dialogue && dialogue.isShowing())
                {
                    dialogue.dismiss();
                    dialogue = null;
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            if (result != null)
            {
            // start activity with result
                Log.e("doInBackground", result.toString());

                final Intent intent = new Intent(getApplicationContext(), DisplayMoviesActivity.class);
                intent.putParcelableArrayListExtra("movies", result);
                startActivity(intent);

            } else
            {
               Log.e("doInBackground", "No results found");
            }
        }

    }

}
