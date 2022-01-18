package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;

public class Handler extends GameList<Updatable> implements CameraDependency {

	public Handler(boolean cameraDependant) {
		super(LISTTYPE.LINKED);
		this.cameraDependant = cameraDependant;
		SuperHandler.getGameList().addObject(this);
	}
	
	////////// USEFULL ////////////
	
	public static GameObject getObjectAtPos(Handler layer, int x, int y) {

		for (Updatable tempUpdatable : layer.getList()) {

			if (!(tempUpdatable instanceof GameObject))
				continue;

			GameObject tempObject = (GameObject) tempUpdatable;
			if (tempObject.getX() == x && tempObject.getY() == y) {
				return tempObject;
			}
		}
		return null;
	}

	////////// MAP OR LIST ////////////

	public void softRemoveObject(Updatable object) {
		if (object == null || !cointainsObject(object))
			return;

		getList().remove(object);
	}

	@Override
	public void removeObject(Updatable object) {
		if (object == null || !cointainsObject(object))
			return;

		object.destroyControls();
		getList().remove(object);
	}

	@Override
	public void clear() {
		if(getList().size() > 0)
		{
			for (Updatable tempUpdatable : getList()) {
				tempUpdatable.destroyControls();
			}
		}		
		getList().clear();
	}

	////////// CAMERA ////////////

	private boolean cameraDependant;

	@Override
	public boolean isCameraDependant() {
		return cameraDependant;
	}

	////////// TICK ////////////

	protected void tick() {

		for (Updatable tempObject : getList()) {

			tempObject.tick();

			for (PhysicLaw tempPhysicLaw : PhysicList.getList().getList()) {
				tempPhysicLaw.tick(tempObject);
			}
		}
	}

	////////// RENDER ////////////

	protected void render(Graphics g) {

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
		}
	}

}
