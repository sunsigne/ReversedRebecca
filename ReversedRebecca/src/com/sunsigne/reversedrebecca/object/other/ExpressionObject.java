package com.sunsigne.reversedrebecca.object.other;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Stunnable;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class ExpressionObject extends GameObject {

	private String name;
	
	public ExpressionObject(PiranhaObject object, String name) {
		super(object.getX(), object.getY() - Size.M);
		this.name = name;
		stunObject(object);
	}

	private void stunObject(PiranhaObject object) {
		if (object instanceof Stunnable == false)
			return;

		Stunnable stunnable = (Stunnable) object;
		stunnable.setStunned(true);
		new GameTimer(1 * Game.SEC, () -> stunnable.setStunned(false));
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "EXPRESSION";
		return clazz + " : " + name.toUpperCase();
	}

	////////// TICK ////////////

	private int time;
	private final int MAX_TIME = 50;

	@Override
	public void tick() {
		if (time < MAX_TIME)
			time++;

		if (time >= MAX_TIME)
			getHandler().removeObject(this);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/other/" + "expression_" + name);
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
