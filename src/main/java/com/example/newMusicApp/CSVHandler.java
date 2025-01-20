package com.example.newMusicApp;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class CSVHandler {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static List<Song> readFromCSV(String filePath) {
        List<Song> songs = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0].trim();
                String artist = values[1].trim();
                LocalDate date = LocalDate.parse(values[2].trim(), formatter);
                songs.add(new Song(name, artist, date));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return songs;
    }

    public static void writeToCSV(String filePath, List<Song> songs) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath))) {
            for (Song song : songs) {
                bw.write(String.format("%s,%s,%s", song.getName(), song.getArtist(), song.getDateAdded().format(formatter)));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
