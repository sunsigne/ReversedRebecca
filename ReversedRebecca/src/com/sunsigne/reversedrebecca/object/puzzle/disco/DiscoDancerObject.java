package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption.LIVING_TYPE;
import com.sunsigne.reversedrebecca.object.piranha.living.NPC;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimation;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DownKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.LeftKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.RightKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.UpKey;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class DiscoDancerObject extends PuzzleObject implements DiscoSwitchableSide, KeyboardEvent {

	public DiscoDancerObject(Puzzle puzzle, String name, boolean autoplay) {
		super(puzzle, false, 0, 0, 2 * Size.XL, 2 * Size.XL);
		this.name = name;
		this.autoplay = autoplay;

		loadLiving();
		loadAnimations();
	}

	private boolean autoplay;

	public boolean isAutoplay() {
		switch (DifficultyOption.getDifficulty()) {
		case EASY:
			return true;
		case NORMAL:
			return autoplay;
		case HARD:
			return false;
		}
		return autoplay;
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

	////////// POSITION ////////////

	@Override
	public void setX(int x) {
		super.setX(x);
		if (living != null)
			living.setX(x);
	}

	@Override
	public void setY(int y) {
		super.setY(y);
		if (living != null)
			living.setY(y);
	}

	////////// FACING ////////////

	public DIRECTION getFacing() {
		if (living != null)
			return living.getFacing();
		return DIRECTION.NULL;
	}

	public void setFacing(DIRECTION facing) {
		if (living != null)
			living.setFacing(facing);
	}

	////////// LIT ////////////

	private DiscoFireObject fire;
	
	public void lit(int delayInTicks) {
		if(fire != null) {
			fire.lit(delayInTicks);
			fire = null;
			return;
		}
		
		fire = new DiscoFireObject(getPuzzle(), this, delayInTicks);

		// adding right after the puzzle itself
		LAYER.PUZZLE.getHandler().getList().add(1, fire);
		Handler.updateHandlerMap(LAYER.PUZZLE.getHandler(), fire);
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_MOVER;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		getAnimation().run();
	}

	////////// TEXTURE ////////////

	private LivingObject living;

	private void loadLiving() {
		living = new NPC(name, 0, 0);
		living.setBlockingPath(false);
		
		if(name.contains("rebecca"))
			return;
		
		String gender = LivingOption.getType() == LIVING_TYPE.FEMALE ? "sabrina" : "stephan";
		living.setTextureName(name + "_" + gender);
	}

	private LivingAnimation dancingAnimation;

	private void loadAnimations() {
		dancingAnimation = new LivingAnimation(living, 21, true, 2, 3);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {
		return dancingAnimation;
	}

	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (isAutoplay() && name.contains("rebecca") == false)
			return;

		int key = e.getKeyCode();

		if (key == LeftKey.getKey() || key == KeyEvent.VK_LEFT)
			living.setFacing(DIRECTION.LEFT);
		if (key == RightKey.getKey() || key == KeyEvent.VK_RIGHT)
			living.setFacing(DIRECTION.RIGHT);
		if (key == UpKey.getKey() || key == KeyEvent.VK_UP)
			living.setFacing(DIRECTION.UP);
		if (key == DownKey.getKey() || key == KeyEvent.VK_DOWN)
			living.setFacing(DIRECTION.DOWN);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
