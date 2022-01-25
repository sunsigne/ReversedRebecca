package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.extrabehaviors.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.OldConductor;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class TitleScreen implements Updatable {

	public TitleScreen() {
		LAYER.MENU.addObject(this);
		
		GenericListener onPress = () -> {};
		GenericListener onRelease = () -> new OldConductor().stopApp();
		
		quitButton = new ButtonObject("quit", 500, 500, 500, 100, onPress, onRelease);
		LAYER.MENU.addObject(quitButton);
		
		onPress = () -> {};
		onRelease = () -> 
		{
			LAYER.MENU.getHandler().clear();
			new World("lvl001");
		};
		
		quitButton = new ButtonObject("start", 500, 300, 500, 100, onPress, onRelease);
		LAYER.MENU.addObject(quitButton);
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

}
