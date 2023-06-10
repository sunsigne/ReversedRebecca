package com.sunsigne.reversedrebecca.object.loot;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.other.BonusText;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class LootObject extends GameObject implements CollisionReactor {

	public LootObject(int x, int y) {
		super(x + Size.XS / 2, y + Size.XS / 2, Size.S, Size.S);
		blink();
	}

	public void pickup() {
		removeObject();
		LAYER.WORLD_TEXT.addObject(new BonusText(getTextWhenLooted(), getX(), getY()));
		new SoundTask().playSoundIfCamera(this, "loot");
		actionWhenLooted();
	}

	////////// TICK ////////////

	private final int ACTIVE_TIME = 100;
	private int time;
	protected Cycloid<Boolean> blinking = new Cycloid<Boolean>(false, true);

	public void blink() {
		time = ACTIVE_TIME;
		blinking.setState(true);
	}

	@Override
	public void tick() {
		if (time < 0)
			return;

		time--;
		if(time % 10 == 0)
			blinking.cycle();
		if (time == 0)
			blinking.setState(false);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (!(detectorObject instanceof Player))
			return;

		collidingReaction(detectorObject, false, () -> pickup());
	}

	public abstract String getTextWhenLooted();

	public abstract void actionWhenLooted();

}
