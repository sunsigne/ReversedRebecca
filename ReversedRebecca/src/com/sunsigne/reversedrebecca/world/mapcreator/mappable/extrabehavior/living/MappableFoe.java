package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.living;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.foe.Foe;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableFoe implements Mappable {

	private MappableFoe() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableFoe();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new Foe(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 255, 0 };
		return rgb;
	}
	
}
