package com.sunsigne.reversedrebecca.object.characteristics;

public class Facing {

	////////// DIRECTION ////////////

	public enum DIRECTION {
		NULL("null", -1), LEFT("left", 0), RIGHT("right", 1), UP("up", 2), DOWN("down", 3);

		private String name;
		private int num;

		DIRECTION(String name, int num) {

			this.name = name;
			this.num = num;
		}

		public String getName() {
			return name;
		}
		
		public int getNum() {
			return num;
		}
	}

}
