package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
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

	public void softRemoveObject(Updatable object) {
		if (object == null || !containsObject(object))
			return;

		getList().remove(object);
	}

	@Override
	public void removeObject(Updatable object) {
		if (object == null || !containsObject(object))
			return;

		object.destroyControls();
		getList().remove(object);
	}

	@Override
	public void clear() {
		if (getList().size() > 0) {
			for (Updatable tempUpdatable : getList()) {
				tempUpdatable.destroyControls();
			}
		}
		getList().clear();
		hideRendering = false;
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

		for (Updatable tempObject : getList()) {

			tempObject.tick();

			for (PhysicLaw tempPhysicLaw : PhysicList.getList().getList()) {
				tempPhysicLaw.tick(tempObject);
			}

			if (getList().iterator().hasNext() == false)
				break;
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

		for (Updatable tempObject : getList()) {

			renderDependency(g, true);

			for (PhysicLaw tempPhysicLaw : PhysicList.getList().getList()) {
				tempPhysicLaw.beforeObjectRender(g, tempObject);
			}

			tempObject.render(g);

			for (PhysicLaw tempPhysicLaw : PhysicList.getList().getList()) {
				tempPhysicLaw.afterObjectRender(g, tempObject);
			}

			renderDependency(g, false);

			if (getList().iterator().hasNext() == false)
				break;
		}
	}

}
