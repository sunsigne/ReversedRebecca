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
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class DemoObject extends GameObject implements TickFree {

	public DemoObject(String name, int x, int y, String... pamareters) {
		super(x, y, 2 * Size.XL, Size.L);
		this.name = name;
		loadText(pamareters);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "DEMO";
		return clazz + " " + name.toUpperCase() + " : " + getX() + "-" + getY();
	}

	////////// TEXT ////////////

	private String translate(String text) {
		return new Translatable().getTranslatedText("Demo" + text, FilePath.MENU);
	}

	private String file = "save.csv";
	private boolean userData = true;

	public String getData(String parameter) {
		String yes = translate("yes");
		String no = translate("no");

		if (parameter.contentEquals("yes"))
			return yes;
		if (parameter.contentEquals("no"))
			return no;

		String[] data = new FileTask().read(userData, file).split(System.getProperty("line.separator"));

		for (String tempDatum : data) {
			if (tempDatum.equalsIgnoreCase(parameter))
				return yes;
		}

		return no;
	}

	private String line1;
	private String line2;
	private String line3;

	private void loadText(String[] pamareters) {
		if (pamareters.length > 0)
			line1 = translate(pamareters[0]) + " : " + getData(pamareters[1]);
		if (pamareters.length > 2)
			line2 = translate(pamareters[2]) + " : " + getData(pamareters[3]);
		if (pamareters.length > 4)
			line3 = translate(pamareters[4]) + " : " + getData(pamareters[5]);
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.MENU;
	}

	////////// TEXTURE ////////////

	private String name;
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

		if (name.contentEquals("double-y"))
			w = 3 * w / 2;
		else
			g.drawImage(getImage(), getX(), getY(), w, Size.L, null);

		
		int gap = 80;

		if (line3 != null)
			drawLine(g, line3, w, 2 * gap);
		else
			gap = 120;

		if (line2 != null)
			drawLine(g, line2, w, gap);
		else
			gap = 160;

		if (line1 != null)
			drawLine(g, line1, w, gap - 80);
	}

	private void drawLine(Graphics g, String text, int w, int h) {
		int height = getHeight() / 5;
		int[] rect = new int[] { getX() + Size.XS + w, getY() + 10, getWidth() - w, h + height };
		new TextDecoration().drawShadowedString(g, fontText, text, color, Color.BLACK, DIRECTION.LEFT, rect);
	}

}
