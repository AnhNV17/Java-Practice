package main.com.java.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SensetiveWordsFiltering {
	public static void main(String[] args) {
		String filePath = "src/test/com/java/practice/Test_SensetiveWordsFiltering.txt";
        File myObj = new File(filePath);
        
        if (!myObj.exists() || !myObj.isFile()) {
            System.err.println("Invalid file: " + filePath);
            return;
        }
        
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String filteredData = filterSensitiveWords(data);
                System.out.println(filteredData);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
	}

	private static String filterSensitiveWords(String line) {
		String[] sensitiveWords = { "sex", "fuck", "drug", "kill" };

		for (String word : sensitiveWords) {
	        String regex = "(?i)" + word;
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(line);

	        StringBuffer sb = new StringBuffer();
	        while (matcher.find()) {
	            String foundWord = matcher.group();
	            String replacedWord = foundWord.replaceAll("[aeiouAEIOU]", "*");
	            matcher.appendReplacement(sb, replacedWord);
	        }
	        matcher.appendTail(sb);
	        line = sb.toString();
		}
        return line;
	}
}
