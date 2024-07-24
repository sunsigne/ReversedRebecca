package com.sunsigne.reversedrebecca.ressources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.sunsigne.reversedrebecca.pattern.FormattedString;

public class FileTask {

	////////// USEFUL ////////////

	public boolean doesExist(boolean userData, String path) {
		String folder = userData ? FilePath.USERDATA_PATH : FilePath.RESSOURCES_PATH;
		File file = new File(folder + path);
		return file.exists();
	}

	public boolean isEmptyFile(boolean userData, String path) {
		String folder = userData ? FilePath.USERDATA_PATH : FilePath.RESSOURCES_PATH;
		Path path0 = Paths.get(folder + path);
		try {
			return Files.size(path0) != 0L;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void delete(boolean userData, String path) {
		String folder = userData ? FilePath.USERDATA_PATH : FilePath.RESSOURCES_PATH;
		File file = new File(folder + path);
		if (file.exists())
			file.delete();
	}

	////////// READ ////////////

	// userData determine the folder to search :
	// true -> Programme File / false -> AppData/Roaming
	public String read(boolean userData, String path) {
		return read(userData, null, path);
	}

	public String read(boolean userData, String valueToRead, String path) {

		String folder = userData ? FilePath.USERDATA_PATH : FilePath.RESSOURCES_PATH;
		File file = new File(folder + path);
		Scanner scan = null;
		String content = "";
		boolean txt = path.contains(".txt");

		try {
			if (file.exists()) {
				scan = new Scanner(file, "UTF-8");
				boolean flag = false;

				// read the whole file
				if (valueToRead == null) {
					while (scan.hasNextLine()) {
						if (!flag) {
							content = content.concat(getFormattedText(txt, scan.nextLine()));
							flag = true;
						} else
							content = content
									.concat(System.getProperty("line.separator") + getFormattedText(txt, scan.nextLine()));
					}
				}

				// read one specific value
				else {
					while (scan.hasNextLine()) {
						String line = getFormattedText(txt, scan.nextLine());
						if (line.split("=")[0].equalsIgnoreCase(valueToRead))
							content = line.split("=")[1];
					}
				}

				scan.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}

	private String getFormattedText(boolean txt, String text) {
		String formatted_text = text;
		formatted_text = new FormattedString().replaceSpace(txt, formatted_text);
		formatted_text = new FormattedString().replaceValues(formatted_text);
		return formatted_text;
	}

	////////// WRITE ////////////

	// it is impossible to write in Programmes Files -> userData is always true here
	// to write something, keep in mind that "readable space" must be writted "#"
	public void write(String path, String text) {
		write(null, path, text);
	}

	public void write(String valueToReplace, String path, String text) {
		File file = new File(FilePath.USERDATA_PATH + path);
		String fileContent = read(true, path);
		String[] alllines = fileContent.split(System.getProperty("line.separator"));
		int size = alllines.length;

		FileWriter writer = null;

		try {
			writer = new FileWriter(file);

			// write the whole file
			if (valueToReplace == null) {
				writer.write(text);
			}

			// write one specific value
			else {
				for (int i = 0; i < size; i++) {
					if (alllines[i].split("=")[0].equalsIgnoreCase(valueToReplace))
						writer.write(alllines[i].split("=")[0] + "=" + text + System.getProperty("line.separator"));
					else
						writer.write(alllines[i].replace(" ", "#") + System.getProperty("line.separator"));
				}
			}

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
