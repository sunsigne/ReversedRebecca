package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.extrabehaviors.menu.ButtonObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.unchecked.system.OldConductor;
import com.sunsigne.reversedrebecca.world.LAYER;
import com.sunsigne.reversedrebecca.world.World;

public class TitleScreen implements Updatable, KeyboardEvent {

	public TitleScreen() {
		LAYER.GUI.addObject(this);
		
		GenericListener onPress = () -> {};
		GenericListener onRelease = () -> new OldConductor().stopApp();
		
		quitButton = new ButtonObject(500, 500, 500, 100, onPress, onRelease);
		LAYER.GUI.addObject(quitButton);
		
		onPress = () -> {};
		onRelease = () -> 
		{
			LAYER.GUI.getHandler().clear();
			World.getInstance().run();
		};
		
		quitButton = new ButtonObject(500, 300, 500, 100, onPress, onRelease);
		LAYER.GUI.addObject(quitButton);
	}
	
	////////// BUTTONS ////////////
	
	private ButtonObject playerButton;
	private ButtonObject quitButton;
	
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

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE)
			new OldConductor().stopApp();		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
