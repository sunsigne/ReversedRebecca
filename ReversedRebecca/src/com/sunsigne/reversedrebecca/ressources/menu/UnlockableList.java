package com.sunsigne.reversedrebecca.ressources.menu;

import java.util.HashMap;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.menu.achievement.Achievement;
import com.sunsigne.reversedrebecca.ressources.menu.drawing.Drawing;
import com.sunsigne.reversedrebecca.ressources.menu.pixelart.PixelArt;

public class UnlockableList {

	////////// MAP OR LIST ////////////

	private static GameList<Achievement> achievement_list = new GameList<>(LISTTYPE.ARRAY);
	private static HashMap<String, Drawing> drawing_map = new HashMap<>();
	private static GameList<PixelArt> pixelart_list = new GameList<>(LISTTYPE.ARRAY);

	public static GameList<Achievement> getAchievementList() {
		return achievement_list;
	}

	public static HashMap<String, Drawing> getDrawingMap() {
		return drawing_map;
	}

	public static GameList<PixelArt> getPixelArtList() {
		return pixelart_list;
	}

}
