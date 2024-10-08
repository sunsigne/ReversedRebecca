package com.sunsigne.reversedrebecca.ressources.font;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import com.sunsigne.reversedrebecca.ressources.FilePath;

public class FontTask {

	public Font createNewFont(String path, float size) {

		Font font = null;

		try {
			File file = new File(FilePath.RESSOURCES_PATH + "font/" + path);
			font = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (Exception e) {
			e.printStackTrace();
			font = new Font("arial", 1, 55);
		}

		return font;
	}

}
