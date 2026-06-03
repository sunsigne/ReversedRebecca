package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.animation.ExplosionAnimationObject;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class WallBombPuzzle extends WallPuzzle implements MouseUserEvent, GamepadEvent {

	public WallBombPuzzle(BufferedImage image, int x, int y) {
		super(image, x, y);
	}

	private boolean exploded;

	public boolean hasExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;

		if (exploded == false)
			return;

		new CameraShaker().shaking(SHAKE.LITTLE);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "explosion_medium");

		int gap = Size.XS / 2;
		LAYER.PUZZLE.addObject(new ExplosionAnimationObject(getX() + gap, getY() + gap, false));
		freeShoot();
	}

	private void freeShoot() {
		for (Updatable tempUpdatable : LAYER.PUZZLE.getHandler().getList()) {
			if (tempUpdatable instanceof BulletBombObject == false)
				continue;

			BulletBombObject bullet = (BulletBombObject) tempUpdatable;
			if (bullet.isCritical())
				return;

			bullet.setCount(bullet.getCount() + 1);
			return;
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (hasExploded() == false)
			super.render(g);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (hasExploded())
			return;

		if (isSelected() == false)
			return;

		setExploded(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (ButtonEvent.isActionButton(e))
			mousePressed(null);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

}
