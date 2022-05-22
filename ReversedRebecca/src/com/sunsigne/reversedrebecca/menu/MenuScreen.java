package com.sunsigne.reversedrebecca.menu;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class MenuScreen implements Updatable, TickFree {

	protected String file = "menu.csv";

	public MenuScreen(MENU menu) {
		etablishHierarchy(menu);
	}

	protected abstract void createNewMenu();

	////////// USEFUL ////////////

	public static void clearAll() {
		LAYER.MENU_MAIN.getHandler().clear();
		LAYER.MENU_NAV.getHandler().clear();
		LAYER.MENU_BOX.getHandler().clear();
	}

	////////// MENU ////////////

	public enum MENU {
		MAIN(LAYER.MENU_MAIN.getHandler()),
		NAV(LAYER.MENU_NAV.getHandler()),
		BOX(LAYER.MENU_BOX.getHandler());

		MENU(Handler handler) {
			this.handler = handler;
		}

		private Handler handler;

		public Handler getHandler() {
			return handler;
		}
	}

	// create a hierarchy between menus
	private void etablishHierarchy(MENU menu) {
		switch (menu) {

		case MAIN:
			refresh(LAYER.MENU_MAIN);
			LAYER.MENU_NAV.getHandler().clear();
			LAYER.MENU_BOX.getHandler().clear();
			break;

		case NAV:
			destroyControls(LAYER.MENU_MAIN);
			refresh(LAYER.MENU_NAV);
			LAYER.MENU_BOX.getHandler().clear();
			break;

		case BOX:
			destroyControls(LAYER.MENU_MAIN);
			destroyControls(LAYER.MENU_NAV);
			refresh(LAYER.MENU_BOX);
			break;

		default:
			break;
		}
	}

	private void refresh(LAYER layer) {
		if (layer.getHandler().getList().isEmpty()) {
			layer.addObject(this);
			createNewMenu();
		}

		else
			retablishControls(layer);
	}

	private void destroyControls(LAYER layer) {
		for (Updatable tempUpdatable : layer.getHandler().getList()) {
			tempUpdatable.destroyControls();
		}
	}

	private void retablishControls(LAYER layer) {
		for (Updatable tempUpdatable : layer.getHandler().getList()) {
			tempUpdatable.retablishControls();
		}
	}

}
