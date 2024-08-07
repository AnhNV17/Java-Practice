package main.com.java.practice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SensetiveWordsFiltering {
	public static void main(String[] args) {
		String filePath = "src/utilities/Test_SensetiveWordsFiltering.txt";
        File myObj = new File(filePath);
        
        if (!myObj.exists() || !myObj.isFile()) {
            System.err.println("Invalid file: " + filePath);
            return;
        }
        
        try {
        	String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String filteredContent = filterSensitiveWords(content);
            System.out.println(filteredContent);
        } catch (IOException e) {
            System.err.println("There is an exception happened while reading the file: " + e.getMessage());
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
