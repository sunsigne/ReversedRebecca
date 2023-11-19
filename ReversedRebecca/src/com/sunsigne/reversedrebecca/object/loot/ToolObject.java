package com.sunsigne.reversedrebecca.object.loot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.characteristics.Highlightable;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ToolObject extends LootObject implements Difficulty, Highlightable {

	public ToolObject(ToolPlayer toolPlayer, LVL difficulty, int x, int y) {
		super(x, y);
		this.toolPlayer = toolPlayer;
		this.difficulty = difficulty;

		loadImages();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "LOOT";
		var name = toolPlayer.getName().toUpperCase();
		var lvl = difficulty.getName().toUpperCase();
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + name + " " + lvl + " : " + goal.getX() + "-" + goal.getY();
	}

	private ToolPlayer toolPlayer;

	////////// DIFFICULTY ////////////

	private LVL difficulty;

	@Override
	public LVL getDifficulty() {
		return difficulty;
	}

	@Override
	public void setDifficulty(LVL difficulty) {
		this.difficulty = difficulty;
	}

	////////// TEXTURE ////////////

	protected BufferedImage image;
	protected BufferedImage blinking_image;

	private void loadImages() {
		image = new ImageTask().loadImage("textures/tools/" + toolPlayer.getName() + "_" + difficulty.getName());
		blinking_image = new ImageTask().loadImage("textures/tools/" + toolPlayer.getName() + "_" + "blinking");
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
		drawHighlight(g, blinking_image);
	}

	////////// COLLISION ////////////

	@Override
	public String getTextWhenLooted() {
		return new Translatable().getTranslatedText(toolPlayer.getName() + difficulty.getName(), FilePath.TOOL);
	}

	@Override
	public void actionWhenLooted() {
		if (new DifficultyComparator().isPositiveUpgade(toolPlayer.getDifficulty(), getDifficulty()))
			toolPlayer.setDifficulty(getDifficulty());
	}

}