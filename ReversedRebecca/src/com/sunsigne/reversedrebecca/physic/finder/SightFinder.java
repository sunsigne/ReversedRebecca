package com.sunsigne.reversedrebecca.physic.finder;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class SightFinder implements Position {

	public SightFinder(GameObject observer, GameObject goal) {
		this.observer = observer;
		this.goal = goal;

		setX(observer.getX());
		setY(observer.getY());
	}

	private GameObject observer;
	private GameObject goal;

	////////// POSITION ////////////

	private int x, y;

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	private int getTilePos(int pos) {
		return new TilePos().getTilePos(pos, observer.getSize());
	}

	////////// SIZE ////////////

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	////////// USEFUL ////////////

	public boolean isGoalInSight() {

		if (goal == null)
			return false;

		// false if not the same layer
		if (observer.getHandler() != goal.getHandler())
			return false;

		float distance;

		do {
			int diffX = getX() - (goal.getX());
			int diffY = getY() - (goal.getY());
			distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

			// draw a line between the observer and the goal
			setX(getX() + Math.round((-1 / distance) * diffX));
			setY(getY() + Math.round((-1 / distance) * diffY));

			// if a "wall" (blocking sight object) is on the way, return false
			GameList<GameObject> wall_list = Handler.getObjectsAtPos(observer.getHandler(), getTilePos(getX()), getTilePos(getY()),
					observer.getSize(), false);

			for(GameObject tempWall : wall_list.getList()) {
				if (tempWall instanceof CollisionReactor == false)
					continue;
				if (((CollisionReactor) tempWall).isBlockingSight()) {
					return false;
				}
			}

		} while (distance != 0);

		return true;
	}

}
