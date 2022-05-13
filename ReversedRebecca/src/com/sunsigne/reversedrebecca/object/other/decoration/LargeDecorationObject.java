package com.sunsigne.reversedrebecca.object.other.decoration;

import com.sunsigne.reversedrebecca.system.Size;

public class LargeDecorationObject extends DecorationObject {

	public LargeDecorationObject(int x, int y, String name) {
		super(x, y, 2 * Size.M, 2 * Size.M, name);
	}

}
