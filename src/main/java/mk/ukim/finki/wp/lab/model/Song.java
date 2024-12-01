package mk.ukim.finki.wp.lab.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Song {
    Long id;
    String trackID;
    String title;
    String genre;
    int releaseYear;
    List<Artist> performers;
    public Song(){

    }
    public Song(String trackID, String title, String genre, int releaseYear) {
        this.id = (long) (Math.random() * 1000);
        this.trackID = trackID;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        performers = new ArrayList<>();
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void addPerformer(Artist performer) {
        performers.add(performer);
    }
}
