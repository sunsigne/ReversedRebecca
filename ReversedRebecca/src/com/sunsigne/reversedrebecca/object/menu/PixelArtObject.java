package com.sunsigne.reversedrebecca.object.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption.LIVING_TYPE;
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
		this.unlocked = pixelArt.isUnlocked();
	}

	private PixelArt pixelArt;
	private boolean unlocked;

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PIXEL ART";
		return clazz + " " + pixelArt.getName().toUpperCase() + " : " + getX() + "-" + getY();
	}

	////////// TEXT ////////////

	private String title;
	private String text;

	public String getTitle() {
		if (title == null) {
			String value = pixelArt.getName() + "title";
			String type = LivingOption.getType() == LIVING_TYPE.DEFAULT ? "" : LivingOption.getType().getName();
			title = new Translatable().getStrictTranslatedText(value + type, FilePath.ACHIEVEMENT);
			if (title.isEmpty())
				title = new Translatable().getTranslatedText(pixelArt.getName() + "title", FilePath.ACHIEVEMENT);
		}

		return title;
	}

	public String getText() {
		if (text == null) {
			if (pixelArt.isHidden() && pixelArt.isUnlocked() == false) {
				text = "???";
				return text;
			}

			String value = pixelArt.getName() + "text";
			String type = LivingOption.getType() == LIVING_TYPE.DEFAULT ? "" : LivingOption.getType().getName();
			text = new Translatable().getStrictTranslatedText(value + type, FilePath.ACHIEVEMENT);
			if (text.isEmpty())
				text = new Translatable().getTranslatedText(value, FilePath.ACHIEVEMENT);
		}

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
		var image = unlocked ? pixelArt.getImage() : pixelArt.getImageLocked();
		g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
		drawText(g);
	}

	private void drawText(Graphics g) {
		Color text_color = Color.WHITE;
		Color shadow_color = Color.BLACK;
		int height = unlocked ? getHeight() / 5 : 0;
		int[] rect = new int[] { getX() + Size.XL + 2, getY() + height, getWidth(), getHeight() };

		new TextDecoration().drawShadowedString(g, fontText, getText(), text_color, shadow_color, DIRECTION.LEFT, rect);
	}

}
