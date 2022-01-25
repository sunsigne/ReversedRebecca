package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public abstract class ButtonObject extends GameObject implements MouseUserEvent {

	public ButtonObject(String text, int x, int y, int w, int h, GenericListener onPress, GenericListener onRelease) {
		super(x, y, w, h);
		this.text = text;
		
		this.onPress = onPress;
		this.onRelease = onRelease;
	}	

	////////// BEHAVIOR ////////////

	private GenericListener onPress;
	private GenericListener onRelease;

		
	////////// TEXT ////////////
	
	private String text;

	public String getText() {
		return text;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}
	
	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);
	
	@Override
	public MouseController getMouseController() {
		return  mouseController;
	}	
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (mouseOver(e, getRect())) {
			if(onPress != null)
				onPress.doAction();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (mouseOver(e, getRect())) {
			if(onRelease != null)
				onRelease.doAction();
		}
	}	

}
