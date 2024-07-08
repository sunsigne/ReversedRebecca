package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DownKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.LeftKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.RightKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.UpKey;

public class DiscoArrowObject extends PuzzleObject implements SheetableImage, CollisionReactor, KeyboardEvent {

	public DiscoArrowObject(Puzzle puzzle, DIRECTION facing, int x, int y) {
		super(puzzle, false, x, y);
		this.facing = facing;
	}

	private DIRECTION facing;

	////////// NAME ////////////

	protected String getName() {
		return "ARROW";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName() + " : " + "FACING:" + facing.getName();
	}

	////////// PLAY ////////////

	private DiscoPlayerArrowObject player_arrrow;
	private boolean played;

	protected enum CASE {
		GOOD("good"), PERFECT("perfect"), FAIL("fail");

		private String name;

		CASE(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private void play(CASE caze) {
		played = true;
		playSound(caze);
		LAYER.PUZZLE.addObject(new DiscoTextObject(getPuzzle(), getX(), caze));

		if (caze == CASE.FAIL)
			return;

		removeObject();
		player_arrrow.blink();
	}

	private void playSound(CASE caze) {
		String path = null;

		switch (caze) {
		case FAIL:
			path = "button_back";
			break;
		case GOOD:
			path = "button";
			break;
		case PERFECT:
			path = "button_validate";
			break;
		}

		new SoundTask().playSound(SOUNDTYPE.SOUND, path);
	}

	////////// TICK ////////////

	private boolean flag;

	@Override
	public void tick() {
		if (validPos)
			flag = true;

		// fail case
		if (flag == true && played == false && validPos == false) {
			flag = false;
			play(CASE.FAIL);
		}
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	public int getSheetSize() {
		return 2 * 16;
	}

	@Override
	public int getSheetColCriterion() {
		return 1 + facing.getNum();
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "disco_arrow");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////

	private boolean onUp;
	private boolean onDown;
	private boolean validPos;

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof DiscoPlayerArrowObject == false)
			return;

		if (detectorObject.getBounds(DIRECTION.UP).intersects(getBounds()))
			onUp = true;
		else
			onUp = false;

		if (detectorObject.getBounds(DIRECTION.DOWN).intersects(getBounds()))
			onDown = true;
		else
			onDown = false;

		if (detectorObject.getBounds(DIRECTION.NULL).intersects(getBounds())) {
			validPos = true;
			player_arrrow = (DiscoPlayerArrowObject) detectorObject;
		} else
			validPos = false;
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	private boolean isValidKey(KeyEvent e) {
		int key = e.getKeyCode();

		switch (facing) {
		case LEFT:
			return key == LeftKey.getKey() || key == KeyEvent.VK_LEFT;
		case RIGHT:
			return key == RightKey.getKey() || key == KeyEvent.VK_RIGHT;
		case UP:
			return key == UpKey.getKey() || key == KeyEvent.VK_UP;
		case DOWN:
			return key == DownKey.getKey() || key == KeyEvent.VK_DOWN;
		default:
			return false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (played)
			return;

		if (validPos == false)
			return;

		if (isValidKey(e) == false)
			return;

		if (onUp && onDown)
			play(CASE.PERFECT);
		else
			play(CASE.GOOD);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
