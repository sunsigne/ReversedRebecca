package com.sunsigne.reversedrebecca.object.extrabehaviors;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.SurVelocity;

public abstract class SuperExtraBehaviorsObject extends GameObject implements SurVelocity, Facing {

		public SuperExtraBehaviorsObject(String name, int x, int y) {
			super(x, y);
			this.name = name.toLowerCase();
		}

		////////// NAME ////////////

		private String name;

		public String getName() {
			return name;
		}

		////////// SURVELOCICY ////////////

		private int surVelX, surVelY;

		@Override
		public int getSurVelX() {
			return surVelX;
		}

		@Override
		public int getSurVelY() {
			return surVelY;
		}

		@Override
		public void setSurVelX(int surVelX) {
			this.surVelX = surVelX;
		}

		@Override
		public void setSurVelY(int surVelY) {
			this.surVelY = surVelY;
		}

		////////// FACING ////////////

		private DIRECTION facing = DIRECTION.DOWN;

		@Override
		public DIRECTION getFacing() {
			return facing;
		}

		@Override
		public void setFacing(DIRECTION facing) {
			this.facing = facing;
		}
		
}
