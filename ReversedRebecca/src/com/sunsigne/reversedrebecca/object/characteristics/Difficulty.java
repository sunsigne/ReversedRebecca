package com.sunsigne.reversedrebecca.object.characteristics;

public interface Difficulty {

	////////// DIFFICULTY ////////////

	public LVL getDifficulty();

	public void setDifficulty(LVL difficulty);

	////////// LVL ////////////

	public enum LVL {
		NULL("null"), CYAN("cyan"), GREEN("green"), YELLOW("yellow"), ORANGE("orange"), RED("red"), PURPLE("purple");
		// purple is special difficulty, for boss or senario only

		private String name;

		LVL(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
		
		public LVL getNext() {
			switch (name) {
			case "null":
				return LVL.CYAN;
			case "cyan":
				return LVL.GREEN;
			case "green":
				return LVL.YELLOW;
			case "yellow":
				return LVL.ORANGE;
			case "orange":
				return LVL.RED;
			case "red":
				return LVL.PURPLE;
			case "purple":
				return LVL.PURPLE;
			}
			return null;
		}
	}

}
