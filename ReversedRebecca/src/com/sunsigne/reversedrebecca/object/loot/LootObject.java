package com.sunsigne.reversedrebecca.object.loot;

import com.sunsigne.reversedrebecca.object.BonusText;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class LootObject extends GameObject implements CollisionReactor {

	public LootObject(int x, int y) {
		super(x + Size.XS / 2, y + Size.XS / 2, Size.S, Size.S);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (!(detectorObject instanceof Player))
			return;

		collidingReaction(detectorObject, false, () -> {
			getHandler().removeObject(this);
			LAYER.WORLD_TEXT.addObject(new BonusText(getTextWhenLooted(), getX(), getY()));
			actionWhenLooted();
		});
	}

	public abstract String getTextWhenLooted();

	public abstract void actionWhenLooted();

}
