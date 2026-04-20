package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PointerPuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class PointerBombObject extends PointerPuzzleObject {

	public PointerBombObject(Puzzle puzzle, boolean critical) {
		super(puzzle, critical);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_shoot");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {
		if (isClickable() == false)
			return;

		createHole();
		createParticules(3);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private void createHole() {
		int[] pos = new MousePos().get();

		int mouseX = pos[0] - getWidth();
		int mouseY = pos[1] - getHeight();

		HoleBombObject hole = new HoleBombObject(getPuzzle(), isCritical(), mouseX, mouseY);
		LAYER.PUZZLE.getHandler().getList().add(1, hole);
	}

	private void createParticules(int num_of_particles) {
		int[] pos = new MousePos().get();

		int mouseX = pos[0] - getWidth();
		int mouseY = pos[1] - getHeight();

		for (int index = 0; index < num_of_particles; index++) {
			ParticleBombObject particle = new ParticleBombObject(getPuzzle(), isCritical(), mouseX, mouseY);
			LAYER.PUZZLE.getHandler().addObject(particle);
		}
	}

}
