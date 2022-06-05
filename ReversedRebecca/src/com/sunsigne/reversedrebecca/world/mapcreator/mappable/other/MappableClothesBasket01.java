package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.DecorationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableClothesBasket01 implements Mappable {

	private MappableClothesBasket01() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableClothesBasket01();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DecorationObject(x, y, "clothes_basket_01");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 253, 0, 251 };
		return rgb;
	}

}