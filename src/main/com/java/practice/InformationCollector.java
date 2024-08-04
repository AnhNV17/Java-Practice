package main.com.java.practice;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InformationCollector {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String[] questionList = {
				"Tên của bạn là gì?",
				"Ngày/tháng/năm sinh của bạn?",
				"Công việc của bạn là gì?"
		};
		
		String[] anserList = new String[questionList.length];
		
		for (int i = 0; i < questionList.length; i++) {
            System.out.println(questionList[i]);
            anserList[i] = scanner.nextLine();
        }
		
		String fileName = "PersonalInformation.txt";
		
		 try (BufferedWriter writer = new BufferedWriter(
	            new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
	        for (int i = 0; i < questionList.length; i++) {
	            writer.write(questionList[i] + " " + anserList[i]);
	            writer.newLine();
	        }
	        System.out.println("Successfully written on the file: " + fileName);
	    } catch (IOException e) {
	        System.out.println("There is an error when writting content data to the file.");
	        e.printStackTrace();
	    }
		 
		 scanner.close();
	}
}
