package com.sunsigne.reversedrebecca.object.piranha;

import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.finder.InFrontOfFinder;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ChoiceObject extends InteractiveObject {

	public ChoiceObject(PiranhaObject object) {
		this(object, null);
	}

	public ChoiceObject(String highlight) {
		this(null, highlight);
	}

	private ChoiceObject(PiranhaObject object, String highlight) {
		super("CHOICE", 0, 0);

		this.player = new PlayerFinder().getPlayer();

		if (object != null)
			this.object = object;
		else
			registerHighlight(highlight);

		imitateHighlight(true);
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

		player.setCanInteract(true);
		player.setUserAllowedToMovePlayer(false);
		int[] pos = new InFrontOfFinder().getPos(player);
		setX(pos[0]);
		setY(pos[1]);
	}

	private void updateChoice() {
		removeObject();
		boolean playerCanInteract = Cutscene.isRunning() == false;
		player.setCanInteract(playerCanInteract);
		player.setUserAllowedToMovePlayer(true);
		imitateHighlight(false);
	}

	////////// HIGHLIGHT ////////////

	private PiranhaObject object;

	private void registerHighlight(String highlight) {
		if (player == null)
			return;

		var list = player.getHandler().getList();

		for (Updatable tempUpdatable : list) {
			if (tempUpdatable instanceof PiranhaObject == false)
				continue;

			PiranhaObject tempObject = (PiranhaObject) tempUpdatable;
			if (tempObject.getName().equalsIgnoreCase(highlight) == false)
				continue;

			object = tempObject;
			return;
		}
	}

	private void imitateHighlight(boolean active) {
		if (object == null)
			return;

		object.setForceHighlight(active);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {

	}

}
