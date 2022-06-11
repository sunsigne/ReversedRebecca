package com.sunsigne.reversedrebecca.puzzle.hack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorDesktop;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorEatable;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorHorse;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorTrash;
import com.sunsigne.reversedrebecca.object.puzzle.hack.VirusObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusLocker;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusParalyzer;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusReverser;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusShrinker;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusTerminator;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralAudio;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralKeyboard;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralMouse;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralObject;
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

	protected void createVirus() {
		PuzzleObject virus = new VirusObject(this);
		LAYER.PUZZLE.addObject(virus);
	}

	private PeripheralObject createPeripheralObject(String name, String text) {
		PeripheralObject object = new PeripheralObject(this, text) {
			@Override
			public String getName() {
				return name;
			}
		};
		return object;
	}

	protected ProcessorFolder createPeripheralManager() {
		RandomGenerator rad = new RandomGenerator();
		var list = new GameList<ProcessorObject>(LISTTYPE.LINKED);

		// vitals peripherals are added to list,
		// unnecessary ones sometimes are

		PeripheralObject network = createPeripheralObject("network_map", "Net-Map");
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

		PeripheralObject printer = createPeripheralObject("printer", "Printer");
		if (rad.getBoolean()) {
			getComputer().addObject(printer);
			list.addObject(printer);
		}

		PeripheralObject cd = createPeripheralObject("CD_player", "CD Player");
		if (rad.getBoolean()) {
			getComputer().addObject(cd);
			list.addObject(cd);
		}

		PeripheralScreen screen = new PeripheralScreen(this);
		getComputer().addObject(screen);
		list.addObject(screen);

		PeripheralObject ram = createPeripheralObject("cpu", "RAM");
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
		return createFolder("peripherals", "PCI", peripherals);
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

	protected ProcessorFolder createFolder(String name, String text, ProcessorObject... processors) {
		ProcessorFolder folder = new ProcessorFolder(this, text, processors) {
			@Override
			public String getName() {
				return name;
			}
		};
		getComputer().addObject(folder);
		return folder;
	}

	private String[] getPNGNames() {
		String[] names = new String[] { "Stars", "Forest", "Mountains", "River", "Desert", "Apple", "Banana", "Grapes",
				"Potato", "Car", "Truck", "Cat", "Dog", "Snake", "Dolphin" };
		return names;
	}

	private String[] getMP3Names() {
		String[] names = new String[] { "Jazz", "Classic", "Rock", "Rap", "RnB", "Metal", "Reggae", "Hip-Hop", "Folk",
				"Electro" };
		return names;
	}

	protected ProcessorEatable[] createPNGFiles() {
		RandomGenerator rad = new RandomGenerator();
		int size = rad.getIntBetween(6, 10);
		ProcessorEatable[] png = new ProcessorEatable[size];

		int horse = rad.getIntBetween(0, size - 1);

		// create "size" number of png files
		for (int index = 0; index < size; index++) {
			png[index] = new ProcessorEatable(this, rad.getString(getPNGNames())) {
				@Override
				public String getName() {
					return "image_" + 1;
				}
			};
			// number "horse" not added to computer yet
			if (index != horse)
				getComputer().addObject(png[index]);
		}

		// creation of the ProcessorHorse
		png[horse] = new ProcessorHorse(this);
		getComputer().addObject(png[horse]);

		return png;
	}

	protected ProcessorEatable[] createMP3Files() {
		RandomGenerator rad = new RandomGenerator();
		int size = rad.getIntBetween(4, 10);
		ProcessorEatable[] mp3 = new ProcessorEatable[size];

		// create "size" number of mp3 files
		for (int index = 0; index < size; index++) {
			mp3[index] = new ProcessorEatable(this, rad.getString(getMP3Names())) {
				@Override
				public String getName() {
					int num = rad.getIntBetween(1, 3);
					return "music_" + num;
				}
			};
			getComputer().addObject(mp3[index]);
		}
		return mp3;
	}

	protected ProcessorFolder createSystem(ProcessorObject... processors) {
		ProcessorFolder system = createFolder("computer", "system", processors);
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

	// add a random num between min and max of lockers in a folder
	protected void addLocker(int min, int max, ProcessorFolder folder) {
		int num = new RandomGenerator().getIntBetween(min, max);
		int count = 0;
		while (count < num) {
			addLocker(folder);
			count++;
		}
	}

	protected void addReverser(ProcessorFolder... folders) {
		int rad = new RandomGenerator().getIntBetween(0, folders.length - 1);

		AntivirusReverser reverser = new AntivirusReverser(this);
		getComputer().addObject(reverser);
		folders[rad].push(reverser);
	}

	// Don't forget to use createPNGFiles() to counter this Virus !
	protected void addTerminator(ProcessorFolder... folders) {
		int rad = new RandomGenerator().getIntBetween(0, folders.length - 1);

		AntivirusTerminator terminator = new AntivirusTerminator(this);
		getComputer().addObject(terminator);
		folders[rad].push(terminator);
	}

	protected void addParalyzer(ProcessorFolder... folders) {
		int rad = new RandomGenerator().getIntBetween(0, folders.length - 1);

		AntivirusParalyzer paralyzer = new AntivirusParalyzer(this);
		getComputer().addObject(paralyzer);
		folders[rad].push(paralyzer);
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
		int alpha = getComputer().hasScreen() ? 240 : 100;
		Color blue = new Color(10, 10, 50, alpha);
		new TransluantLayer().drawPuzzle(g, blue);
	}

}
