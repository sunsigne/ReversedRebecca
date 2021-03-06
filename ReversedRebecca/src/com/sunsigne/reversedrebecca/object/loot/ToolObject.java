package com.sunsigne.reversedrebecca.object.loot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ToolObject extends LootObject implements Difficulty {

	private String file = "tools.csv";

	public ToolObject(ToolPlayer toolPlayer, LVL difficulty, int x, int y) {
		super(x, y);
		this.toolPlayer = toolPlayer;
		this.difficulty = difficulty;
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

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/tools/" + toolPlayer.getName() + "_" + difficulty.getName());
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////

	@Override
	public String getTextWhenLooted() {
		return new Translatable().getTranslatedText(toolPlayer.getName() + difficulty.getName(), file);
	}

	@Override
	public void actionWhenLooted() {
		if (new DifficultyComparator().isPositiveUpgade(toolPlayer.getDifficulty(), getDifficulty()))
			toolPlayer.setDifficulty(getDifficulty());
	}

}