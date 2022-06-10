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
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorTrash;
import com.sunsigne.reversedrebecca.object.puzzle.hack.VirusObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusLocker;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusShrinker;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralAudio;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralCDPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralKeyboard;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralMouse;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralNetworkMap;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralPrinter;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralRAM;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralScreen;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
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

	private HackComputer computer;

	public HackComputer getComputer() {
		if (computer == null)
			computer = new HackComputer();
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

	protected ProcessorFolder createPeripheralManager() {
		RandomGenerator rad = new RandomGenerator();
		var list = new GameList<ProcessorObject>(LISTTYPE.LINKED);

		// vitals peripherals are added to list,
		// unnecessary ones sometimes are

		PeripheralNetworkMap network = new PeripheralNetworkMap(this);
		if (rad.getBoolean()) {
			getComputer().addObject(network);
			list.addObject(network);
		}

		PeripheralKeyboard keyboard = new PeripheralKeyboard(this);
		getComputer().addObject(keyboard);
		list.addObject(keyboard);

		PeripheralAudio audio = new PeripheralAudio(this);
		getComputer().addObject(audio);
		list.addObject(audio);

		PeripheralPrinter printer = new PeripheralPrinter(this);
		if (rad.getBoolean()) {
			getComputer().addObject(printer);
			list.addObject(printer);
		}

		PeripheralCDPlayer cd = new PeripheralCDPlayer(this);
		if (rad.getBoolean()) {
			getComputer().addObject(cd);
			list.addObject(cd);
		}

		PeripheralScreen screen = new PeripheralScreen(this);
		getComputer().addObject(screen);
		list.addObject(screen);

		PeripheralRAM ram = new PeripheralRAM(this);
		if (rad.getBoolean()) {
			getComputer().addObject(ram);
			list.addObject(ram);
		}

		PeripheralMouse mouse = new PeripheralMouse(this);
		getComputer().addObject(mouse);
		list.addObject(mouse);

		// conversion of the list into an Array

		int size = list.getList().size();
		ProcessorObject[] peripherals = new ProcessorObject[size];
		for (int index = 0; index < size; index++) {
			peripherals[index] = list.getList().get(index);
		}

		// creation of the peripherals folder

		ProcessorFolder manager = new ProcessorFolder(this, "PCI", peripherals);
		getComputer().addObject(manager);
		return manager;
	}

	protected ProcessorCPU[] createCPU() {
		ProcessorCPU[] cpu = new ProcessorCPU[3];
		for (int index = 0; index < 3; index++) {
			cpu[index] = new ProcessorCPU(this, "CPU-" + String.valueOf(index + 1));
			getComputer().addObject(cpu[index]);
		}
		return cpu;
	}

	protected ProcessorFolder createFolder(String text, ProcessorObject... processors) {
		ProcessorFolder folder = new ProcessorFolder(this, text, processors);
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

		ProcessorTrash trash = new ProcessorTrash(this);
		getComputer().addObject(trash);
		desktop.push(trash);

		LAYER.PUZZLE.addObject(desktop);
	}

	///// antivirus /////

	// each method add ONE ANTIVIRUS BETWEEN ALL POSSIBLE FOLDERS
	// (unless cast severeal times)

	protected void addLocker(ProcessorFolder... folders) {
		int rad = new RandomGenerator().getIntBetween(0, folders.length - 1);

		AntivirusLocker locker = new AntivirusLocker(this, folders[rad]);
		getComputer().addObject(locker);
		folders[rad].push(locker);
	}

	// WARNING, do not put more than ONE of this nightmare in your puzzle !
	protected void addShrinker(ProcessorFolder... folders) {
		int rad = new RandomGenerator().getIntBetween(0, folders.length - 1);

		AntivirusShrinker shrinker = new AntivirusShrinker(this);
		getComputer().addObject(shrinker);
		folders[rad].push(shrinker);
	}

	////////// CLOSE ////////////

	public void closePuzzle(boolean isPuzzleWon) {
		// some Processors has controls "bypass" above Handler, they need this
		getComputer().getList().forEach(tempUpdatable -> tempUpdatable.destroyControls());
		getComputer().clear();
		super.closePuzzle(isPuzzleWon);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		boolean flag = false;

		for (ProcessorObject tempProcessor : getComputer().getList()) {
			if (tempProcessor instanceof ProcessorCPU)
				return;

			// prevent puzzle to close before puzzle is ready
			if (tempProcessor instanceof ProcessorDesktop)
				flag = true;
		}

		if (flag)
			// happens when all CPU got eaten
			closePuzzle(true);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (getComputer().hasScreen() == false)
			return;

		Color blue = new Color(10, 10, 50, 240);
		new TransluantLayer().drawPuzzle(g, blue);
	}

}
