package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {

    public List<Song> findAll(){
        return DataHolder.songs;
    }

    public Optional<Song> findSongByTrackId(String trackID){
        return DataHolder.songs.stream()
                .filter(s -> s.getTrackID().equals(trackID)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        for(Song s : DataHolder.songs){
            if(s.getTrackID().equals(song.getTrackID())){
                song.addPerformer(artist);
                return artist;
            }
        }
        return null;
    }
}
