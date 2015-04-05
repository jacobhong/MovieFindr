package droid.behavior.moviefindr;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by pairenoid on 4/5/2015.
 */
public class Movie implements Parcelable, Serializable {
    private static final long serialVersionUID = 1L;

    public String id;
    public String title;
    public String backdrop_path;
    public String poster_path;
    public String results;

    public Movie(){}

    public Movie(Parcel in) {
        this.title = in.readString();
        this.backdrop_path = in.readString();
        this.poster_path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(backdrop_path);
        dest.writeString(poster_path);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static Parcelable.Creator<Movie> getCreator() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "Movie [title=" + title + "]";
    }
}
