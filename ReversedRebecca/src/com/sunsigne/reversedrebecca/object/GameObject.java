package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.ressources.images.Texture;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public abstract class GameObject implements Velocity {

	public GameObject(int x, int y) {
		this(x, y, Size.M, Size.M);
	}

	public GameObject(int x, int y, int w, int h) {

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		initX = x;
		initY = y;
	}
	
	////////// DESTRUCTION ////////////
	
	public void destroyControls() {
		
		if(this instanceof KeyboardEvent) {
			KeyboardEvent keyboard = (KeyboardEvent) this;
			keyboard.getKeyBoardController().removeKeyListener();
		}
		
		if(this instanceof MouseUserEvent) {
			MouseUserEvent mouse = (MouseUserEvent) this;
			mouse.getMouseController().removeMouseListener();
		}
	}

	////////// POSITION ////////////

	private int x, y;

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	private int initX, initY;

	public int getInitX() {
		return initX;
	}

	public int getInitY() {
		return initY;
	}

	////////// SIZE ////////////

	private int w, h;

	@Override
	public int getWidth() {
		return w;
	}

	@Override
	public int getHeight() {
		return h;
	}

	////////// VELOCICY ////////////

	private int velX, velY;

	@Override
	public int getVelX() {
		return velX;
	}

	@Override
	public int getVelY() {
		return velY;
	}

	@Override
	public void setVelX(int velX) {
		this.velX = velX;
	}

	@Override
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
}
