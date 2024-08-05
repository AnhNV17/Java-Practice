package main.com.java.practice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class FileRandomGeneratorImprovement {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final int LINE_LENGTH = 256;
	private static final int BYTES_CONVERTER = 1024 * 1024;
	private static final SecureRandom RANDOM = new SecureRandom();
	
	public static void main(String[] args) {
		if (args.length != 2) {
            System.out.println("Please enter the file name and file size to generate! <fileName> <fileSize>");
            return;
        }
		
		String fileName = args[0];		
		long fileSizeInMB;
		
		try {
			fileSizeInMB = Integer.parseInt(args[1]);
			if (fileSizeInMB < 10) {
				System.out.println("Please enter the fileSize again. It must be greater than 10.");
                return;
            }
		} catch(NumberFormatException e) {
			System.out.println("The fileSize must be a positive integer.");
            return;
		}
		
		long fileSizeInBytes = (long) fileSizeInMB * BYTES_CONVERTER;
		if(!hasEnoughDiskSpace(fileSizeInBytes)) {
			System.out.println("Not enough disk space to create the file.");
            return;
		}
		
		try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(fileName))) {
			long byteWritten = 0;
			while (byteWritten <= fileSizeInBytes) {
				String randomContentLine = generateRandomContentLine();
				bufferWriter.write(randomContentLine);
				bufferWriter.newLine();
				byteWritten += randomContentLine.length() + System.lineSeparator().length();
			}
			System.out.println("File is created successfully!");
		} catch (IOException e) {
			System.out.println("There is an exception happened when creating the file: " + e.getMessage());
		}
	}

	private static boolean hasEnoughDiskSpace(long neededSpace) {
		File file = new File(".");
        long usableSpace = file.getUsableSpace();
        return usableSpace >= neededSpace;
	}

	private static String generateRandomContentLine() {
		StringBuilder stringBuilder = new StringBuilder(LINE_LENGTH);
		for (int i = 0; i < LINE_LENGTH; i++) {
			stringBuilder = stringBuilder.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
		}
		return stringBuilder.toString();
	}
}
