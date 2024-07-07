package com.sunsigne.reversedrebecca.object.puzzler;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class RequirementBubbleObject extends GameObject implements SheetableImage {

	public RequirementBubbleObject(int x, int y, ToolPlayer tool, LVL difficulty) {
		super(x, y, Size.L, Size.L);
		this.tool = tool;
		this.difficulty = difficulty;

		text = new Translatable().getTranslatedText("Require", FilePath.PUZZLER);
	}

	private ToolPlayer tool;
	private LVL difficulty;

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "REQUIREMENT BUBBLE : ";
		var pos = getX() + "-" + getY();
		var lvl = difficulty.getName().toUpperCase();
		var tool = this.tool.getName().toUpperCase();
		return clazz + " : " + pos + " / " + lvl + " " + tool;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		if (difficulty == LVL.NULL || difficulty == LVL.PURPLE)
			removeObject();
	}

	////////// TEXTURE ////////////

	private BufferedImage tool_image;
	private BufferedImage difficulty_image;

	@Override
	public int getSheetColCriterion() {
		return difficulty.ordinal();
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetSize() {
		return 128;
	}

	public BufferedImage getToolImage() {
		if (tool_image == null) {
			BufferedImage sheet = tool_image = new ImageTask().loadImage("textures/tools/" + tool.getName());
			tool_image = getSheetSubImage(sheet, 1, 1, 16, 16);
		}
		return tool_image;
	}

	public BufferedImage getDifficultyImage() {
		if (difficulty_image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/hud/" + "requirement");
			difficulty_image = getSheetSubImage(sheet);
		}
		return difficulty_image;
	}

	////////// RENDER ////////////

	private boolean visible;
	private int registeredX, registeredY;

	public boolean isVisible() {
		return visible;
	}
	
	// besure correct display without blinking or misplacement
	public void setVisible(boolean visible) {
		if (visible) {
			this.visible = true;
			if (registeredX == 0 && registeredY == 0) {
				registeredX = getX();
				registeredY = getY();
			}
		}

		else {
			this.visible = false;
			registeredX = 0;
			registeredY = 0;
		}
	}

	private String text;

	@Override
	public void render(Graphics g) {
		if (registeredX != getX() || registeredY != getY())
			setVisible(false);

		if (visible == false)
			return;

		g.drawImage(getDifficultyImage(), getX(), getY(), getWidth(), getHeight(), null);
		g.drawImage(getToolImage(), getX() + 8, getY() + (getHeight() - Size.XS + 2) / 2, getWidth() / 2,
				getHeight() / 2, null);

		int lenght = Math.max(0, text.length() - 6);
		float size = (25f - lenght * 2f) / (float) Math.sqrt(Window.SCALE_X);
		Font font = new FontTask().createNewFont("square_sans_serif_7.ttf", size);

		int rect[] = new int[] { getRect()[0] + 2, getRect()[1] + Size.XS, getRect()[2], getRect()[3] };
		new TextDecoration().drawOutlinesString(g, font, text, DIRECTION.UP, rect);

		visible = false;
	}

}
