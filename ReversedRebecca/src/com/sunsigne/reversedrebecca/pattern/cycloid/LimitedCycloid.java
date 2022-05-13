package com.sunsigne.reversedrebecca.pattern.cycloid;

public class LimitedCycloid<T> extends Cycloid<T> {

	@SuppressWarnings("unchecked")
	public LimitedCycloid(T... objects) {
		super(objects);
	}

	@Override
	public void cycle() {
		index++;
		if (index >= size)
			return;
		state = objects[index];
	}

}
