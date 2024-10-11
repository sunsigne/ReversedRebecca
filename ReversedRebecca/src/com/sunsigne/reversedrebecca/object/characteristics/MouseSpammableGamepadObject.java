package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.SpammableGamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public interface MouseSpammableGamepadObject extends MouseObject, GamepadEvent {

	////////// MOUSE ////////////

	public default boolean isInPauseMenu() {
		return LAYER.MENU.getHandler().getList().isEmpty() == false;
	}
	
	////////// SPAMMABLE ////////////
	
	SpammableGamepadEvent[] getSpammables();
	
	void setSpammable(int index, SpammableGamepadEvent spammable);

	default SpammableGamepadEvent getSpammable(int index) {
		return getSpammables()[index];
	}

	default void createSpammable() {
		GenericListener onSpam = null;

		onSpam = () -> moveMouseFrom(-GameCursor.SPEED, 0);
		setSpammable(0, new SpammableGamepadEvent(getGamepadController(), ButtonEvent.LEFT, 1, 1, onSpam));
		onSpam = () -> moveMouseFrom(GameCursor.SPEED, 0);
		setSpammable(1, new SpammableGamepadEvent(getGamepadController(), ButtonEvent.RIGHT, 1, 1, onSpam));
		onSpam = () -> moveMouseFrom(0, -GameCursor.SPEED);
		setSpammable(2, new SpammableGamepadEvent(getGamepadController(), ButtonEvent.UP, 1, 1, onSpam));
		onSpam = () -> moveMouseFrom(0, GameCursor.SPEED);
		setSpammable(3, new SpammableGamepadEvent(getGamepadController(), ButtonEvent.DOWN, 1, 1, onSpam));
	}

	private void moveMouseFrom(int x, int y) {
		MousePos mousePos = new MousePos();
		new PresetMousePos(mousePos.getX() + x, mousePos.getY() + y).moveMouse();
	}
	
	////////// GAMEPAD ////////////
	
	@Override
	public default void buttonPressed(ButtonEvent e) {
		if(getSpammables() == null)
			return;
		
		if (isInPauseMenu())
			return;

		for (int index = 0; index < 4; index++)
			getSpammable(index).buttonPressed(e);
	}

	@Override
	public default void buttonReleased(ButtonEvent e) {
		if(getSpammables() == null)
			return;
		
		for (int index = 0; index < 4; index++)
			getSpammable(index).buttonReleased(e);
	}
	
}
