package droid.behavior.moviefindr;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by pairenoid on 4/5/2015.
 */
public class Movie implements Parcelable, Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String vote_average;
    private String vote_count;
    private String backdrop_path;
    private String poster_path;

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public Movie(){}

    public Movie(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.vote_average = in.readString();
        this.vote_count = in.readString();
        this.backdrop_path = in.readString();
        this.poster_path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(vote_average);
        dest.writeString(vote_count);
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


    @Override
    public int describeContents() {
        return 0;
    }
    public static Parcelable.Creator<Movie> getCreator() {
        return CREATOR;
    }

    public String toString() {
        return "Movie [title=" + title + "\n " + "id=" + id + "\n " + "vote_avg=" + vote_average + " \n" +
                "vote_count=" + vote_count + " \n" + "backdrop=" + backdrop_path + "\n " + "posterpath=" + poster_path +
        "]";
    }
}
