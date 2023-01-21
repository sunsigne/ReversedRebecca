package com.sunsigne.reversedrebecca.object.puzzler.hole;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.GroundLayerSendable;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class NullHoleObject extends HoleObject implements GroundLayerSendable {

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

	protected void loadTripleAction() {
		//String noActionText = new Translatable().getTranslatedText("HoleLoose", FilePath.ACTION);
		//Action digAction = new DigAction(this);
		//tripleAction = new TripleAction(noActionText, digAction, null, null);
	}

	////////// GROUND LAYER SENDABLE ////////////

	@Override
	public Updatable getReplacementUpdatable() {
		return new NullHoleObject(getX(), getY());
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
