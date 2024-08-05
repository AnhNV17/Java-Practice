package main.com.java.practice;

import java.io.FileWriter;
import java.io.IOException;

public class FileWritter {
	public static void main(String[] args) {
		String content = "This is the content to be written on a file.";
		String fileName = "src/utilities/outputFile.txt";
		try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Successfully written on the file: " + fileName);
        } catch (IOException e) {
            System.out.println("There is an error when writting content data to the file.");
            e.printStackTrace();
        }
	}
}
