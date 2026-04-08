package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;

public class ParticleBombObject extends PuzzleObject implements SheetableImage {

	public ParticleBombObject(Puzzle puzzle, boolean critical, int x, int y) {
		this(puzzle, critical, x, y, Size.L, Size.L);
	}

	public ParticleBombObject(Puzzle puzzle, boolean critical, int x, int y, int w, int h) {
		super(puzzle, critical, x, y, w, h);

		RandomGenerator rad = new RandomGenerator();
		float factor = (1.5f * w) / Size.L;
		setVelX((int) (factor * (rad.getBoolean() ? 1 : -1) * rad.getIntBetween(5, 20)));
		setVelY((int) (factor * (rad.getBoolean() ? 1 : -1) * rad.getIntBetween(5, 20)));
	}

	////////// NAME ////////////

	protected String getName() {
		return "PARTICLE";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_MOVER;
	}

	////////// TICK ////////////

	private final int FADING_TIME = 10;
	private int time;

	@Override
	public void tick() {
		time++;

		if (time >= FADING_TIME)
			removeObject();
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	public int getSheetSize() {
		return 2 * 16;
	}

	@Override
	public int getSheetColCriterion() {
		return 4;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "bomb_shoot");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
