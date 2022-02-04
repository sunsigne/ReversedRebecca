package com.sunsigne.reversedrebecca.ressources;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileTask {

	////////// USEFUL ////////////
	
	public boolean doesExist(String path) {
		File file = new File("ressources/" + path);
		return file.exists();
	}
	

	public void delete(String path) {
		File file = new File("ressources/" + path);
		if (file.exists())
			file.delete();
	}

	////////// READ ////////////
	
	public String read(String path) {
		return read(0, path);
	}

	public String read(int lineToRead, String path) {

		File file = new File("ressources/" + path);
		Scanner scan = null;
		String content = "";
		int count = 0;

		try {
			if (file.exists()) {
				scan = new Scanner(file, "UTF-8");
				boolean flag = false;

				// read the whole file
				if (lineToRead == 0) {
					while (scan.hasNextLine()) {
						if (!flag) {
							content = content.concat(scan.nextLine());
							flag = true;
						} else
							content = content.concat(String.format("%n" + scan.nextLine()));
					}
				}

				// read one specific line
				else {
					while (count != lineToRead) {
						if (lineToRead < 0)
							break;
						content = scan.nextLine();
						count++;
					}
				}

				scan.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}

	////////// WRITE ////////////
	
	public void write(String path, String text) {
		write(0, path, text);
	}

	public void write(int lineToReplace, String path, String text) {
		File file = new File("ressources/" + path);
		String fileContent = read(path);
		String[] alllines = fileContent.split(System.getProperty("line.separator"));
		int size = alllines.length;

		FileWriter writer = null;

		try {
			writer = new FileWriter(file);

			// write the whole file
			if (lineToReplace == 0) {
				writer.write(text);
			}

			// write one specific line
			else {
				for (int i = 0; i < size; i++) {
					if (i != lineToReplace)
						writer.write(String.format(alllines[i] + "%n"));
					else
						writer.write(String.format(text + "%n"));
				}
			}

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
