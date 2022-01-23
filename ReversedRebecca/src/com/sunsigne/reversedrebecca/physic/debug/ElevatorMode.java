package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.pattern.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class ElevatorMode extends DebugMode {

	////////// PHYSIC LAW ////////////

	private static PhysicLaw physicLaw = new ElevatorMode();

	@Override
	public PhysicLaw getPhysicLaw() {
		return physicLaw;
	}

	////////// DEBUG MODE ////////////

	@Override
	public String getName() {
		return "debug_elevator_mode";
	}

	@Override
	protected void actionWhenTurnedOn() {

	}

	@Override
	protected void actionWhenTurnedOff() {

	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {

	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

	////////// KEYBOARD ////////////

	@Override
	protected int getKeyEvent() {
		return KeyEvent.VK_F1;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == getKeyEvent()) {
			cycle();
		}
		elevatorKey(key);
	}

	private void elevatorKey(int key) {
		if (!getState())
			return;

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;
		
		if (key == KeyEvent.VK_NUMPAD8)
			upPlayer();

		else if (key == KeyEvent.VK_NUMPAD2)
			downPlayer();
	}

	private void upPlayer() {
		putPlayerFromAtoB(LAYER.WORLD_CONTENT.getHandler(), LAYER.UP_WORLD_CONTENT.getHandler(), LAYER.UP_GROUND);
		putPlayerFromAtoB(LAYER.DOWN_WORLD_CONTENT.getHandler(), LAYER.WORLD_CONTENT.getHandler(), LAYER.GROUND);
	}

	private void downPlayer() {
		putPlayerFromAtoB(LAYER.WORLD_CONTENT.getHandler(), LAYER.DOWN_WORLD_CONTENT.getHandler(), LAYER.DOWN_GROUND);
		putPlayerFromAtoB(LAYER.UP_WORLD_CONTENT.getHandler(), LAYER.WORLD_CONTENT.getHandler(), LAYER.GROUND);
	}

	private void putPlayerFromAtoB(Handler handlerA, Handler handlerB, LAYER layer) {
		Player player = new PlayerFinder().getPlayer();

		if (player.getHandler() == handlerA) {
			handlerA.softRemoveObject(player);
			handlerB.addObject(player);
			World.get().setLayer(layer);
		}
	}

}
