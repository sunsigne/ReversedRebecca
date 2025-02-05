package com.sunsigne.reversedrebecca.physic.finder;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.PathPointObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider.AVOIDERTYPE;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.condition.local.GoalCondition;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PathFinder implements Position {

	public PathFinder(PathSearcher searcher, Position goal) {
		this(searcher, searcher, goal, true, null);
	}

	private PathFinder(PathSearcher initial_searcher, PathSearcher searcher, Position goal, boolean allow_complex_path,
			Handler handler) {

		this.initial_searcher = initial_searcher;
		this.searcher = searcher;
		this.goal = goal;
		this.handler = handler;
		if (handler == null)
			this.handler = searcher.getHandler();

		setX(searcher.getX());
		setY(searcher.getY());
		calculDistance();
		path = findPath(allow_complex_path);

	}

	private PathSearcher initial_searcher;
	private PathSearcher searcher;
	private Position goal;
	private Handler handler;

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
		return goal.getX() - getX() == 0 & goal.getY() - getY() == 0;
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

		// goal is reached
		if (isGoalReached())
			return potentialNewPath();

		// straight path
		boolean WOH = wallOnTheWay(0, true); // wall from origin to goal moving horizontally
		boolean WOV = wallOnTheWay(0, false); // wall from origin to goal moving vertically

		if (isPathStraightHorizontal(WOH) | isPathStraightVertical(WOV))
			return getStraightPath();

		// single curved path
		boolean WGH = wallOnTheWay(tileY, true); // wall from gap to goal moving horizontally
		boolean WGV = wallOnTheWay(tileX, false); // wall from gap to goal moving vertically

		if (isPathSigneCurveHV(WOH, WGV)) {
			tileY = 0;
			return getStraightPath();
		}

		if (isPathSigneCurveVH(WOV, WGH)) {
			tileX = 0;
			return getStraightPath();
		}

		// complexe path
		if (!allow_complex_path)
			return DIRECTION.NULL;

		return findComplexePath();
	}

	private DIRECTION potentialNewPath() {
		searcher.setGoal(null);
		registerGoalCondition();

		if (searcher.getGoal() == null)
			return path;

		PathFinder pathFinder = new PathFinder(initial_searcher, searcher, searcher.getGoal(), true, handler);
		return pathFinder.getPath();
	}

	private void registerGoalCondition() {
		if (goal instanceof GoalObject == false || ((GoalObject) goal).doesTriggerGoalCondition())
			new GoalCondition().registerValue(searcher, goal);
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

		Player player = null;
		int range = horizontal ? tileX : tileY;

		if (range == 0)
			return false;

		// get all objects along the searched axis
		int pos = horizontal ? getY() : getX();
		GameList<GameObject> object_list = Handler.getObjectsAtPos(handler, pos + from, horizontal, Size.M, false);
		removeSearcherAndGoalFromList(object_list);

		// establish the first and last element encountered
		Position firstElement = range > 0 ? this : goal;
		Position secondElement = range < 0 ? this : goal;

		for (GameObject tempObject : object_list.getList()) {

			// object has no collision behaviors
			if (tempObject instanceof CollisionReactor == false)
				continue;

			// object is outside the trajectory
			boolean beforeElements = horizontal ? tempObject.getX() + tempObject.getWidth() - 1 < firstElement.getX()
					: tempObject.getY() + tempObject.getHeight() - 1 < firstElement.getY();
			boolean afterElements = horizontal ? tempObject.getX() - tempObject.getSize() + 1 > secondElement.getX()
					: tempObject.getY() - tempObject.getSize() + 1 > secondElement.getY();

			if (beforeElements | afterElements)
				continue;

			// object is player
			if (tempObject instanceof Player) {
				player = (Player) tempObject;
				continue; // player is a specific case;
			}

			CollisionReactor wall = (CollisionReactor) tempObject;

			// object" is blocking path
			if (wall.isBlockingPath()) {
				if (wall instanceof PlayerAvoider == false)
					return true;

				// searcher is the player and object is an npc that can be passed through
				PlayerAvoider avoider = (PlayerAvoider) wall;
				if (avoider.getPlayerAvoiderType() != AVOIDERTYPE.THROUGH
						|| initial_searcher instanceof Player == false)
					return true;
			}
		}

		if (player != null) {
			return isPlayerBlockingPath();
		}
		return false;
	}

	private void removeSearcherAndGoalFromList(GameList<GameObject> object_list) {
		if (initial_searcher instanceof GameObject)
			object_list.removeObject((GameObject) initial_searcher);

		if (searcher instanceof GameObject)
			object_list.removeObject((GameObject) searcher);

		if (goal instanceof GameObject)
			object_list.removeObject((GameObject) goal);
	}

	private boolean isPlayerBlockingPath() {
		if (initial_searcher instanceof PlayerAvoider)
			return ((PlayerAvoider) initial_searcher).isPlayerBlockingAvoider();
		return new PlayerFinder().getPlayer().isBlockingPath();
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
		initial_searcher.disabledPathFinder(Game.SEC);
		return DIRECTION.NULL;
	}

	private GameList<PathPointObject> createValidPathPointList() {

		var valid_path_point_list = new GameList<PathPointObject>(LISTTYPE.LINKED);

		// searching for all path points than can reach goal
		for (Updatable tempUpdatable : handler.getList()) {
			if (tempUpdatable instanceof PathPointObject == false)
				continue;

			PathPointObject tempPassPoint = (PathPointObject) tempUpdatable;
			PathFinder tempPathFinder = new PathFinder(initial_searcher, tempPassPoint, goal, false, handler);

			if (tempPathFinder.getPath() != DIRECTION.NULL)
				valid_path_point_list.addObject(tempPassPoint);
		}
		return valid_path_point_list;
	}

	private DIRECTION getPathFromSeacherToValidPoint(GameList<PathPointObject> valid_path_point_list) {

		// searching if any valid path point is reachable by seacher
		for (PathPointObject tempPassPoint : valid_path_point_list.getList()) {

			PathFinder tempPathFinder = new PathFinder(initial_searcher, searcher, tempPassPoint, false, handler);

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

		for (Updatable tempUpdatable : handler.getList()) {

			if (tempUpdatable instanceof PathPointObject == false)
				continue;

			PathPointObject tempPassPoint = (PathPointObject) tempUpdatable;

			if (valid_path_point_list.containsObject(tempPassPoint))
				continue;

			for (PathPointObject previousPassPoint : copy_list.getList()) {

				PathFinder tempPathFinder = new PathFinder(initial_searcher, tempPassPoint, previousPassPoint, false,
						handler);

				if (tempPathFinder.getPath() != DIRECTION.NULL) {
					valid_path_point_list.addObject(tempPassPoint);
					there_are_more_paths = true;
					break;
				}
			}
		}

		return there_are_more_paths;
	}

}
