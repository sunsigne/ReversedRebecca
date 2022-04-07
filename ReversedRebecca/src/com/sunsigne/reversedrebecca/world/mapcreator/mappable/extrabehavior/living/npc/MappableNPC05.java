package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.living.npc;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.npc.NPC;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNPC05 implements Mappable {

	private MappableNPC05() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNPC05();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NPC("NPC-05", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 5, 0 };
		return rgb;
	}

}
