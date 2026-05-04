package com.sunsigne.reversedrebecca.object.puzzle.yy.intelligence;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.yy.intelligence.YYIntelligencePuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class IntelligenceLauncherObject extends PuzzleObject implements TickFree, SheetableImage {

	public IntelligenceLauncherObject(Puzzle puzzle) {
		super(puzzle, false, 0, 0, 3 * Size.L, 3 * Size.L);

		if (puzzle instanceof YYIntelligencePuzzle)
			LOOP_TIME = ((YYIntelligencePuzzle) puzzle).getLoopTime();
		else
			LOOP_TIME = Game.SEC;
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		return "PUZZLE : " + "LAUNCHER YY";
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TICK ////////////

	private int LOOP_TIME;
	private int time;

	@Override
	public void tick() {
		time++;

		if (time < LOOP_TIME)
			return;

		time = 0;
		image = null;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return new RandomGenerator().getIntBetween(4, 12);
	}

	@Override
	public int getSheetRowCriterion() {
		return 5;
	}

	@Override
	public int getSheetSize() {
		return 24;
	}

	private BufferedImage image;

	private BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/characters/" + "double-y");
			image = getSheetSubImage(sheet);
		}

		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
