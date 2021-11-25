package com.sunsigne.reversedrebecca.object.extrabehaviors;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;

public abstract class ExtraBehaviorsObject extends GameObject implements Behavior, Facing {

	public ExtraBehaviorsObject(String name, int x, int y) {
		super(x, y);
		this.name = name.toLowerCase();
	}

	////////// MAP OR LIST ////////////
	
	private GameLimitedList<Behavior> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public GameLimitedList<Behavior> getBehaviorList() {
		return list;
	}
	
	public void addBehavior(Behavior behavior) {
		getBehaviorList().addObject(behavior);
	}

	public void removeBehavior(Behavior behavior) {
		getBehaviorList().removeObject(behavior);
	}
	
	////////// BEHAVIOR ////////////
	
	private String name;
	
	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return this;
	}
	
	public String getName() {
		return name;
	}	
	
	//////////FACING ////////////
	
	private DIRECTION facing = DIRECTION.DOWN;
	
	@Override
	public DIRECTION getFacing() {
		return facing;
	}
	
	@Override
	public void setFacing(DIRECTION facing) {
		this.facing = facing;
	}	

	////////// TICK ////////////

	@Override
	public void tick() {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null)
				tempBehavior.tick();
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null)
				tempBehavior.render(g);
		}
	}

	////////// KEYBOARD ////////////

	KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null)
				tempBehavior.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null)
				tempBehavior.keyReleased(e);
		}
	}

}
