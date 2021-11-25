package com.sunsigne.reversedrebecca.world.mapcreator;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.Foe;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.object.gui.ExempleHP;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.unchecked.system.OldConductor;
import com.sunsigne.reversedrebecca.world.LAYER;

public class MapCreator implements KeyboardEvent {

	////////// ??? ////////////

	public void loadLevel() {
		/*
		 * Player.get().start(); new GUIHealth().start(); new GUIDebug().start();
		 * 
		 * new Wall(500, 300).start(); new Wall(900, 600).start();
		 * 
		 * new Foe(1500, 100).start(); new Foe(1500, 500).start();
		 */

		Player player = new Player(Window.WIDHT/2 - 50, Window.HEIGHT/2 - 50);
		
		LAYER.WORLD.addObject(new Wall(50, 50));		
		LAYER.WORLD.addObject(new Wall(Window.WIDHT - 150, 50));		
		LAYER.WORLD.addObject(new Wall(Window.WIDHT - 150, Window.HEIGHT - 150));		
		LAYER.WORLD.addObject(new Wall(50, Window.HEIGHT - 150));
		
		LAYER.WORLD.addObject(new Foe("Gamma", 700, 700));
		LAYER.WORLD.addObject(new Foe("Gamma", 1200, 800));
		
		LAYER.WORLD.addObject(player);
		
		LAYER.GUI.addObject(new ExempleHP());
		

		
		
	

		Game.getInstance().forceLoop();
	}

	////////// KEYBOARD ////////////

	KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE)
			new OldConductor().stopApp();

		if (key == KeyEvent.VK_R) {
			LAYER.WORLD.getHandler().clear();
			loadLevel();
		}			
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
