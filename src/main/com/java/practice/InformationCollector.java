package main.com.java.practice;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            anserList[i] = scanner.nextLine().trim();
            if (anserList[i].isEmpty()) {
                System.out.println("Câu trả lời không được để trống, vui lòng điền lại!");
                i--;
            }
        }
		
		String fileName = "src/utilities/PersonalInformation.txt";
		try {
            Files.createDirectories(Paths.get(fileName).getParent());
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi tạo thư mục: " + e.getMessage());
        }
		
		 try (BufferedWriter writer = new BufferedWriter(
	            new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
	        for (int i = 0; i < questionList.length; i++) {
	            writer.write(questionList[i] + " " + anserList[i]);
	            writer.newLine();
	        }
	        System.out.println("Đã ghi thành công vào file: " + fileName);
	    } catch (IOException e) {
	        System.out.println("Đã xảy ra lỗi khi ghi file: " + e.getMessage());
	    }
		 
		 scanner.close();
	}
}
