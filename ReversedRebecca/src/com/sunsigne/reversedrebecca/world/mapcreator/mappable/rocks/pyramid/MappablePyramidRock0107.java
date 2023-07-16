package com.sunsigne.reversedrebecca.world.mapcreator.mappable.rocks.pyramid;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.RockPiranhaObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.rocks.RockSize;

public class MappablePyramidRock0107 implements Mappable {

	private MappablePyramidRock0107() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappablePyramidRock0107();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		var rock = new RockPiranhaObject(x, y);
		rock.setBounds(1, 7, RockSize.PYRAMID[0], RockSize.PYRAMID[1]);		
		return rock;
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 131, 137, 254 };
		return rgb;
	}

}
