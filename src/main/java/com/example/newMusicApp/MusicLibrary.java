package com.example.newMusicApp;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MusicLibrary {

    private Map<String, List<Song>> artistSongsMap;
    private List<Song> allSongs;

    public MusicLibrary() {
        artistSongsMap = new HashMap<>();
        allSongs = new ArrayList<>();
    }

    public void addSong(String songName, String artistName, LocalDate date) {
        if (songName == null || songName.isEmpty() || artistName == null || artistName.isEmpty()) {
            System.out.println("Song name and artist name cannot be null or empty.");
            return;
        }

        String artistNameLower = artistName.toLowerCase();
        Song newSong = new Song(songName, artistName, date);
        allSongs.add(newSong);
        artistSongsMap.putIfAbsent(artistNameLower, new ArrayList<>());
        artistSongsMap.get(artistNameLower).add(newSong);
    }

    public List<Song> getSongByArtist(String artistName) {
        String artistNameLower = artistName.toLowerCase();
        return artistSongsMap.getOrDefault(artistNameLower, Collections.emptyList());
    }

    public List<Song> getAllSongs() {
        return allSongs;
    }

    public void importPlaylist(String filePath) {
        CSVHandler.readFromCSV(filePath).forEach(song -> {
            addSong(song.getName(), song.getArtist(), song.getDateAdded());
        });
    }

    public void exportPlaylist(String filePath) {
        CSVHandler.writeToCSV(filePath, allSongs);
    }
}
