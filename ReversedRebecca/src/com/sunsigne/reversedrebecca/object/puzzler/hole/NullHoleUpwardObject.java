package com.sunsigne.reversedrebecca.object.puzzler.hole;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.LayerSendable;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.living.player.GoUpAction;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class NullHoleUpwardObject extends HoleUpwardObject implements LayerSendable {

	public NullHoleUpwardObject(DIRECTION facing, int x, int y) {
		super(LVL.NULL, facing, x, y);
	}

	private NullHoleUpwardObject(DIRECTION facing, int x, int y, boolean onLight) {
		super(LVL.NULL, facing, x, y);
		this.onLight = onLight;
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

	@Override
	public Updatable getReplacementUpdatable() {
		return new NullHoleUpwardObject(getFacing(), getX(), getY(), true);
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

	////////// TEXTURE ////////////

	@Override
	public BufferedImage getImage() {
		if (onLight)
			return super.getImage();
		else
			return null;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (onLight)
			super.render(g);
	}

}
