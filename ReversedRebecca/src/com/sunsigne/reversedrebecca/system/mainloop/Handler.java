package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Graphics;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
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
		super.removeObject(object);

		if (object != null)
			map.remove(object);
	}

	@Override
	public void removeObject(Updatable object) {
		removeObject(object, true);

		if (object != null)
			map.remove(object);
	}

	public void removeObject(Updatable object, boolean forthwith) {
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
		if (getList().size() > 0) {
			for (Updatable tempUpdatable : getList()) {
				tempUpdatable.destroyControls();
				map.remove(tempUpdatable);
			}
		}
		super.clear();
		hideRendering = false;
	}

	public void updateHandler() {
		var list = new GameList<Updatable>(LISTTYPE.ARRAY);
		list.getList().addAll(add_list.getList());

		for (Updatable tempObject : list.getList())
			addObject(tempObject);

		list.clear();
		list.getList().addAll(remove_list.getList());

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

		var list = new GameList<Updatable>(LISTTYPE.ARRAY);
		list.getList().addAll(getList());

		for (Updatable tempObject : list.getList()) {
			if (getList().contains(tempObject) == false)
				continue;

			tempObject.tick();

			var physic_list = new GameLimitedList<PhysicLaw>(LISTTYPE.ARRAY);
			physic_list.getList().addAll(PhysicList.getList().getList());

			for (PhysicLaw tempPhysicLaw : physic_list.getList()) {
				tempPhysicLaw.tick(tempObject);
			}
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

		var list = new GameList<Updatable>(LISTTYPE.ARRAY);
		list.getList().addAll(getList());

		for (Updatable tempObject : list.getList()) {

			// skip rendering if out of camera
			if (isCameraDependant()) {
				if (tempObject instanceof Position) {
					Position tempPosition = (Position) tempObject;

					if (CAMERA.isObjectVisible(tempPosition, true) == false)
						continue;
				}
			}

			renderDependency(g, true);

			var physic_list = new GameLimitedList<PhysicLaw>(LISTTYPE.ARRAY);
			physic_list.getList().addAll(PhysicList.getList().getList());

			for (PhysicLaw tempPhysicLaw : physic_list.getList()) {
				tempPhysicLaw.beforeObjectRender(g, tempObject);
			}

			tempObject.render(g);

			for (PhysicLaw tempPhysicLaw : physic_list.getList()) {
				tempPhysicLaw.afterObjectRender(g, tempObject);
			}

			renderDependency(g, false);
		}
	}

}
