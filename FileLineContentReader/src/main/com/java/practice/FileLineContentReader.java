package main.com.java.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLineContentReader {
	public static void main(String[] args) {
		if (args.length != 2) {
            System.out.println("Please enter the <fileName> and <lineNumber> to read the content of it!");
            return;
        }
		
		String fileName = args[0];
		int lineNumber;
		
		try {
			lineNumber  = Integer.parseInt(args[1]);
			if (lineNumber < 0) {
				System.out.println("Please enter the positive integer number for the line!");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("The lineNumber must be a positive integer.");
            return;
		}
		
		if (!Files.exists(Paths.get(fileName))) {
            System.out.println("The file does not exist: " + fileName);
            return;
        }
		
		try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
			String lineContent;
			int currentLine = 0;
			boolean isFound = false;
			
			while ((lineContent = bufferReader.readLine()) != null) {
				currentLine++;
				if (currentLine == lineNumber) {
					System.out.println("The content of line " + lineContent + " is " + lineContent);
					isFound = true;
					break;
				}
			}
			
			if (!isFound) {
				System.out.println("The lineNumber " + lineNumber + " is not existed on the file content!");
			}
		} catch (IOException e) {
			System.out.println("There is an exception happened when reading the file: " + e.getMessage());
		}
	}
}
