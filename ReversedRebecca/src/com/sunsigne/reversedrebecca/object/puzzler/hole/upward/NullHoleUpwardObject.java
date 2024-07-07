package com.sunsigne.reversedrebecca.object.puzzler.hole.upward;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.LayerSendable;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.living.player.GoUpAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class NullHoleUpwardObject extends HoleUpwardObject implements LayerSendable {

	public NullHoleUpwardObject(DIRECTION facing, int x, int y) {
		super(LVL.NULL, facing, x, y);
	}

	private NullHoleUpwardObject(DIRECTION facing, int x, int y, boolean onLight) {
		super(LVL.NULL, facing, x, y);
		this.onLight = onLight;
		this.invisible = onLight;
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	protected void loadTripleAction() {
		Action goUpAction = new GoUpAction(this, "enter_hole");
		tripleAction = new TripleAction(null, goUpAction, null, null);
	}

	////////// GROUND LAYER SENDABLE ////////////

	private NullHoleUpwardObject initial_hole;

	@Override
	public Updatable getReplacementUpdatable() {
		Updatable updatable = new NullHoleUpwardObject(getFacing(), getX(), getY(), true);
		((PuzzlerObject) updatable).setDisabled(isDisabled());
		this.initial_hole = (NullHoleUpwardObject) updatable;
		return updatable;
	}

	////////// TICK ////////////

	private boolean onLight;

	@Override
	public void tick() {
		if (onLight)
			return;

		onLight = true;
		sendToLight();
	}

	////////// HIGHLIGHT ////////////

	@Override
	public boolean getHighlightCondition() {
		if (onLight == false)
			return false;

		if (getFacing() != DIRECTION.NULL && initial_hole != null)
			return initial_hole.getHighlightCondition();

		if (canPlayerInterfact() == false)
			return false;

		if (getTripleAction() == null || getTripleAction().cannotDoAnyAction())
			return false;

		return true;
	}

	////////// TEXTURE ////////////

	public BufferedImage getHighlightImage() {
		if (highlightImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + getName() + "_" + "highlight");
			highlightImage = getSheetSubImage(sheet, 2, getSheetRowCriterion(), getSheetWidth() + 2,
					getSheetHeight() + 2);
		}
		return highlightImage;
	}

	////////// RENDER ////////////

	private boolean invisible;

	@Override
	public void render(Graphics g) {
		if (onLight && invisible == false)
			super.render(g);
	}

}
