package com.sunsigne.reversedrebecca.object.puzzler;

public enum COLOR {

	////////// COLOR ////////////

	BLUE("blue", 1), GREEN("green", 2), WHITE("white", 3), BROWN("brown", 4), BROWN_SUGAR("brown_sugar", 5),
	GRAY("gray", 6), PURPLE("purple", 7), RED("red", 8);

	COLOR(String name, int num) {
		this.name = name;
		this.num = num;
	}

	private String name;

	public String getName() {
		return name;
	}

	private int num;

	public int getNum() {
		return num;
	}

	public COLOR getPrevious() {
		switch (name) {
		case "blue":
			return COLOR.RED;
		case "green":
			return COLOR.BLUE;
		case "white":
			return COLOR.GREEN;
		case "brown":
			return COLOR.WHITE;
		case "brown_sugar":
			return COLOR.BROWN;
		case "gray":
			return COLOR.BROWN_SUGAR;
		case "purple":
			return COLOR.GRAY;
		case "red":
			return COLOR.PURPLE;
		}
		return null;
	}

	public COLOR getNext() {
		switch (name) {
		case "blue":
			return COLOR.GREEN;
		case "green":
			return COLOR.WHITE;
		case "white":
			return COLOR.BROWN;
		case "brown":
			return COLOR.BROWN_SUGAR;
		case "brown_sugar":
			return COLOR.GRAY;
		case "gray":
			return COLOR.PURPLE;
		case "purple":
			return COLOR.RED;
		case "red":
			return COLOR.BLUE;
		}

		// should never occurs
		return null;
	}

}
