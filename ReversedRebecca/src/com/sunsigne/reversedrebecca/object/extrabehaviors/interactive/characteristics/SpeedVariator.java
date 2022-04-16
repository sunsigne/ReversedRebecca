package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;

public interface SpeedVariator extends Velocity {

	////////// SPEED VARIATOR ////////////

	public int getSpeed();

	public void setSpeed(SPEEDTYPE speedType);

	////////// SPEED ////////////

	public enum SPEEDTYPE {
		SLOW("slow"), NORMAL("move"), FAST("fast");

		private String name;

		SPEEDTYPE(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
