package com.sunsigne.reversedrebecca;

import java.net.URL;

import javax.swing.ImageIcon;

import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class Infos {

	public static final String NAME = "Reversed Rebecca";

	public static final ImageIcon ICON = new ImageIcon("ressources/icon.png");
	
	public static final URL LOC = Game.class.getProtectionDomain().getCodeSource().getLocation();
}
