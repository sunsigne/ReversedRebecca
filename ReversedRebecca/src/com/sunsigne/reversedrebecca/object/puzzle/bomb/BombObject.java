package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class BombObject extends PuzzleObject implements MouseUserEvent {

	protected BombObject(Puzzle puzzle, int x, int y, int w, int h) {
		super(puzzle, x, y, w, h);
		loadAnimation();
		maxcount = 1;
		count = maxcount;
	}

	public BombObject(Puzzle puzzle, int x, int y) {
		this(puzzle, x, y, 2 * Size.L, 2 * Size.L);
	}

	private boolean exploded;

	public boolean hasExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
		if (exploded)
			new SoundTask().playSound(SOUNDTYPE.SOUND, "explosion_medium");
	}

	////////// NAME ////////////

	protected String getName() {
		return "BOMB";
	}
	
	@Override
	public String toString() {
		return "PUZZLE : " + getName() + " : " + getCount() + "/" + getMaxCount() + " : " + getRow(getX()) + "-" + getCol(getY());
	}

	////////// MAX COUNT ////////////

	private int maxcount;

	public int getMaxCount() {
		return maxcount;
	}

	public void setMaxCount(int maxcount) {
		if (maxcount > 1)
			this.maxcount = maxcount;
		else
			this.maxcount = 1;

		if (count > maxcount)
			count = maxcount;
	}

	////////// COUNT ////////////

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		if (count <= 0)
			this.count = 0;

		else if (count > maxcount)
			this.count = maxcount;

		else
			this.count = count;
	}

	public void addCount() {
		setCount(getCount() + 1);
	}

	public void removeCount() {
		setCount(getCount() - 1);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "explosion_small");
	}

	////////// TICK ////////////

	private final int ANIMATION_TIME = 2;
	private int time = ANIMATION_TIME;

	@Override
	public void tick() {

		// explode
		if (hasExploded())
			runAnimation();

		else {
			if (count <= 0)
				setExploded(true);
		}
	}

	private void runAnimation() {
		time--;
		if (time < 0) {
			time = ANIMATION_TIME;
			animation.cycle();
		}
	}

	////////// TEXTURE ////////////

	private LimitedCycloid<BufferedImage> animation;

	private void loadAnimation() {

		String path = "textures/puzzle/" + getPuzzle().getName() + "_bomb_";
		ImageTask loader = new ImageTask();

		BufferedImage i0 = loader.loadImage(path + "0");
		BufferedImage i1 = loader.loadImage(path + "1");
		BufferedImage i2 = loader.loadImage(path + "2");
		BufferedImage i3 = loader.loadImage(path + "3");
		BufferedImage i4 = loader.loadImage(path + "4");
		BufferedImage i5 = loader.loadImage(path + "5");
		BufferedImage i6 = loader.loadImage(path + "6");
		BufferedImage i7 = loader.loadImage(path + "7");
		BufferedImage i8 = loader.loadImage(path + "8");
		BufferedImage i9 = loader.loadImage(path + "9");

		animation = new LimitedCycloid<BufferedImage>(i0, i1, i2, i3, i4, i5, i6, i7, i8, i9);
	}

	public BufferedImage getImage() {
		return animation.getState();
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("DigitalNumbers-Regular.ttf", 100f);

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);

		if (count > 0)
			drawCount(font, g);
	}

	private void drawCount(Font font, Graphics g) {

		int rect[] = new int[] { getX() - 5, getY() + 20, getWidth(), getHeight() };

		if (count == 1)
			rect = new int[] { getX() - 22, getY() + 20, getWidth(), getHeight() };

		new TextDecoration().drawOutlinesString(g, font, String.valueOf(count), getTextColor(), Color.BLACK,
				DIRECTION.NULL, rect);
	}

	protected Color getTextColor() {
		if (count == maxcount)
			// red
			return new Color(180, 50, 50);
		if (count == maxcount - 1)
			// orange
			return new Color(255, 128, 40);
		else
			return Color.YELLOW;
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (hasExploded())
			return;

		if (isSelected() == false)
			return;

		removeCount();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
