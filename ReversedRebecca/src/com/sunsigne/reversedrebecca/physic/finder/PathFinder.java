package com.sunsigne.reversedrebecca.physic.finder;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.PathPointObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.local.GoalCondition;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PathFinder implements Position {

	public PathFinder(PathSearcher searcher, Position goal, boolean allow_complex_path) {

		this.searcher = searcher;
		this.goal = goal;

		setX(searcher.getX());
		setY(searcher.getY());
		calculDistance();

		path = findPath(allow_complex_path);
	}

	private PathSearcher searcher;
	private Position goal;

	// WARNING ! This is not a pos ! This is the DISTANCE between searcher and goal
	private int tileX, tileY;

	private void calculDistance() {
		tileX = getTilePos(goal.getX()) - getX();
		tileY = getTilePos(goal.getY()) - getY();
	}

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
		this.x = getTilePos(x);
	}

	@Override
	public void setY(int y) {
		this.y = getTilePos(y);
	}

	private int getTilePos(int pos) {
		return new TilePos().getTilePos(pos, searcher.getSize());
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

	////////// PATH FINDER ////////////

	private DIRECTION path = DIRECTION.NULL;

	public DIRECTION getPath() {
		return path;
	}

	private boolean isGoalReached() {
		return tileX == 0 & tileY == 0;
	}

	private boolean isPathStraightHorizontal(boolean WOH) {
		return tileX != 0 & tileY == 0 & !WOH;
	}

	private boolean isPathStraightVertical(boolean WOV) {
		return tileX == 0 & tileY != 0 & !WOV;
	}

	private boolean isPathSigneCurveHV(boolean WOH, boolean WGV) {
		return tileX != 0 & tileY != 0 & !WOH & !WGV;
	}

	private boolean isPathSigneCurveVH(boolean WOV, boolean WGH) {
		return tileX != 0 & tileY != 0 & !WOV & !WGH;
	}

	private DIRECTION findPath(boolean allow_complex_path) {

		if (isGoalReached()) {
			searcher.setGoal(null);
			new GoalCondition().registerValue(searcher, goal);
			return path;
		}

		boolean WOH = wallOnTheWay(0, true); // wall from origin to goal moving horizontally
		boolean WOV = wallOnTheWay(0, false); // wall from origin to goal moving vertically
		boolean WGH = wallOnTheWay(tileY, true); // wall from gap to goal moving horizontally
		boolean WGV = wallOnTheWay(tileX, false); // wall from gap to goal moving vertically

		if (isPathStraightHorizontal(WOH) | isPathStraightVertical(WOV))
			return getStraightPath();

		if (isPathSigneCurveHV(WOH, WGV)) {
			tileY = 0;
			return getStraightPath();
		}

		if (isPathSigneCurveVH(WOV, WGH)) {
			tileX = 0;
			return getStraightPath();
		}

		if (!allow_complex_path)
			return DIRECTION.NULL;

		return findComplexePath();
	}

	private DIRECTION findComplexePath() {

		GameList<PathPointObject> valid_path_point_list = createValidPathPointList();

		if (valid_path_point_list.getList().isEmpty())
			return DIRECTION.NULL;

		boolean pathDoesExist = true;

		while (pathDoesExist) {

			DIRECTION tryPath = getPathFromSeacherToValidPoint(valid_path_point_list);

			if (tryPath != DIRECTION.NULL)
				return tryPath;

			pathDoesExist = expandingValidPoint(valid_path_point_list);
		}

		// occurs when there is definitively no existing path between searcher and goal
		return DIRECTION.NULL;
	}

	private DIRECTION getStraightPath() {

		if (tileX < 0)
			return DIRECTION.LEFT;
		if (tileX > 0)
			return DIRECTION.RIGHT;
		if (tileY < 0)
			return DIRECTION.UP;
		if (tileY > 0)
			return DIRECTION.DOWN;

		return DIRECTION.NULL;
	}

	private boolean wallOnTheWay(int from, boolean horizontal) {

		int range = horizontal ? tileX : tileY;
		Player player = null;

		while (range != 0) {

			GameObject object;

			if (horizontal)
				object = Handler.getObjectAtPos(searcher.getHandler(), getX() + range, getY() + from, Size.M);
			else
				object = Handler.getObjectAtPos(searcher.getHandler(), getX() + from, getY() + range, Size.M);

			if (object instanceof CollisionReactor) {
				CollisionReactor wall = (CollisionReactor) object;
				if (wall != goal) {
					if (wall instanceof Player)
						player = (Player) wall;
					else if (wall.isBlockingPath())
						return true;
				}
			}

			if (range > 0)
				range = range - Size.M;
			if (range < 0)
				range = range + Size.M;
		}

		if (player != null) {
			if (searcher instanceof PlayerAvoider)
				return ((PlayerAvoider) searcher).isPlayerBlockingAvoider();
			else
				return player.isBlockingPath();
		}
		return false;
	}

	private GameList<PathPointObject> createValidPathPointList() {

		var valid_path_point_list = new GameList<PathPointObject>(LISTTYPE.LINKED);

		// searching for all path points than can reach goal
		for (Updatable tempUpdatable : searcher.getHandler().getList()) {
			if (tempUpdatable instanceof PathPointObject == false)
				continue;

			PathPointObject tempPassPoint = (PathPointObject) tempUpdatable;
			PathFinder tempPathFinder = new PathFinder(tempPassPoint, searcher.getGoal(), false);

			if (tempPathFinder.getPath() != DIRECTION.NULL)
				valid_path_point_list.addObject(tempPassPoint);
		}
		return valid_path_point_list;
	}

	private DIRECTION getPathFromSeacherToValidPoint(GameList<PathPointObject> valid_path_point_list) {

		// searching if any valid path point is reachable by seacher
		for (PathPointObject tempPassPoint : valid_path_point_list.getList()) {

			PathFinder tempPathFinder = new PathFinder(searcher, tempPassPoint, false);

			if (tempPathFinder.getPath() != DIRECTION.NULL)
				return tempPathFinder.getPath();
		}
		return DIRECTION.NULL;
	}

	private boolean expandingValidPoint(GameList<PathPointObject> valid_path_point_list) {

		boolean there_are_more_paths = false;

		// creation of a copy list, to avoid concurrentModification
		GameList<PathPointObject> copy_list = new GameList<PathPointObject>(LISTTYPE.ARRAY);
		copy_list.getList().addAll(valid_path_point_list.getList());

		for (Updatable tempUpdatable : searcher.getHandler().getList()) {

			if (tempUpdatable instanceof PathPointObject == false)
				continue;

			PathPointObject tempPassPoint = (PathPointObject) tempUpdatable;

			if (valid_path_point_list.containsObject(tempPassPoint))
				continue;

			for (PathPointObject previousPassPoint : copy_list.getList()) {

				PathFinder tempPathFinder = new PathFinder(tempPassPoint, previousPassPoint, false);

				if (tempPathFinder.getPath() != DIRECTION.NULL) {
					valid_path_point_list.addObject(tempPassPoint);
					there_are_more_paths = true;
				}
			}
		}

		return there_are_more_paths;
	}

}
