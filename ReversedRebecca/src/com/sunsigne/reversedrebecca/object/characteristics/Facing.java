package com.sunsigne.reversedrebecca.object.characteristics;

public interface Facing {

	////////// FACING ////////////

	public DIRECTION getFacing();

	public void setFacing(DIRECTION facing);

	public default DIRECTION getOppositeFacing() {
		return getFacing().getOpposite();
	}

	public static String getAxisName(boolean horizontal) {
		return horizontal ? "horizontal" : "vertical";
	}

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

		public DIRECTION getOpposite() {
			switch (this) {
			case LEFT:
				return DIRECTION.RIGHT;
			case RIGHT:
				return DIRECTION.LEFT;
			case UP:
				return DIRECTION.DOWN;
			case DOWN:
				return DIRECTION.UP;
			default:
				return DIRECTION.NULL;
			}
		}
	}

}
