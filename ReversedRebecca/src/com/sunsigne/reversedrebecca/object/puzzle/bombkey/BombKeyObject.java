package com.sunsigne.reversedrebecca.object.puzzle.bombkey;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.Animation;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;

public class BombKeyObject extends PuzzleObject implements SheetableImage {

	protected BombKeyObject(Puzzle puzzle, boolean critical, int x, int y, int w, int h) {
		super(puzzle, critical, x, y, w, h);
		loadAnimation();
		bomb_tick = getMaxBombTick();
		TICKING_TIME = time = 20;
	}

	public BombKeyObject(Puzzle puzzle, boolean critical, int x, int y) {
		this(puzzle, critical, x, y, 3 * Size.XL, 3 * Size.XL);
	}

	private boolean exploded;

	public boolean hasExploded() {
		return exploded;
	}

	////////// NAME ////////////

	protected String getName() {
		return "BOMBKEY";
	}

	@Override
	public String toString() {

		String critical = isCritical() ? " CRITICAL" : "";
		String bomb_tick = getBombTick() + "/" + getMaxBombTick();

		return "PUZZLE : " + getName() + critical + " : " + bomb_tick;
	}

	////////// BOMB_TICK ////////////

	private int TICKING_TIME;
	private int bomb_tick;

	public int getMaxBombTick() {
		return 15;
	}

	public int getBombTick() {
		return bomb_tick;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		runAnimation();
	}

	private void runAnimation() {
		time--;
		if (time < 0) {
			time = TICKING_TIME;;
			bomb_tick--;
			animation.cycle();
		}
		
		if(animation.cycleIsFinished())
			getPuzzle().closePuzzle(false);
	}

	////////// TEXTURE ////////////

	private LimitedCycloid<BufferedImage> animation;

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetSize() {
		return 64;
	}

	private void loadAnimation() {
		String path = "textures/puzzle/" + getPuzzle().getName() + "_";
		BufferedImage sheet = new ImageTask().loadImage(path + "bomb");
		Animation ticking_animation = new Animation(sheet, getSheetWidth(), getSheetHeight());
		animation = new LimitedCycloid<BufferedImage>(ticking_animation.getImages());
	}

	public BufferedImage getImage() {
		return animation.getState();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
