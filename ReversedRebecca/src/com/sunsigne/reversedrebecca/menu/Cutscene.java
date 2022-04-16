package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class Cutscene implements Updatable {

	////////// CUTSCENE ////////////

	private static boolean running;

	public void start() {
		running = true;
		LAYER.GUI.addObject(this);
		playerCanKeyMove(false);
	}

	public void stop() {
		running = false;
	}

	private void playerCanKeyMove(boolean playerCanKeyMove) {
		Player player = new PlayerFinder().getPlayer();

		if (player == null)
			return;

		if (playerCanKeyMove) {
			player.addBehavior(player.userCanKeyMove);
			player.addBehavior(player.hasPlayerSpeed);
		}

		else {
			player.removeBehavior(player.userCanKeyMove);
			player.removeBehavior(player.hasPlayerSpeed);
		}
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
		playerCanKeyMove(true);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.WIDHT, blacking);
		g.fillRect(0, Window.HEIGHT - blacking, Window.WIDHT, blacking);
	}
}
