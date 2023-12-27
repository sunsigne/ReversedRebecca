package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DownKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.LeftKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.RightKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.UpKey;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class DiscoDancerObject extends PuzzleObject implements KeyboardEvent {

	public DiscoDancerObject(Puzzle puzzle, String name) {
		super(puzzle, false, 0, 0, 2 * Size.XL, 2 * Size.XL);
		this.name = name;

		loadAnimations();
	}

	////////// NAME ////////////

	private String name;

	protected String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "PUZZLE : " + "DANCER" + " " + getName();
	}

	////////// LIT ////////////

	public void lit(int delayInTicks) {
		DiscoFireObject fire = new DiscoFireObject(getPuzzle(), delayInTicks);
		fire.setX(getX() - getWidth() / 2);
		fire.setY(getY() - getHeight());

		// adding right after the puzzle itself
		LAYER.PUZZLE.getHandler().getList().add(1, fire);
		Handler.updateHandlerMap(LAYER.PUZZLE.getHandler(), fire);
	}

	////////// TICK ////////////

	private int animation_time = 21;
	private int time = animation_time;

	@Override
	public void tick() {
		time--;
		if (time < 0) {
			time = animation_time;
			for (int i = 0; i < cycloid.length; i++) {
				cycloid[i].cycle();
			}
		}
	}

	////////// TEXTURE ////////////

	@SuppressWarnings("unchecked")
	private Cycloid<BufferedImage>[] cycloid = new Cycloid[4];

	private void loadAnimations() {
		cycloid[DIRECTION.LEFT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.LEFT));
		cycloid[DIRECTION.RIGHT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.RIGHT));
		cycloid[DIRECTION.UP.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.UP));
		cycloid[DIRECTION.DOWN.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.DOWN));
	}

	private BufferedImage[] loadAnimation(DIRECTION direction) {

		String path = "walking_" + direction.getName();
		BufferedImage img0 = loadImage(path + "_0");
		BufferedImage img1 = loadImage(path + "_1");

		return new BufferedImage[] { img0, img1 };
	}

	private BufferedImage loadImage(String imageName) {
		String imagePath = "textures/characters/" + getName() + "/" + imageName;

		BufferedImage img = new ImageTask().loadImage(imagePath, true);

		// load error character instead of missing texture
		if (img == null)
			img = new ImageTask().loadImage(imagePath.replace(getName(), "error"));

		return img;
	}

	public BufferedImage getImage() {
		return cycloid[facing.getNum()].getState();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// KEYBOARD ////////////

	private DIRECTION facing = DIRECTION.DOWN;

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == LeftKey.getKey() || key == KeyEvent.VK_LEFT)
			facing = DIRECTION.LEFT;
		if (key == RightKey.getKey() || key == KeyEvent.VK_RIGHT)
			facing = DIRECTION.RIGHT;
		if (key == UpKey.getKey() || key == KeyEvent.VK_UP)
			facing = DIRECTION.UP;
		if (key == DownKey.getKey() || key == KeyEvent.VK_DOWN)
			facing = DIRECTION.DOWN;
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
