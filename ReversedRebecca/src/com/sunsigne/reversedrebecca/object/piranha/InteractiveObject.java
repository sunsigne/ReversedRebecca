package com.sunsigne.reversedrebecca.object.piranha;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.SpammableGamepadEvent;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class InteractiveObject extends PiranhaObject implements TickFree, RenderFree {

	public InteractiveObject(String name, int x, int y) {
		super(name, x, y);
		setStunned(true); // looks stupid, but allow to bypass the RoundToTileLaw

		createSpammable();
	}

	////////// PATH FINDER ////////////

	@Override
	public boolean mustFollowPath() {
		return false;
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

	////////// SPAMMABLE ////////////

	private SpammableGamepadEvent[] spammable;

	private void createSpammable() {
		spammable = new SpammableGamepadEvent[4];
		GenericListener onSpam = null;

		onSpam = () -> spamAction(spammable[0]);
		spammable[0] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.A, 30, 3, onSpam);
		onSpam = () -> spamAction(spammable[1]);
		spammable[1] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.B, 30, 3, onSpam);
		onSpam = () -> spamAction(spammable[2]);
		spammable[2] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.X, 30, 3, onSpam);
		onSpam = () -> spamAction(spammable[3]);
		spammable[3] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.Y, 30, 3, onSpam);

		if (getGamepadController().isPressed(ButtonEvent.A))
			spammable[0].spamButton(true);
		if (getGamepadController().isPressed(ButtonEvent.B))
			spammable[1].spamButton(true);
		if (getGamepadController().isPressed(ButtonEvent.X))
			spammable[2].spamButton(true);
		if (getGamepadController().isPressed(ButtonEvent.Y))
			spammable[3].spamButton(true);
	}

	private void spamAction(SpammableGamepadEvent spammable) {
		inputPressed(65535, spammable.getButtonEvent());
	}

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if(spammable == null)
			return;
		
		for (int index = 0; index < 4; index++)
			spammable[index].buttonPressed(e);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		if(spammable == null)
			return;
		
		for (int index = 0; index < 4; index++)
			spammable[index].buttonReleased(e);
	}

}
