package com.sunsigne.reversedrebecca.physic.debug;

import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;

public abstract class DebugMode extends Cycloid<Boolean> implements PhysicLaw {

	public DebugMode() {
		super(false, true);
		PhysicList.getList().addObject(this);
		index++;
	}

	private static int index = 0;
	private int local_index = index;

	public int getIndex() {
		return local_index;
	}

	public abstract int getNum();
	
	public abstract DebugMode getDebugMode();

	////////// NAME ////////////

	public abstract String getName();

}
