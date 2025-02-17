package com.sunsigne.reversedrebecca.physic.natural.correlated;

import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;

public class CameraShaker {

	public enum SHAKE {
		TINY, LITTLE, MEDIUM, STRONG;
	}

	public void shaking(SHAKE shake) {
		switch (shake) {
		case STRONG:
			shaking(40);
			return;
		case MEDIUM:
			shaking(18);
			return;
		case LITTLE:
			shaking(10);
			return;
		case TINY:
			shaking(3);
			return;
		default:
			shaking(0);
		}
	}

	public void shaking(int shift) {
		PhysicLaw law = PhysicList.getList().getObject(new CameraShakingLaw());
		((CameraShakingLaw) law).shift = shift;
	}

}
