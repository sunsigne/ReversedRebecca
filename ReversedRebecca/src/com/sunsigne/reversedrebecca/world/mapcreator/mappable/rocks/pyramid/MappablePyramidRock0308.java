package com.sunsigne.reversedrebecca.world.mapcreator.mappable.rocks.pyramid;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.RockPiranhaObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.rocks.RockSize;

public class MappablePyramidRock0308 implements Mappable {

	private MappablePyramidRock0308() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappablePyramidRock0308();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		var rock = new RockPiranhaObject(x, y);
		rock.setBounds(3, 8, RockSize.PYRAMID[0], RockSize.PYRAMID[1]);		
		return rock;
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 133, 138, 254 };
		return rgb;
	}

}
