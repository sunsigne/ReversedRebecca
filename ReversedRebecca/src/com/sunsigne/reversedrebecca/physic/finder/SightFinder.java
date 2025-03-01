package com.sunsigne.reversedrebecca.physic.finder;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
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

	public DIRECTION getDirectionOfGoalFromObserver() {
		if (goal == null)
			return DIRECTION.NULL;

		int diffX = getX() - (goal.getX());
		int diffY = getY() - (goal.getY());

		if (diffX == 0 && diffY == 0)
			return DIRECTION.NULL;

		if (Math.abs(diffX) > Math.abs(diffY)) {
			if (diffX > 0)
				return DIRECTION.LEFT;
			else
				return DIRECTION.RIGHT;
		} else {
			if (diffY > 0)
				return DIRECTION.UP;
			else
				return DIRECTION.DOWN;
		}
	}

	public boolean isGoalInSight() {

		if (goal == null)
			return false;

		// false if not the same layer
		if (observer.getHandler() != goal.getHandler())
			return false;

		float distance;

		var handler = observer.getHandler();

		do {
			int diffX = getX() - (goal.getX());
			int diffY = getY() - (goal.getY());
			distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

			if (isInTheBack(diffX, diffY))
				return false;

			// draw a line between the observer and the goal
			setX(getX() + Math.round((-1 / distance) * diffX));
			setY(getY() + Math.round((-1 / distance) * diffY));

			// if a "wall" (blocking sight object) is on the way, return false
			GameList<GameObject> wall_list = Handler.getObjectsAtPos(handler, getTilePos(getX()), getTilePos(getY()),
					observer.getSize(), false);
			removeObserverAndGoalFromList(wall_list);

			for (GameObject tempWall : wall_list.getList()) {
				if (tempWall instanceof CollisionReactor == false)
					continue;
				if (((CollisionReactor) tempWall).isBlockingSight()) {
					return false;
				}
			}

		} while (distance != 0);

		return true;
	}

	private boolean isInTheBack(int diffX, int diffY) {
		if (observer instanceof Facing == false)
			return false;

		Facing facer = (Facing) observer;
		DIRECTION direction = facer.getFacing();

		if (direction == DIRECTION.LEFT && diffX < 0)
			return true;
		if (direction == DIRECTION.RIGHT && diffX > 0)
			return true;
		if (direction == DIRECTION.UP && diffY < 0)
			return true;
		if (direction == DIRECTION.DOWN && diffY > 0)
			return true;

		return false;
	}

	private void removeObserverAndGoalFromList(GameList<GameObject> wall_list) {
		wall_list.removeObject(observer);
		wall_list.removeObject(goal);
	}

}
