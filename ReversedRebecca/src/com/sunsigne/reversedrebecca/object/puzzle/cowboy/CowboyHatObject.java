package com.sunsigne.reversedrebecca.object.puzzle.cowboy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;

public class CowboyHatObject extends PuzzleObject implements SheetableImage {

	public CowboyHatObject(Puzzle puzzle, boolean critical, int x, int y) {
		super(puzzle, critical, x, y, 3 * Size.XL, 3 * Size.XL);
		setVelX(-speed / 2);
		setVelY(-speed * 2);
	}

	private int speed = Size.XS / 2;

	////////// NAME ////////////
	
	protected String getName() {
		return "HAT";
	}

	@Override
	public String toString() {
		String pos = getX() + "-" + getY();		
		return "PUZZLE : " + getName() + " : " + pos;
	}
	
	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_MOVER;
	}

	////////// TICK ////////////

	private int alpha;

	@Override
	public void tick() {
		setVelY(getVelY() + 1);
		alpha++;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	public int getSheetWidth() {
		return 144;
	}

	@Override
	public int getSheetHeight() {
		return 171;
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
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cowboy_living");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int offset = getSize() / 2;

		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(Math.toRadians(alpha * 20), getX() + offset, getY() + offset);
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		g2d.rotate(Math.toRadians(-alpha * 20), getX() + offset, getY() + offset);
	}

}
