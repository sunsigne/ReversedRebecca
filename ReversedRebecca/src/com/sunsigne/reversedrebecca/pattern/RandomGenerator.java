package com.sunsigne.reversedrebecca.pattern;

import java.util.Random;

public class RandomGenerator {

	public int getIntBetween(int a, int b) {
		return a + new Random().nextInt(b - a + 1);
	}

	public boolean getBoolean() {
		return new Random().nextBoolean();
	}
}
