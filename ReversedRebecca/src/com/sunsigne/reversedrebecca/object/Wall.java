package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;

public class Wall extends GameObject implements WallOptimizer, RenderFree, CollisionReactor {

	public Wall(int x, int y) {
		super(x, y);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "WALL";
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + goal.getX() + "-" + goal.getY();
	}

	////////// COLOR ////////////

	public enum COLOR {
		BROWN("brown"), BROWN_SUGAR("brown_sugar"), BLUE("blue"), GREEN("green"), WHITE("white");

		private String name;

		COLOR(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	////////// WALL OPTIMIZER ////////////

	private boolean playerTooFar;

	@Override
	public boolean getPlayerTooFar() {
		return playerTooFar;
	}

	@Override
	public void setPlayerTooFar(boolean playerTooFar) {
		this.playerTooFar = playerTooFar;
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return true;
	}

	@Override
	public boolean isBlockingPath() {
		return true;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

}
