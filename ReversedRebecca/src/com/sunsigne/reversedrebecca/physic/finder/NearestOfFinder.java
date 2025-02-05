package com.sunsigne.reversedrebecca.physic.finder;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class NearestOfFinder {

	public Position getPos(PathSearcher searcher, GameObject object) {
		if (object instanceof CollisionReactor == false)
			return object;

		CollisionReactor wall = (CollisionReactor) object;
		if (wall.isBlockingPath() == false)
			return object;

		int x = new TilePos().getTilePos(object.getX(), object.getSize());
		int y = new TilePos().getTilePos(object.getY(), object.getSize());

		Position left = createPosition(x, y, object.getWidth(), DIRECTION.LEFT);
		Position right = createPosition(x, y, object.getWidth(), DIRECTION.RIGHT);
		Position up = createPosition(x, y, object.getHeight(), DIRECTION.UP);
		Position down = createPosition(x, y, object.getHeight(), DIRECTION.DOWN);

		DIRECTION path_left = new PathFinder(searcher, left).getPath();
		DIRECTION path_right = new PathFinder(searcher, right).getPath();
		DIRECTION path_up = new PathFinder(searcher, up).getPath();
		DIRECTION path_down = new PathFinder(searcher, down).getPath();

		var list = new GameList<Position>(LISTTYPE.ARRAY);

		if (path_left != DIRECTION.NULL)
			list.addObject(left);
		if (path_right != DIRECTION.NULL)
			list.addObject(right);
		if (path_up != DIRECTION.NULL)
			list.addObject(up);
		if (path_down != DIRECTION.NULL)
			list.addObject(down);

		if (list.getList().isEmpty())
			return object;

		if (list.getList().size() == 1)
			return list.getList().get(0);

		if (list.getList().size() == 1)
			return list.getList().get(0);

		int shortest_distance = calculShortestDistance(list, searcher);

		for (Position tempPosition : list.getList()) {
			if (shortest_distance == calculDistance(searcher, tempPosition))
				return tempPosition;
		}

		return object;
	}

	private int calculShortestDistance(GameList<Position> list, PathSearcher searcher) {
		int shortest_distance = Integer.MAX_VALUE;

		for (Position tempPosition : list.getList()) {
			int distance = calculDistance(searcher, tempPosition);

			if (shortest_distance > distance)
				shortest_distance = distance;
		}

		return shortest_distance;
	}

	private int calculDistance(PathSearcher searcher, Position position) {
		int searcher_x = new TilePos().getTilePos(searcher.getX(), searcher.getSize());
		int searcher_y = new TilePos().getTilePos(searcher.getY(), searcher.getSize());

		int distance_x = Math.abs(position.getX() - searcher_x);
		int distance_y = Math.abs(position.getY() - searcher_y);

		return distance_x + distance_y;
	}

	private Position createPosition(int x, int y, int size, DIRECTION direction) {

		Position position = new Position() {

			@Override
			public int getX() {
				switch (direction) {

				case LEFT:
					return x - size;
				case RIGHT:
					return x + size;
				case UP:
					return x;
				case DOWN:
					return x;
				case NULL:
					return x;
				}
				return x;
			}

			@Override
			public int getY() {
				switch (direction) {

				case LEFT:
					return y;
				case RIGHT:
					return y;
				case UP:
					return y - size;
				case DOWN:
					return y + size;
				case NULL:
					return y;
				}
				return y;
			}

			@Override
			public void setX(int x) {
			}

			@Override
			public void setY(int y) {
			}

			@Override
			public int getWidth() {
				return size;
			}

			@Override
			public int getHeight() {
				return size;
			}

		};

		return position;
	}

}
