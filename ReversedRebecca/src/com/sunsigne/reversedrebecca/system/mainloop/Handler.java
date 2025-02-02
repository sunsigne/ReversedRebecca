package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Graphics;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.physic.debug.FastWorldMode;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;

public class Handler extends GameList<Updatable> implements CameraDependency {

	public Handler(boolean cameraDependant) {
		super(LISTTYPE.LINKED);
		this.cameraDependant = cameraDependant;
		SuperHandler.getList().addObject(this);
	}

	////////// USEFULL ////////////

	public static GameList<GameObject> getObjectsAtPos(Handler layer, int x, int y, int size, boolean playerExluded) {

		GameList<GameObject> object_list = new GameList<GameObject>(LISTTYPE.LINKED);

		for (Updatable tempUpdatable : layer.getList()) {

			if (tempUpdatable instanceof GameObject == false)
				continue;

			GameObject tempObject = (GameObject) tempUpdatable;

			if (tempObject instanceof Player && playerExluded)
				continue;

			if (tempObject.getX() == x && tempObject.getY() == y) {
				object_list.addObject(tempObject);
				continue;
			}

			// walls may have big hitboxes
			if (tempObject instanceof Wall) {
				Wall tempWall = (Wall) tempObject;

				if (object_list.containsObject(tempWall))
					continue;

				if (tempWall.getX() == x) {
					if (tempWall.getY() > y)
						continue;

					for (int yy = y + size; yy < y + tempWall.getHeight(); yy = yy + size)
						if (y == yy)
							object_list.addObject(tempWall);
				}

				if (tempWall.getY() == y) {
					if (tempWall.getX() > x)
						continue;
					
					for (int xx = x + size; xx < x + tempWall.getWidth(); xx = xx + size)
						if (x == xx)
							object_list.addObject(tempWall);
				}
			}

			if (tempObject instanceof Player == false)
				continue;

			// player is counted "at pos" as soon as 1 pixel is on the tile
			Player player = (Player) tempObject;
			for (int xx = x - size + 1; xx < x + size - 1; xx++) {
				for (int yy = y - size + 1; yy < y + size - 1; yy++) {
					if (player.getX() == xx && player.getY() == yy)
						object_list.addObject(player);
				}
			}
		}
		return object_list;
	}

	public static GameList<GameObject> getObjectsAtPos(Handler layer, int pos, boolean pos_is_y_axis, int size,
			boolean playerExluded) {

		GameList<GameObject> object_list = new GameList<GameObject>(LISTTYPE.LINKED);

		for (Updatable tempUpdatable : layer.getList()) {

			if (tempUpdatable instanceof GameObject == false)
				continue;

			GameObject tempObject = (GameObject) tempUpdatable;

			int tempPos = pos_is_y_axis ? tempObject.getY() : tempObject.getX();
			if (tempPos == pos) {
				object_list.addObject(tempObject);
				continue;
			}

			if (tempObject instanceof Player == false)
				continue;

			if (playerExluded)
				continue;

			// player is counted "at pos" as soon as 1 pixel is on the tile
			Player player = (Player) tempObject;
			for (int ppos = pos - size + 1; ppos < pos + size - 1; ppos++) {
				if (tempPos == ppos)
					object_list.addObject(player);
			}
		}
		return object_list;
	}

	////////// MAP OR LIST ////////////

	private static HashMap<Updatable, Handler> map = new HashMap<>();
	private GameList<Updatable> add_list = new GameList<Updatable>(LISTTYPE.ARRAY);
	private GameList<Updatable> remove_list = new GameList<Updatable>(LISTTYPE.ARRAY);

	public static void updateHandlerMap(Handler handler, Updatable object) {
		if (object != null)
			map.put(object, handler);
	}

	public static Handler getHandler(Updatable object) {
		return map.get(object);
	}

	@Override
	public boolean containsObject(Updatable object) {
		return getHandler(object) == this;
	}

	public void addObject(Updatable object, boolean forthwith) {
		if (forthwith)
			addObject(object);
		else
			add_list.addObject(object);
	}

	@Override
	public void addObject(Updatable object) {
		super.addObject(object);
		updateHandlerMap(this, object);
	}

	public void softRemoveObject(Updatable object) {
		if (containsObject(object) == false)
			return;

		super.removeObject(object);

		if (object != null)
			map.remove(object);
	}

	@Override
	public void removeObject(Updatable object) {
		if (containsObject(object) == false)
			return;

		removeObject(object, true);

		if (object != null)
			map.remove(object);
	}

	public void removeObject(Updatable object, boolean forthwith) {
		if (containsObject(object) == false)
			return;

		if (object != null)
			object.destroyControls();

		if (forthwith) {
			super.removeObject(object);
			if (object != null)
				map.remove(object);
		} else
			remove_list.addObject(object);
	}

	@Override
	public void clear() {
		var list = new ListCloner().deepClone(this);

		if (list.getList().size() > 0) {
			for (Updatable tempUpdatable : list.getList()) {
				tempUpdatable.destroyControls();
				map.remove(tempUpdatable);
			}
		}
		super.clear();
		hideRendering = false;
	}

	public void updateHandler() {

		var list = new ListCloner().deepClone(add_list);
		for (Updatable tempObject : list.getList())
			addObject(tempObject);

		list = new ListCloner().deepClone(remove_list);
		for (Updatable tempObject : list.getList()) {
			super.removeObject(tempObject);
			map.remove(tempObject);
		}

		add_list.clear();
		remove_list.clear();
	}

	////////// CAMERA ////////////

	private boolean cameraDependant;

	@Override
	public boolean isCameraDependant() {
		return cameraDependant;
	}

	////////// TICK ////////////

	private boolean freezeTicking;

	public void setFreezeTicking(boolean freezeTicking) {
		this.freezeTicking = freezeTicking;
	}

	protected void tick() {
		if (freezeTicking)
			return;

		updateHandler();

		var list = new ListCloner().deepClone(this);

		for (Updatable tempObject : list.getList()) {
			if (getList().contains(tempObject) == false)
				continue;

			tempObject.tick();
			tempObject.applyPhysics(null, 1);
			FastWorldMode.debugMode.getDebugMode().tick(tempObject);
		}
	}

	////////// RENDER ////////////

	private boolean hideRendering;

	public void setHideRendering(boolean hideRendering) {
		this.hideRendering = hideRendering;
	}

	protected void render(Graphics g) {
		if (hideRendering)
			return;

		var list = new ListCloner().deepClone(this);
		for (Updatable tempObject : list.getList()) {

			// skip rendering if out of camera, except for walls
			if (isCameraDependant()) {
				if (tempObject instanceof Position && tempObject instanceof Wall == false) {
					Position tempPosition = (Position) tempObject;

					if (CAMERA.isObjectVisible(tempPosition, true) == false)
						continue;
				}
			}

			renderDependency(g, true);
			tempObject.applyPhysics(g, 2);
			tempObject.render(g);
			tempObject.applyPhysics(g, 3);
			renderDependency(g, false);
		}
	}

}
