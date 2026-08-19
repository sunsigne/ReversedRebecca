package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.Animation;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;

public class DiscoFireObject extends PuzzleObject {

	public DiscoFireObject(Puzzle puzzle, DiscoDancerObject discoDancer, int delayInTicks) {
		super(puzzle, false, 0, 0, 4 * Size.XL, 4 * Size.XL);
		this.discoDancer = discoDancer;
		this.delay = delayInTicks;
		loadAnimations("red");
	}

	////////// NAME ////////////

	protected String getName() {
		return "FIRE";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName();
	}

	////////// POSITION ////////////

	private DiscoDancerObject discoDancer;

	@Override
	public int getX() {
		return discoDancer.getX() - discoDancer.getWidth() / 2;

	}

	@Override
	public int getY() {
		return discoDancer.getY() - discoDancer.getHeight();
	}

	////////// LIT ////////////

	public void lit(int delayInTicks) {
		new GameTimer(delayInTicks, true, () -> {
			loadAnimations("blue");
			if (ControllerManager.getInstance().isUsingGamepad())
				new CameraShaker().shaking(SHAKE.MEDIUM);
		});
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TICK ////////////

	private int ANIMATION_TIME = 10;
	private int time = ANIMATION_TIME;
	private int delay;

	@Override
	public void tick() {
		delay--;
		time--;
		if (time < 0) {
			time = ANIMATION_TIME;
			animation.cycle();
		}

		if (delay == 0 && ControllerManager.getInstance().isUsingGamepad())
			new CameraShaker().shaking(SHAKE.MEDIUM);
	}

	////////// TEXTURE ////////////

	private Cycloid<BufferedImage> animation;

	private void loadAnimations(String color) {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "disco_fire_" + color);
		Animation images = new Animation(sheet);
		animation = new Cycloid<BufferedImage>(images.getImages());
	}

	public BufferedImage getImage() {
		if (animation != null)
			return animation.getState();
		else
			return null;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (delay < 0)
			g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
