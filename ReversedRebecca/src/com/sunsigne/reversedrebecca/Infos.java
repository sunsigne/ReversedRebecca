package com.sunsigne.reversedrebecca;

import java.net.URL;

import javax.swing.ImageIcon;

import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class Infos {

	public static final String NAME = "Reversed Rebecca";

	// first is for release status - second is for the level - third is for the version
	public static final String VERSION = "alpha.1.1";

	public static final ImageIcon ICON = new ImageIcon("icon.ico");
	
	public static final URL LOC = Game.class.getProtectionDomain().getCodeSource().getLocation();
}
