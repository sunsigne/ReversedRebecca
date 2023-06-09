package com.sunsigne.reversedrebecca.ressources;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class Options {

	private String file = "options.csv";
	private boolean userData = true;

	public void loadRessources() {
		if (new FileTask().doesExist(userData, file))
			return;

		var line = getOptionTemplate();
		String text = "";

		for (String tempLine : line.getList()) {
			text = text.concat(tempLine + System.getProperty("line.separator"));
		}

		new FileTask().write(file, text);
	}

	private GameList<String> getOptionTemplate() {
		var line = new GameList<String>(LISTTYPE.ARRAY);

		line.addObject("Language=french");
		line.addObject("Camera=dynamic");
		line.addObject("Texts=medium");
		line.addObject("Difficulty=normal");
		line.getList().add("");
		line.addObject("/////KEYS/////");
		line.getList().add("");
		line.addObject("Up=Z:90");
		line.addObject("Down=S:83");
		line.addObject("Left=Q:81");
		line.addObject("Right=D:68");
		line.addObject("Dialogue=SPACE:32");
		line.addObject("Action1=E:69");
		line.addObject("Action2=R:82");
		line.addObject("Action3=F:70");
		line.getList().add("");
		line.addObject("/////VOLUME/////");
		line.getList().add("");
		line.addObject("Main=1.0");
		line.addObject("Music=1.0");
		line.addObject("Sound=1.0");
		line.addObject("Voice=1.0");

		return line;
	}

}
