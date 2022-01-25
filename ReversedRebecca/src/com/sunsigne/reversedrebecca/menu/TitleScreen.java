package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.OldConductor;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class TitleScreen implements Updatable {

	public TitleScreen() {
		LAYER.MENU.addObject(this);
		
		createPlayButton();
		createOptionsButton();		
		createQuitButton();
	}

	////////// BUTTONS ////////////

	private void createPlayButton() {
		GenericListener onPress = () -> startWorld();
		GenericListener onRelease = null;

		ButtonObject playButton = new TitleScreenButton("START", 710, 570, 500, 90, onPress, onRelease);
		LAYER.MENU.addObject(playButton);
	}	
	
	private void startWorld() {
		LAYER.MENU.getHandler().clear();
		new World("lvl001");
	}

	private void createOptionsButton() {
		GenericListener onPress = null;
		GenericListener onRelease = null;

		ButtonObject optionsButton = new TitleScreenButton("OPTIONS", 710, 740, 500, 90, onPress, onRelease);
		LAYER.MENU.addObject(optionsButton);
	}
	
	private void createQuitButton() {
		GenericListener onPress = () -> new OldConductor().stopApp();
		GenericListener onRelease = null;

		ButtonObject quitButton = new TitleScreenButton("QUIT", 710, 920, 500, 90, onPress, onRelease);
		LAYER.MENU.addObject(quitButton);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);
	}

}
