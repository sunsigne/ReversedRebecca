package com.sunsigne.reversedrebecca.object.loot.tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.loot.LootObject;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public abstract class ToolObject extends LootObject implements Difficulty {

	public ToolObject(LVL difficulty, int x, int y) {
		super(x, y);
		this.difficulty = difficulty;
		loadImage();
	}

	////////// NAME ////////////

	public abstract String getPuzzlerName();

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

	private void loadImage() {
		image = new ImageTask()
				.loadImage("textures/" + getPuzzlerName() + "/" + getName() + "_" + difficulty.getName());
	}

	public BufferedImage getImage() {
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////

	public abstract ToolPlayer getToolPlayer();

	@Override
	public void actionWhenLooted() {

		ToolPlayer toolPlayer = getToolPlayer();
		boolean shouldUpgrade = new DifficultyComparator().canUseTool(toolPlayer.getDifficulty(), getDifficulty());

		if (shouldUpgrade)
			toolPlayer.setDifficulty(getDifficulty());
	}

}