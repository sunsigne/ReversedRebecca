package com.sunsigne.reversedrebecca.object.piranha;

import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class ChoiceObject extends InteractiveObject {

	public ChoiceObject(String name, int x, int y) {
		super(name, x, y);

		this.player = new PlayerFinder().getPlayer();
	}

	////////// INTERACTIVE ////////////

	@Override
	public void setTripleAction(TripleAction tripleAction) {
		super.setTripleAction(tripleAction);

		if (registeredTripleAction == null)
			this.registeredTripleAction = tripleAction;

		else
			updateChoice();
	}

	////////// TICK ////////////

	private Player player;
	private TripleAction registeredTripleAction;

	@Override
	public void tick() {

		// if there is no player, there is no choice (ofc)
		if (player == null)
			return;

		player.setCanInterract(true);
		player.setUserAllowedToMovePlayer(false);
	}

	private void updateChoice() {
		getHandler().removeObject(this);
		boolean playerCanInterract = Cutscene.isRunning() == false;
		player.setCanInterract(playerCanInterract);
		player.setUserAllowedToMovePlayer(true);
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {

	}

}
