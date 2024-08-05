package main.com.java.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordSearching {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Please enter the <path> and <wordToSearch>!");
            return;
		}
		
		String path = args[0];
		String wordToSearch = args[1];
		
		File directory = new File(path);
		if (!directory.isDirectory()) {
			System.out.println("The provided path is not a directory.");
            return;
		}
		
		File[] fileList = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
		
		if (fileList == null || fileList.length == 0) {
			System.out.println("There is no .txt file found in the directory.");
            return;
		}
		
		for (File file : fileList) {
			searchWordInFile(wordToSearch, file);
		}
	}

	private static void searchWordInFile(String wordToSearch, File file) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String lineContent;
			int lineNumber = 0;
			boolean isFound = false;
			 while ((lineContent = bufferedReader.readLine()) != null) {
                 lineNumber++;
                 if (lineContent.contains(wordToSearch)) {
                    System.out.printf("File: %s, Line: %d, Content: %s%n", file.getName(), lineNumber, lineContent);
                    isFound = true;
                 }
            }
			 if (!isFound) {
                 System.out.printf("The word to search '%s' not found in file: %s%n", wordToSearch, file.getName());
             }
		} catch (IOException e) {
			System.out.println("There is an exception happened when searching " + wordToSearch 
					+ " in the file " + file.getName() + ": " + e.getMessage());
		}
	}
}
