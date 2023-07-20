package com.sunsigne.reversedrebecca.ressources;

import java.net.URL;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class FilePath {

	public static final URL LOC = Game.class.getProtectionDomain().getCodeSource().getLocation();
	public static final String RESSOURCES_PATH = Infos.IS_DEV_VERSION ? "ressources/" : "";
	public static final String USERDATA_PATH = Infos.IS_DEV_VERSION ? "userdata/"
			: System.getenv("APPDATA").replace("\\", "/") + "/" + Infos.NAME + "/";

	public static final String ACHIEVEMENT = "achievement.txt";
	public static final String ACTION = "action.txt";
	public static final String BONUS_TEXT = "bonus_text.txt";
	public static final String MENU = "menu.txt";
	public static final String KEY = "key.txt";
	public static final String PUZZLE = "puzzle.txt";
	public static final String TECHTREE = "techtree.txt";
	public static final String TOOL = "tool.txt";
	
	public static final String TEST = "test";
	public static final String TUTORIAL = "tutorial";
	public static final String WARNING = "warning";
	public static final String LVL000 = "rebeccas_room_000";
	
}
