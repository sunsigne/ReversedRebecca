package com.sunsigne.reversedrebecca.physic.natural.independant;

import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;

public abstract class IndependantLaw implements PhysicLaw {

	public IndependantLaw() {
		PhysicList.getList().addObject(this);
	}
}
