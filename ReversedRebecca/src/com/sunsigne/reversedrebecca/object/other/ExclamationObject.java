package com.sunsigne.reversedrebecca.object.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;

public class ExclamationObject extends ExpressionObject {

	public ExclamationObject(PiranhaObject object, String name) {
		super(object, name);
		ymin = getY() - shift;
		ymax = getY() + shift;
		setVelY(-shift / 3);
		new SoundTask().playSoundIfCamera(this, "jump");
	}

	////////// TICK ////////////

	private int ymin;
	private int ymax;
	private final int shift = 6;

	private void bump() {
		if (getY() <= ymin)
			setVelY(shift / 3);
		if (getY() >= ymax)
			setVelY(0);
	}

	@Override
	public void tick() {
		super.tick();
		bump();
	}

}
