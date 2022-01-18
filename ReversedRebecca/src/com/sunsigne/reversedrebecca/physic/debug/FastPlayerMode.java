package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.pattern.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class FastPlayerMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new FastPlayerMode();
	
	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	@Override
	protected void actionWhenTurnedOn() {
		Player player = new PlayerFinder().getPlayer();
		if(player != null) player.speed = 3 * player.speed;
	}

	@Override
	protected void actionWhenTurnedOff() {
		Player player = new PlayerFinder().getPlayer();
		if(player != null) player.speed = player.speed / 3;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {

	}
		
	////////// TEXTURE ////////////
	
	private BufferedImage img = new ImageTask().loadImage("textures/debug_fast_player_mode.png");
	
	@Override
	public BufferedImage getImage() {
		return img;
	}
		
	////////// RENDER ////////////
	
	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

	////////// KEYBOARD ////////////

	@Override
	protected int getKeyEvent() {
		return KeyEvent.VK_F1;
	}

}
