package com.sunsigne.reversedrebecca.object.loot;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;

public abstract class LootObject extends GameObject implements CollisionReactor {

	public LootObject(int x, int y) {
		super(x, y);
	}

}
