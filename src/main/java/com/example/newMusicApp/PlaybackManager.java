package com.example.newMusicApp;

import java.util.*;
import java.util.stream.Collectors;


public class PlaybackManager {
    private MusicLibrary library;
    private PriorityQueue<Song> topSongs;


    public PlaybackManager(MusicLibrary library) {
        this.library = library;
        this.topSongs = new PriorityQueue<>(Comparator.comparingInt(Song::getPlayCount));
    }

    public void playSong(String songName, String artistName) {
        Song targetSong = library.getAllSongs().stream()
                .filter(song -> song.getName().equalsIgnoreCase(songName) && song.getArtist().equalsIgnoreCase(artistName))
                .findFirst()
                .orElse(null);

        if (targetSong == null) {
            System.out.println("Song not found: " + songName + " by " + artistName);
            return;
        }

        targetSong.incrementPlayCount();
        updateTopSongs(targetSong);
        System.out.println("Played: " + songName + " by " + artistName);
    }

    private void updateTopSongs(Song song) {
        topSongs.remove(song);
        topSongs.offer(song);

        if (topSongs.size() > 5) {
            topSongs.poll();
        }
    }

    public List<Song> getTop5Songs() {
        return topSongs.stream()
                .sorted(Comparator.comparingInt(Song::getPlayCount).reversed())
                .collect(Collectors.toList());
    }

    public void importPlaylist(String filePath) {
        library.importPlaylist(filePath);
    }

    public void exportPlaylist(String filePath) {
        library.exportPlaylist(filePath);
    }
}
