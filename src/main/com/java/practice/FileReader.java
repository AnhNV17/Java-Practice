package main.com.java.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	public static void main(String[] args) {
		String filePath = "src/utilities/Test_FileReading.txt";
		File myObj = new File(filePath);
	    Scanner myReader;
		try {
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
