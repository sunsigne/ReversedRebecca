package com.sunsigne.reversedrebecca.object.loot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class ToolObject extends LootObject implements Difficulty {

	public ToolObject(ToolPlayer toolPlayer, LVL difficulty, int x, int y) {
		super(x, y);
		this.toolPlayer = toolPlayer;
		this.difficulty = difficulty;
		loadImage();
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

	private void loadImage() {
		image = new ImageTask()
				.loadImage("textures/" + toolPlayer.getPuzzlerName() + "/" + toolPlayer.getName() + "_" + difficulty.getName());
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

	@Override
	public void actionWhenLooted() {

		boolean shouldUpgrade = new DifficultyComparator().canUseTool(toolPlayer.getDifficulty(), getDifficulty());

		if (shouldUpgrade)
			toolPlayer.setDifficulty(getDifficulty());
	}

}