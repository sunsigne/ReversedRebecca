package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class Cutscene implements Updatable {

	////////// CUTSCENE ////////////

	private static boolean running;

	public static boolean isRunning() {
		return running;
	}

	public void start() {
		running = true;
		LAYER.GUI.addObject(this);
		new PlayerFinder().setUserAllowedToControlPlayer(false);
		new PlayerFinder().roundToTilePlayer();
	}

	public void stop(boolean delay) {
		Player player = new PlayerFinder().getPlayer();
		if (player != null)
			player.setMotionless();

		if (delay)
			new GameTimer(1 * Game.SEC, () -> running = false);
		else
			running = false;
	}

	////////// TICK ////////////

	private int blacking;
	private final int MAX_BLACKING = 90;
	private final int BLACKING_SPEED = 4;

	@Override
	public void tick() {
		if (running)
			growBlacking();
		else
			shrinkBlacking();
	}

	private void growBlacking() {
		if (blacking < MAX_BLACKING)
			blacking += BLACKING_SPEED;
	}

	private void shrinkBlacking() {
		if (blacking > 0)
			blacking -= BLACKING_SPEED;

		if (blacking <= 0)
			realStop();
	}

	private void realStop() {
		for (Updatable tempUpdatable : LAYER.GUI.getHandler().getList()) {
			if (tempUpdatable instanceof Cutscene)
				LAYER.GUI.getHandler().removeObject(tempUpdatable);
		}
		new PlayerFinder().setUserAllowedToControlPlayer(true);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.WIDHT, blacking);
		g.fillRect(0, Window.HEIGHT - blacking, Window.WIDHT, blacking);
	}
}
