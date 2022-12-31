package com.sunsigne.reversedrebecca.pattern;

import java.util.Random;

import com.sunsigne.reversedrebecca.pattern.list.GameList;

public class RandomGenerator {

	public int getIntBetween(int a, int b) {
		return a + new Random().nextInt(b - a + 1);
	}

	public boolean getBoolean() {
		return new Random().nextBoolean();
	}

	public boolean getBoolean(int pct_of_chance_for_true) {
		int rad = getIntBetween(1, 100);
		return rad <= pct_of_chance_for_true;
	}

	public String getRandomLetters(int lenght, String allowedChars) {
		char[] char_array = allowedChars.toCharArray();
		String word = "";

		for (int index = 0; index < lenght; index++) {
			// select a random char in the allowed chars
			int rad = getIntBetween(0, char_array.length - 1);
			String letter = String.valueOf(char_array[rad]);

			// add this random letter to the word
			word = word.concat(letter);
		}

		return word;
	}

	public String getString(String... words) {
		return words[getIntBetween(0, words.length - 1)];
	}

	public <T> T getElementFromList(GameList<T> list) {
		Random rad = new Random();
		T element = list.getList().get(rad.nextInt(list.getList().size()));
		return element;
	}

}
