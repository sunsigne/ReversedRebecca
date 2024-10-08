package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.MouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusTerminator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class AntivirusTerminatorEye extends ProcessorObject implements MouseObject {

	public AntivirusTerminatorEye(Puzzle puzzle, AntivirusTerminator terminator, boolean left_eye) {
		super(puzzle, null);
		this.terminator = terminator;
		this.left_eye = left_eye;

		x0 = terminator.getX();
		y0 = terminator.getY();

		setX(x0);
		setY(y0);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "antivirus_terminator_eye";
	}

	@Override
	public String toString() {
		var clazz = "PUZZLE : TERMINATOR EYE ";
		return clazz + " : " + getX() + "-" + getY();
	}

	////////// POSITION ////////////

	private int x0, y0;

	////////// PROCESSOR ////////////

	@Override
	public boolean isLocked() {
		return true;
	}

	private AntivirusTerminator terminator;

	public AntivirusTerminator getTerminator() {
		return terminator;
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {

	}

	@Override
	public String getVirusActionSound() {
		return null;
	}

	////////// TICK ////////////

	private final int R = 9;

	@Override
	public void tick() {
		if (getComputer().getList().contains(terminator) == false || getVirus().isDisguised()) {
			removeObject();
			getComputer().removeObject(this);
		}			

		// eyeball sticks to mouse / virus

		int mouseX = getVirus().getX() - getVirus().getWidth() / 2 + 25;
		int mouseY = getVirus().getY() - getVirus().getHeight() / 2 + 25;
		followMouse(mouseX, mouseY);

		// eyeball stays in eye orbit

		int dx = Math.abs(x0 - getX());
		int dy = Math.abs(y0 - getY());

		if (dy > R)
			dy = R;
		if (dx > R)
			dx = R;

		int xmin = x0 - R + dy / 2;
		int xmax = x0 + R - dy / 2;
		int ymin = y0 - R + dx / 2;
		int ymax = y0 + R - dx / 2;

		keepWithinZone(mouseX, mouseY, xmin, xmax, ymin, ymax);

		// better eyeball behavior when virus is very far or very close

		int divX = Math.abs(getVirus().getY() - y0) / (1 + Math.abs(getVirus().getX() - x0));
		int divY = Math.abs(getVirus().getX() - x0) / (1 + Math.abs(getVirus().getY() - y0));

		if (Math.abs(divX - divY) > 1)
			if (Math.abs(getVirus().getX() - x0) > R * 7 || Math.abs(getVirus().getY() - y0) > R * 7) {
				setX(x0 + ((getX() - x0) / (1 + divX)));
				setY(y0 + ((getY() - y0) / (1 + divY)));
			}

		keepWithinZone(getX(), getY(), x0 - R / 2, x0 + R / 2, y0 - R / 2, y0 + R / 2);
	}

	////////// TEXTURE ////////////
	
	@Override
	public int getSheetColCriterion() {
		return 3;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}
	
	@Override
	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "hack_addon");
			image = getSheetSubImage(sheet);
		}			
		return image;
	}
	
	////////// RENDER ////////////

	private boolean left_eye;

	@Override
	public void render(Graphics g) {
		int gap = Size.XS + 4;
		int x = getX() + 1 + (left_eye ? -gap + 2 : gap);
		int y = getY() - (3 * Size.XS / 2) - 2;

		g.drawImage(getImage(), x, y, getWidth(), getHeight(), null);
	}

	////////// MOUSE ////////////

	@Override
	public boolean replaceMouse() {
		return false;
	}

	@Override
	public boolean isClickable() {
		return false;
	}

}
