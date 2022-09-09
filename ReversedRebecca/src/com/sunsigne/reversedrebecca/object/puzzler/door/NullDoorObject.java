package com.sunsigne.reversedrebecca.object.puzzler.door;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class NullDoorObject extends DoorObject {

	public NullDoorObject(int x, int y) {
		super(LVL.NULL, x, y);
		setDisabled(true);
	}

	////////// TICK ////////////

	private boolean isOpened;
	private boolean tryClosing;

	@Override
	public void tick() {
		if (tryClosing) {
			if (isOpened)
				new SoundTask().playSound(SOUNDTYPE.SOUND, "door_close");
			isOpened = false;
		}

		tryClosing = true;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (isOpened)
			return;

		super.render(g);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return isOpened == false;
	}
	
	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> {
			if (!isOpened)
				new SoundTask().playSound(SOUNDTYPE.SOUND, "door_open");
			isOpened = true;
			tryClosing = false;
		});
	}

}
