package com.sunsigne.reversedrebecca.object.other;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class ComingSoonPanel extends GameObject implements TickFree {

	public ComingSoonPanel(int x, int y) {
		super(x, y);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "COMING SOON";
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + goal.getX() + "-" + goal.getY();
	}

	////////// TEXTURE ////////////

	private BufferedImage image;
	private BufferedImage image_fr;

	public BufferedImage getImage() {
		if (image == null || image_fr == null) {
			image = new ImageTask().loadImage("textures/other/" + "coming_soon");
			image_fr = new ImageTask().loadImage("textures/other/" + "coming_soon" + "_fr");
		}

		if (Language.getInstance().getLang().equalsIgnoreCase("french"))
			return image_fr;
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int w = 2 * 125;
		int y = 2 * 66;

		g.drawImage(getImage(), getX() + Size.XS / 2, getY() + Size.XS, w, y, null);
	}

}
