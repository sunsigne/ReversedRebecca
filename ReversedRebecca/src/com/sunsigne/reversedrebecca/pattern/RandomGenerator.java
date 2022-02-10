package com.sunsigne.reversedrebecca.pattern;

import java.util.Random;

public class RandomGenerator {

	public int randomIntBetween(int a, int b) {
		return new Random().nextInt(a + b);
	}
	
}
