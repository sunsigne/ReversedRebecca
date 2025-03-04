package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class ButtonObject extends GameObject implements TickFree, Facing, MouseUserEvent {

	public ButtonObject(String text, int x, int y, int w, int h, GenericListener onPress, GenericListener onRelease) {
		super(x, y, w, h);
		this.text = text;
		
		this.onPress = onPress;
		this.onRelease = onRelease;
	}
	
	////////// NAME ////////////
	
	public String toString() {
		var clazz = "BUTTON ";
		return clazz + text.toUpperCase() + " : " + getX() + "-" + getY();
	}
		
	////////// BEHAVIOR ////////////

	private GenericListener onPress;
	private GenericListener onRelease;

	////////// FACING ////////////

	private DIRECTION facing = DIRECTION.NULL;

	@Override
	public DIRECTION getFacing() {
		return facing;
	}

	@Override
	public void setFacing(DIRECTION facing) {
		this.facing = facing;
	}

	////////// TEXT ////////////

	protected String text;

	public String getText() {
		return text;
	}
	
	////////// SOUND ////////////

	public String getSound() {
		return "button";
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.MENU;
	}
		
	////////// RENDER ////////////

	protected RECTSIZE rectsize = RECTSIZE.NORMAL;
	
	public RECTSIZE getRectsize() {
		return rectsize;
	}
	
	public void setRectsize(RECTSIZE rectsize) {
		this.rectsize = rectsize;
	}
	
	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		new SoundTask().playSound(SOUNDTYPE.SOUND, getSound());
		
		if (onPress != null)
			onPress.doAction();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isSelected() == false)
			return;

		if (onRelease != null)
			onRelease.doAction();
	}

}
