package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;

public class DiscoPlayerArrowObject extends PuzzleObject implements SheetableImage, CollisionDetector {

	public DiscoPlayerArrowObject(Puzzle puzzle, DIRECTION facing, int x) {
		super(puzzle, false, x, 200);
		this.facing = facing;
		loadImages();
	}

	private DIRECTION facing;

	////////// NAME ////////////

	protected String getName() {
		return "PLAYER ARROW";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName() + " : " + "FACING:" + facing.getName();
	}

	////////// PHYSICS ////////////
	
	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}
	
	////////// TICK ////////////

	private float alpha;

	public void blink() {
		alpha = 1.0f;
	}

	@Override
	public void tick() {
		alpha = alpha - 0.04f;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;
	private BufferedImage image_full;

	@Override
	public int getSheetSize() {
		return 2*16;
	}
	
	@Override
	public int getSheetColCriterion() {
		return 1 + facing.getNum();
	}

	@Override
	public int getSheetRowCriterion() {
		return -1;
	}
	
	private void loadImages() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "disco_arrow");	
		image = getSheetSubImage(sheet, getSheetColCriterion(), 2, getSheetWidth(), getSheetHeight());
		image_full = getSheetSubImage(sheet, getSheetColCriterion(), 3, getSheetWidth(), getSheetHeight());
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.max(0, alpha)));
		g2d.drawImage(image_full, getX(), getY(), getWidth(), getHeight(), null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	////////// COLLISION ////////////

	@Override
	public Rectangle getBounds(DIRECTION direction) {
		switch (direction) {
		case UP:
			return getBoundsUp();
		case DOWN:
			return getBoundsDown();
		default:
			return new Rectangle(getX(), 16 + getY() + getHeight() / 2 - 5, getWidth(), 10);
		}
	}

	private Rectangle getBoundsUp() {
		int x = getX() + getWidth() / 3;
		int y = 16 + getY();
		int w = getWidth() / 3;
		int h = getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsDown() {
		int x = getX() + getWidth() / 3;
		int y = 16 + getY() + 7 * getHeight() / 8;
		int w = getWidth() / 3;
		int h = getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

}
