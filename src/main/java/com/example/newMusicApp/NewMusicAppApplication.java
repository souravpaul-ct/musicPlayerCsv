package com.example.newMusicApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@SpringBootApplication
public class NewMusicAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewMusicAppApplication.class, args);

		MusicLibrary library = new MusicLibrary();
		PlaybackManager manager = new PlaybackManager(library);
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Main Menu:");
			System.out.println("1. Add Song");
			System.out.println("2. Play Song");
			System.out.println("3. Show Top 5 Songs");
			System.out.println("4. Import Playlist");
			System.out.println("5. Export Playlist");
			System.out.println("6. Exit");
			System.out.print("Choose an option: ");

			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
				case 1:
					System.out.print("Enter song name: ");
					String songName = scanner.nextLine();
					System.out.print("Enter artist name: ");
					String artistName = scanner.nextLine();
					System.out.print("Enter date added (dd/MM/yyyy): ");
					LocalDate date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					library.addSong(songName, artistName, date);
					break;
				case 2:
					System.out.print("Enter song name: ");
					String playSongName = scanner.nextLine();
					System.out.print("Enter artist name: ");
					String playArtistName = scanner.nextLine();
					manager.playSong(playSongName, playArtistName);
					break;
				case 3:
					System.out.println("Top 5 Songs:");
					manager.getTop5Songs().forEach(System.out::println);
					break;
				case 4:
					String importPath = "src/main/resources/input_playlist.csv";
					manager.importPlaylist(importPath);
					System.out.println("File Imported");
					break;
				case 5:
					String exportPath =  "src/main/resources/output_playlist.csv";
					manager.exportPlaylist(exportPath);
					System.out.println();
					System.out.println("File exported");
					break;
				case 6:
					System.out.println("Exiting...");
					scanner.close();
					return;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}
	}
