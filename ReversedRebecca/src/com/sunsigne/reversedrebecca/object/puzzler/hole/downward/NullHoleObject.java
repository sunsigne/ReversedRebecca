package com.sunsigne.reversedrebecca.object.puzzler.hole.downward;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.LayerSendable;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.living.player.GoDownAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class NullHoleObject extends HoleObject implements LayerSendable {

	public NullHoleObject(DIRECTION facing, int x, int y) {
		super(LVL.NULL, facing, x, y);
	}

	private NullHoleObject(int x, int y) {
		super(LVL.NULL, DIRECTION.NULL, x, y);
		this.onGround = true;
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	@Override
	protected void loadTripleAction() {
		Action goDownAction = new GoDownAction(this, "enter_hole");
		tripleAction = new TripleAction(null, goDownAction, null, null);
	}

	////////// GROUND LAYER SENDABLE ////////////

	private NullHoleObject initial_hole;

	@Override
	public Updatable getReplacementUpdatable() {
		Updatable updatable = new NullHoleObject(getX(), getY());
		((PuzzlerObject) updatable).setDisabled(isDisabled());
		this.initial_hole = (NullHoleObject) updatable;
		return updatable;
	}

	////////// TICK ////////////

	private boolean onGround;

	@Override
	public void tick() {
		if (onGround)
			return;

		onGround = true;
		sendToGround();
	}

	////////// HIGHLIGHT ////////////

	@Override
	public boolean getHighlightCondition() {
		if(onGround == false)
			return false;
		
		if (getFacing() != DIRECTION.NULL && initial_hole != null)
			return initial_hole.getHighlightCondition();

		if (canPlayerInteract() == false)
			return false;

		if (getTripleAction() == null || getTripleAction().cannotDoAnyAction())
			return false;

		return true;
	}

	////////// TEXTURE ////////////

	public BufferedImage getImage() {
		if (getFacing() == DIRECTION.NULL)
			return null;

		return super.getImage();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (getFacing() == DIRECTION.NULL)
			return;

		super.render(g);
	}

}
