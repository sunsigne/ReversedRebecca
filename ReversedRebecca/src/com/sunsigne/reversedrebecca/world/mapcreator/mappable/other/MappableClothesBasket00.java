package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.DecorationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableClothesBasket00 implements Mappable {

	private MappableClothesBasket00() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableClothesBasket00();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DecorationObject(x, y, "clothes_basket_00");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 253, 0, 252 };
		return rgb;
	}

}
