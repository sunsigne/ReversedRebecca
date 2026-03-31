package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.object.characteristics.Position;

public interface NaveMesh extends Position {

	default boolean isImmutable() {
		return true;
	}
	
}
