package com.sunsigne.reversedrebecca.object.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.NPC;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class DemoObject extends GameObject implements TickFree {

	public DemoObject(String name) {
		this(name, Window.WIDHT / 2 - 13 * Size.XS, -Size.L);
	}

	public DemoObject(String name, int x, int y) {
		super(x, y, 2 * Size.XL, Size.L);
		this.name = name;
	}

	private String name;

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "DEMO";
		return clazz + " " + name.toUpperCase() + " : " + getX() + "-" + getY();
	}

	////////// TEXT ////////////

	private String text;

	public String getText() {
		if (text != null)
			return text;

		text = new Translatable().getStrictTranslatedText(name, FilePath.DEMO);
		if (text.isEmpty())
			text = new Translatable().getTranslatedText(name, FilePath.DEMO);
		return text;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.MENU;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image != null)
			return image;

		var chara = new NPC(name, 0, 0);
		image = chara.getImage();

		return image;
	}

	////////// RENDER ////////////

	private final Font fontText = new FontTask().createNewFont("dogicabold.ttf", 18f);
	private Color color = Color.WHITE;

	@Override
	public void render(Graphics g) {
		int w = Size.L;
		g.drawImage(getImage(), getX(), getY(), w, Size.L, null);

		int height = getHeight() / 5;
		int[] rect = new int[] { getX() + w, getY(), getWidth() - w, height };
		new TextDecoration().drawShadowedString(g, fontText, getText(), color, Color.BLACK, DIRECTION.NULL, rect);
	}

}
