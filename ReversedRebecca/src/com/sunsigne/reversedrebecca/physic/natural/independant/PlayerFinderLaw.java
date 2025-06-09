package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerClone;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PlayerFinderLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new PlayerFinderLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// PLAYER ////////////

	private static Player player;

	public static Player getPlayer() {
		return player;
	}
	
	private static PlayerClone playerClone;

	public static PlayerClone getPlayerClone() {
		return playerClone;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof Player)
			player = (Player) object;
		
		if (object instanceof PlayerClone)
			playerClone = (PlayerClone) object;
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
