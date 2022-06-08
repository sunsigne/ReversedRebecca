package com.sunsigne.reversedrebecca.puzzle.hack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorDesktop;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorSystem;
import com.sunsigne.reversedrebecca.object.puzzle.hack.VirusObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class HackPuzzle extends Puzzle {

	public HackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
		new GameCursor().setCursor(null);
		createVirus();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "hack";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new HackPuzzleFactory();
	}

	////////// PUZZLE ////////////

	private GameList<ProcessorObject> computer = new GameList<>(LISTTYPE.LINKED);

	public GameList<ProcessorObject> getComputer() {
		return computer;
	}

	private void createVirus() {
		PuzzleObject virus = new VirusObject(this);
		LAYER.PUZZLE.addObject(virus);
	}

	////////// OPEN ////////////

	@Override
	protected BufferedImage getWallTexture() {
		String rad = String.valueOf(Math.round(Math.random()));
		return new ImageTask().loadImage("textures/puzzle/" + getName() + "_wall_" + rad);
	}

	@Override
	protected void createWallBorder() {

		Handler handler = LAYER.PUZZLE.getHandler();
		BufferedImage img = null;

		for (int col = 0; col < 13; col++) {
			img = getWallTexture();
			handler.addObject(new WallPuzzle(img, getCol(col), getRow(0)));
			img = getWallTexture();
			handler.addObject(new WallPuzzle(img, getCol(col), getRow(7)));
		}
		for (int row = 0; row < 8; row++) {
			img = getWallTexture();
			handler.addObject(new WallPuzzle(img, getCol(0), getRow(row)));
			img = getWallTexture();
			handler.addObject(new WallPuzzle(img, getCol(13), getRow(row)));
		}
	}
	
	protected ProcessorCPU[] createCPU() {
		ProcessorCPU[] cpu = new ProcessorCPU[3];
		for (int index = 0; index < 3; index++) {
			cpu[index] = new ProcessorCPU(this, "CPU-" + String.valueOf(index + 1), getCol(3 + 2 * index), getRow(1));
			getComputer().addObject(cpu[index]);
		}
		return cpu;
	}

	protected ProcessorFolder createFolder(String text, int x, int y, ProcessorObject... processors) {
		ProcessorFolder folder = new ProcessorFolder(this, text, getCol(3), getRow(1), processors);
		getComputer().addObject(folder);
		return folder;
	}

	protected ProcessorSystem createSystem(ProcessorObject... processors) {
		ProcessorSystem system = new ProcessorSystem(this, processors);
		getComputer().addObject(system);
		LAYER.PUZZLE.addObject(system); // prevent first click to select "system" then its back button
		return system;
	}

	protected void createDesktop(ProcessorObject... processors) {
		ProcessorDesktop desktop = new ProcessorDesktop(this, processors);
		getComputer().addObject(desktop);
		LAYER.PUZZLE.addObject(desktop);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		// prevent puzzle to close before CPU are ready
		if (computer.getList().isEmpty())
			return;

		for (ProcessorObject tempProcessor : getComputer().getList()) {
			if (tempProcessor instanceof ProcessorCPU == false)
				continue;

			return;
		}

		// happens when all CPU got eaten
		closePuzzle(true);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color blue = new Color(10, 10, 50, 240);
		new TransluantLayer().drawPuzzle(g, blue);
	}

}
