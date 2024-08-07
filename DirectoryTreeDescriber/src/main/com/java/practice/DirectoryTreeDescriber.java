package main.com.java.practice;

import java.io.File;

public class DirectoryTreeDescriber {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Please enter the <directoryPath> to describe the folder tree!");
            return;
		}
		
		String path = args[0];
		File directory = new File(path);
		if (!directory.isDirectory()) {
			System.out.println("The provided path is not a directory.");
            return;
		}
		
		describeDirectoryTree(directory, 0);
	}

	private static void describeDirectoryTree(File directory, int indent) {
		System.out.println(getIndentString(indent) + "+-- " + directory.getName());
		
		File[] listFile = directory.listFiles();
		if (listFile != null) {
			for (File file : listFile) {
	            if (file.isDirectory()) {
	            	describeDirectoryTree(file, indent + 1);
	            } else {
	                System.out.println(getIndentString(indent + 1) + "+-- " + file.getName());
	            }
	        }
		}
	}

	private static String getIndentString(int indent) {
		StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
        	stringBuilder.append("|   ");
        }
        return stringBuilder.toString();
	}
}
