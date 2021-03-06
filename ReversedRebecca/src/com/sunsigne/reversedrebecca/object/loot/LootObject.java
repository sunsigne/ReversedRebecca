package com.sunsigne.reversedrebecca.object.loot;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.other.BonusText;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class LootObject extends GameObject implements TickFree, CollisionReactor {

	public LootObject(int x, int y) {
		super(x + Size.XS / 2, y + Size.XS / 2, Size.S, Size.S);
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

		collidingReaction(detectorObject, false, () -> {
			getHandler().removeObject(this);
			LAYER.WORLD_TEXT.addObject(new BonusText(getTextWhenLooted(), getX(), getY()));
			new SoundTask().play(SOUNDTYPE.SOUND, "loot");
			actionWhenLooted();
		});
	}

	public abstract String getTextWhenLooted();

	public abstract void actionWhenLooted();

}
