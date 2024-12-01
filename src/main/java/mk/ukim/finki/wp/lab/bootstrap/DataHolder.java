package mk.ukim.finki.wp.lab.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// In-Memory Data Holder
@Component
public class DataHolder {

    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();

    @PostConstruct
    public void init(){
        artists.add(new Artist(1L, "Chris", "Kamishev", "GOAT"));
        artists.add(new Artist(2L, "Harry", "Potter", "Scrapper"));
        artists.add(new Artist(3L, "Bilbo", "Baggings", "AI/ML"));
        artists.add(new Artist(4L, "Samwise", "Gamge", "CloudBased"));
        artists.add(new Artist(5L, "Brunzi", "Brunovski", "Databases"));

        songs.add(new Song("001", "Timeless", "Pop", 2024));
        songs.add(new Song("002", "My Eyes", "Rock", 2023));
        songs.add(new Song("003", "Fall Back", "Deep House", 2022));
        songs.add(new Song("004", "Thunderstruck", "Techno", 2021));
        songs.add(new Song("005", "Purple Rain", "Rock", 2020));

    }


}
