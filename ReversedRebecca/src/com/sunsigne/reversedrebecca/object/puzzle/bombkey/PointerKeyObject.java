package com.sunsigne.reversedrebecca.object.puzzle.bombkey;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PointerPuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;

public class PointerKeyObject extends PointerPuzzleObject {

	public PointerKeyObject(Puzzle puzzle, boolean critical) {
		this(puzzle, critical, Size.M / 2, Size.M / 2);
	}

	public PointerKeyObject(Puzzle puzzle, boolean critical, int w, int h) {
		super(puzzle, critical, w, h);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 2;
	}

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "key");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() + getWidth() / 8, getY() - getHeight() / 2, 2 * getWidth(), 2 * getHeight(),
				null);
	}
	
	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {
		if (isClickable())
			new SoundTask().playSound(SOUNDTYPE.SOUND, "keys");
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
