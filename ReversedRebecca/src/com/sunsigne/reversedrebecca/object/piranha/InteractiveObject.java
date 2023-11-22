package com.sunsigne.reversedrebecca.object.piranha;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.SpammableGamepadEvent;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.world.World;

public class InteractiveObject extends PiranhaObject implements TickFree {

	public InteractiveObject(String name, int x, int y) {
		super(name, x, y);
		setStunned(true); // looks stupid, but allow to bypass the RoundToTileLaw

		createSpammable();
		loadImages();
	}

	////////// PATH FINDER ////////////

	@Override
	public boolean mustFollowPath() {
		return false;
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

	////////// SPAMMABLE ////////////

	private SpammableGamepadEvent[] spammable;

	private void createSpammable() {
		spammable = new SpammableGamepadEvent[4];
		GenericListener onSpam = null;

		onSpam = () -> spamAction(spammable[0]);
		spammable[0] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.A, 30, 3, onSpam);
		onSpam = () -> spamAction(spammable[1]);
		spammable[1] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.B, 30, 3, onSpam);
		onSpam = () -> spamAction(spammable[2]);
		spammable[2] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.X, 30, 3, onSpam);
		onSpam = () -> spamAction(spammable[3]);
		spammable[3] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.Y, 30, 3, onSpam);

		if (getGamepadController().isPressed(ButtonEvent.A))
			spammable[0].spamButton(true);
		if (getGamepadController().isPressed(ButtonEvent.B))
			spammable[1].spamButton(true);
		if (getGamepadController().isPressed(ButtonEvent.X))
			spammable[2].spamButton(true);
		if (getGamepadController().isPressed(ButtonEvent.Y))
			spammable[3].spamButton(true);
	}

	private void spamAction(SpammableGamepadEvent spammable) {
		inputPressed(65535, spammable.getButtonEvent());
	}

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (spammable == null)
			return;

		for (int index = 0; index < 4; index++)
			spammable[index].buttonPressed(e);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		if (spammable == null)
			return;

		for (int index = 0; index < 4; index++)
			spammable[index].buttonReleased(e);
	}

	////////// HIGHLIGHT ////////////

	@Override
	public boolean getHighlightCondition() {
		if (highlightImage == null)
			return false;

		return super.getHighlightCondition();
	}

	private int var;

	private String getVarName(int var) {
		if (var < 0)
			var = this.var;

		if (var == 0)
			return "";

		String num = String.valueOf(var);
		return "-VAR" + num;
	}

	////////// TEXTURE ////////////

	private BufferedImage highlightImage;

	private void loadImages() {
		loadHighlightVar(0);
	}

	public void loadHighlightVar(int var) {
		if (var >= 0)
			this.var = var;

		highlightImage = new ImageTask().loadImage(
				"maps/" + World.get().getMapName() + "/" + "highlights/" + getName() + getVarName(var), true);
	}

	////////// RENDER ////////////

	private int[] highlight_rect = new int[4];

	public void setHighlightRect(int x, int y, int width, int height) {
		int ratio = Size.M;
		this.highlight_rect[0] = x * ratio;
		this.highlight_rect[1] = y * ratio;
		this.highlight_rect[2] = (width - 1) * ratio;
		this.highlight_rect[3] = (height - 1) * ratio;
	}

	@Override
	public void render(Graphics g) {
		drawHighlight(g, highlightImage, highlight_rect[0], highlight_rect[1], highlight_rect[2], highlight_rect[3]);
	}

}
