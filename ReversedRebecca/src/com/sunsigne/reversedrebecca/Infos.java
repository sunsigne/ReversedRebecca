package com.sunsigne.reversedrebecca;

import java.net.URL;

import javax.swing.ImageIcon;

import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class Infos {

	public static final String NAME = "Reversed Rebecca";
	public static final String VERSION = "v.alpha.1.4.5";
	public static final boolean IS_DEV_VERSION = System.getProperty("java.class.path").contains("\\git\\");
	public static final ImageIcon ICON = new ImageIcon("icon.ico");

	public static final URL LOC = Game.class.getProtectionDomain().getCodeSource().getLocation();
	public static final String RESSOURCES_PATH = IS_DEV_VERSION ? "ressources/" : "";
	public static final String USERDATA_PATH = IS_DEV_VERSION ? "userdata/"
			: System.getenv("APPDATA").replace("\\", "/") + "/" + Infos.NAME + "/";

}
