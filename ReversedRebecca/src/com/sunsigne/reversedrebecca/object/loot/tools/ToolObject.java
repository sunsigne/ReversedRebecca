package com.sunsigne.reversedrebecca.object.loot.tools;

import com.sunsigne.reversedrebecca.object.loot.LootObject;
import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public abstract class ToolObject extends LootObject {

	public ToolObject(DIFFICULTY difficulty, int x, int y) {
		super(x, y);
		this.difficulty = difficulty;
		loadImage();
	}

	////////// NAME ////////////

	public abstract String getPuzzlerName();

	////////// DIFFICULTY ////////////

	private DIFFICULTY difficulty;

	public DIFFICULTY getDifficulty() {
		return difficulty;
	}

	////////// TEXTURE ////////////

	@Override
	protected void loadImage() {
		image = new ImageTask().loadImage("textures/" + getPuzzlerName() + "/" + getName() + "_" + difficulty.getName() + ".png");
	}

	////////// COLLISION ////////////

	@Override
	public void actionWhenLooted() {

	}

}