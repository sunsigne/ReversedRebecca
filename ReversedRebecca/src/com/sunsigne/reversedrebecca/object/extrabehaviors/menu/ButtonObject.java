package com.sunsigne.reversedrebecca.object.extrabehaviors.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;

public class ButtonObject extends ExtraBehaviorsObject {

	public ButtonObject(int x, int y, int w, int h, GenericListener onPress, GenericListener onRelease) {
		super(x, y, w, h);
		
		this.onPress = onPress;
		this.onRelease = onRelease;
	}

	////////// BEHAVIOR ////////////

	private GenericListener onPress;
	private GenericListener onRelease;
	
	////////// RENDER ////////////
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
	////////// MOUSE ////////////
	
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
			if(onPress != null)
				onRelease.doAction();
		}
	}	

}
