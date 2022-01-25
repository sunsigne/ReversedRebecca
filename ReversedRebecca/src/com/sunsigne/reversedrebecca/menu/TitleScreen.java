package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.extrabehaviors.buttons.ButtonObject;
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
		createQuitButton();
	}

	////////// BUTTONS ////////////

	private void createPlayButton() {
		GenericListener onPress = () -> startWorld();
		GenericListener onRelease = null;

		ButtonObject playButton = new ButtonObject("start", 500, 300, 500, 90, onPress, onRelease);
		LAYER.MENU.addObject(playButton);
	}	
	
	private void startWorld() {
		LAYER.MENU.getHandler().clear();
		new World("lvl001");
	}

	private void createQuitButton() {
		GenericListener onPress = () -> new OldConductor().stopApp();
		GenericListener onRelease = null;

		ButtonObject quitButton = new ButtonObject("quit", 500, 500, 500, 90, onPress, onRelease);
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
