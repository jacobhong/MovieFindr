package droid.behavior.moviefindr;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pairenoid on 3/22/2015.
 */
public class ParseJSON {
    private ArrayList<Movie> movies;
    private String title, rating, critics_consensus, poster, release_date,
            rated, synopsis, links;
    private int year, runtime;

    public ArrayList<Movie> parseJson(String url)
    {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try
        {
            JSONObject obj = new JSONObject(url);
            Log.e("parseJson", obj.toString(3));

            JSONArray arry = obj.getJSONArray(MovieConstants.RESULTS);
            for (int i = 0; i < arry.length(); i ++)
            {
                JSONObject o = arry.getJSONObject(i);
                Movie movie = new Movie();
                movie.setId(o.getString(MovieConstants.ID));
                movie.setVote_average(o.getString(MovieConstants.VOTE_AVERAGE));
                movie.setVote_count(o.getString(MovieConstants.VOTE_COUNT));
                movie.setTitle(o.getString(MovieConstants.TITLE));
                movie.setBackdrop_path(o.getString(MovieConstants.BACKDROP_PATH));
                movie.setPoster_path(o.getString(MovieConstants.POSTER_PATH));
                movies.add(movie);
            }
        }
        catch (JSONException e)
        {
            Log.e("parseJson", e + "");
        }
        Log.e("parseJson", movies.toString());
        return movies;

    }

    static class MovieConstants {
        public final static String ID = "id";
        public final static String TITLE = "title";
        public final static String BACKDROP_PATH = "backdrop_path";
        public final static String POSTER_PATH = "poster_path";
        public final static String RESULTS = "results";
        public static final String VOTE_AVERAGE = "vote_average";
        public static final String VOTE_COUNT= "vote_count";

    }
}
