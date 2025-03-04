package com.sunsigne.reversedrebecca.object.loot;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Blinking;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.other.BonusText;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.piranha.condition.global.PickupLootCondition;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class LootObject extends GameObject implements SheetableImage, CollisionReactor, Blinking {

	public LootObject(int x, int y) {
		super(x + Size.XS / 2, y + Size.XS / 2, Size.S, Size.S);
		setBlinking();
	}

	public void pickup() {
		pickup(true);
	}

	public void pickup(boolean playSound) {
		removeObject();
		LAYER.WORLD_TEXT.addObject(new BonusText(getTextWhenLooted(), getX(), getY()));
		if (playSound)
			new SoundTask().playSoundIfCamera(this, "loot");
		new PickupLootCondition().registerValue(this);
		actionWhenLooted();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.COLLISIONNER;
	}
	
	////////// BLINKING ////////////

	private Cycloid<Boolean> blinking = new Cycloid<Boolean>(false, true);

	@Override
	public Cycloid<Boolean> getBlinking() {
		return blinking;
	}

	private int time;

	@Override
	public int getBlinkingTime() {
		return time;
	}

	@Override
	public void setBlinkingTime(int time) {
		this.time = time;
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
