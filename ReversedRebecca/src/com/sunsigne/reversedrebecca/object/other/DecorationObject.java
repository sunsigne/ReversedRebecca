package com.sunsigne.reversedrebecca.object.other;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class DecorationObject extends GameObject implements TickFree, SheetableImage {

	public DecorationObject(int x, int y, int w, int h, String name) {
		this(x, y, w, h, name, 0, 0);
	}

	public DecorationObject(int x, int y, int w, int h, String name, int offsetX, int offsetY) {
		super(x, y, w, h);
		this.name = name;
		setOffset(offsetX, offsetY);
	}

	////////// NAME ////////////

	private String name;

	public String getName() {
		return name;
	}

	protected String getPath() {
		return "textures/other/decoration/";
	}

	@Override
	public String toString() {
		var clazz = "DECORACTION";
		return clazz + " : " + getName().toUpperCase();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.SHAKER;
	}

	////////// POSITION ////////////

	private int offsetX, offsetY;

	private void setOffset(int offsetX, int offsetY) {
		int pixel = 16;
		int ratio = Size.M / pixel;
		this.offsetX = offsetX * ratio;
		this.offsetY = offsetY * ratio;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetWidth() {
		return getWidth() / 6;
	}

	@Override
	public int getSheetHeight() {
		return getHeight() / 6;
	}

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage(getPath() + getName());
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() + offsetX, getY() + offsetY, getWidth(), getHeight(), null);
	}

}
