package com.sunsigne.reversedrebecca.object.puzzle;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class CritToken extends GameObject implements TickFree, SheetableImage {

	public CritToken(Puzzle puzzle, int criticalChance) {
		super(puzzle.getCol(13), puzzle.getRow(7), Size.L, Size.L);
		this.criticalChance = criticalChance;
	}

	private int criticalChance;

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : CRIT TOKEN";
		return clazz + " : " + criticalChance;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	public int getSheetSize() {
		return 2 * 16;
	}

	@Override
	public int getSheetColCriterion() {
		return 7;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/hud/debugmode");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("DigitalNumbers-Regular.ttf", 30f);

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);

		int rect[] = new int[] { getX(), getY(), getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, font, criticalChance + "%", DIRECTION.NULL, rect);
	}

}
