package com.sunsigne.reversedrebecca.ressources;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.sunsigne.reversedrebecca.Infos;

public class FileTask {

	////////// USEFUL ////////////

	public String getRessourcesPath() {
		File ressources = new File("ressources/");

		// occurs for dev app
		if (ressources.exists())
			return "ressources/";
		
		// occurs for player app
		return "";
	}
	
	public String getUserDataPath() {
		// dev sav
		if(getRessourcesPath().isBlank() == false)
			return "userdata/";
		
		// player save
		String userData = System.getenv("APPDATA") + "/" + Infos.NAME + "/";
		return userData.replace("\\", "/");
	}
	
	public boolean doesExist(String path) {
		File file = new File(getRessourcesPath() + path);
		return file.exists();
	}

	public void delete(String path) {
		File file = new File(getRessourcesPath() + path);
		if (file.exists())
			file.delete();
	}

	////////// READ ////////////

	public String read(String path) {
		return read(null, path);
	}

	public String read(String valueToRead, String path) {

		File file = new File(getRessourcesPath() + path);
		Scanner scan = null;
		String content = "";

		try {
			if (file.exists()) {
				scan = new Scanner(file, "UTF-8");
				boolean flag = false;

				// read the whole file
				if (valueToRead == null) {
					while (scan.hasNextLine()) {
						if (!flag) {
							content = content.concat(getFormattedSpace(scan.nextLine()));
							flag = true;
						} else
							content = content.concat(String.format("%n" + getFormattedSpace(scan.nextLine())));
					}
				}

				// read one specific value
				else {
					while (scan.hasNextLine()) {
						String line = getFormattedSpace(scan.nextLine());
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
	
	private String getFormattedSpace(String text) {
		String formattedText = text.replace(" ", "");
		return formattedText.replace("#", " ");
	}

	////////// WRITE ////////////

	public void write(String path, String text) {
		write(null, path, text);
	}

	// WARNING ! To write something, keep in mind that "readable space" must be writted "#"
	public void write(String valueToReplace, String path, String text) {
		File file = new File(getRessourcesPath() + path);
		String fileContent = read(path);
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
						writer.write(String.format(alllines[i].split("=")[0] + "=" + text + "%n"));
					else
						writer.write(String.format(alllines[i] + "%n"));

				}
			}

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
