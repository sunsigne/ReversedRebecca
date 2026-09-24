package com.sunsigne.reversedrebecca.object.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.menu.pixelart.PixelArt;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class PixelArtObject extends GameObject implements TickFree {

	public PixelArtObject(PixelArt pixelArt) {
		this(pixelArt, Window.WIDHT / 2 - 13 * Size.XS, -Size.L);
	}

	public PixelArtObject(PixelArt pixelArt, int x, int y) {
		super(x, y, 5 * Size.XL, Size.L);
		this.pixelArt = pixelArt;
		this.locked = pixelArt.isLocked();
	}

	private PixelArt pixelArt;
	private boolean locked;

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PIXEL ART";
		return clazz + " " + pixelArt.getName().toUpperCase() + " : " + getX() + "-" + getY();
	}

	////////// TEXT ////////////

	private String text;

	public String getText() {
		if (pixelArt.hasText(false) == false)
			text = "";

		if (text != null)
			return text;

		if (pixelArt.isLocked() == false) {
			text = new Translatable().getStrictTranslatedText(pixelArt.getName(), FilePath.PIXEL_ART);
			if (text.isEmpty())
				text = new Translatable().getTranslatedText(pixelArt.getName(), FilePath.PIXEL_ART);
			return text;
		}

		if (pixelArt.hasText(true) == false) {
			text = "";
			return text;
		}

		text = new Translatable().getStrictTranslatedText(pixelArt.getName() + "Locked", FilePath.PIXEL_ART);
		if (text.isEmpty())
			text = new Translatable().getTranslatedText(pixelArt.getName() + "Locked", FilePath.PIXEL_ART);

		return text;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.MENU;
	}

	////////// TEXTURE ////////////

	private final Font fontText = new FontTask().createNewFont("dogicabold.ttf", 18f);

	@Override
	public void render(Graphics g) {
		var image = locked ? pixelArt.getImageLocked() : pixelArt.getImage();
		int w = image.getWidth();
		int h = image.getHeight();

		if (pixelArt.getFacing() == DIRECTION.LEFT) {
			g.drawImage(image, getX(), getY(), w, h, null);
			drawText(g, w, h);
		}

		if (pixelArt.getFacing() == DIRECTION.RIGHT) {
			g.drawImage(image, getX() + getWidth() - w, getY(), w, h, null);
			drawText(g, -w, h);
		}
	}

	private void drawText(Graphics g, int w, int h) {
		if (getText().contains("@") == false) {
			drawLine(g, text, w, h);
			return;
		}

		String[] content = getText().split("@");
		for (int index = 0; index < content.length; index++) {
			drawLine(g, content[index], w, 60 * index);
		}

	}

	private void drawLine(Graphics g, String text, int w, int h) {
		int height = locked ? 0 : getHeight() / 5;
		int[] rect = new int[] { getX() + w, getY(), getWidth() - w, h + height };

		new TextDecoration().drawShadowedString(g, fontText, text, Color.WHITE, Color.BLACK, DIRECTION.NULL, rect);
	}

}
