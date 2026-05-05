package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class DoubleYDeathTornadoPattern extends DoubleYTornadoPattern {

	protected DoubleYDeathTornadoPattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
	}

	public DoubleYDeathTornadoPattern(BossObject boss) {
		this(boss, 15, 145);
	}

	////////// TICK ////////////

	private int time;
	private boolean attacking;

	@Override
	public void tick() {
		super.tick();

		if (attacking == false)
			return;

		time++;
		if (time % 53 != 0)
			return;

		flashPose();
	}

	@Override
	protected void startActing(DOUBLE_Y_CONDITION doubleYCondition) {
		super.startActing(doubleYCondition);
		attacking = true;
		flashPose();
	}

	////////// POSE ////////////

	private int previousPose;

	private int getRadPose() {
		int radPose;

		do
			radPose = new RandomGenerator().getIntBetween(4, 12);
		while (previousPose == radPose);

		previousPose = radPose;
		return radPose;
	}

	private void flashPose() {
		int w = 3 * Size.XL;
		int h = 3 * Size.XL;
		int x = (Window.WIDHT - w) / 2;
		int y = (Window.HEIGHT - h) / 2;

		PoseObject pose = new PoseObject(x, y, w, h);
		LAYER.PUZZLE.addObject(pose);
	}

	private class PoseObject extends GameObject implements SheetableImage {

		public PoseObject(int x, int y, int w, int h) {
			super(x, y, w, h);
		}

		////////// PHYSICS ////////////

		@Override
		public PhysicLaw[] getPhysicLinker() {
			return PhysicLinker.ANIMATION;
		}

		////////// TICK ////////////

		private int time;

		@Override
		public void tick() {
			time++;

			if (time >= 15)
				LAYER.PUZZLE.getHandler().removeObject(this);
		}

		////////// TEXTURE ////////////

		@Override
		public int getSheetColCriterion() {
			return getRadPose();
		}

		@Override
		public int getSheetRowCriterion() {
			return 5;
		}
		
		@Override
		public int getSheetSize() {
			return 24;
		}

		private BufferedImage image;

		private BufferedImage getImage() {
			if (image == null) {
				BufferedImage sheet = new ImageTask().loadImage("textures/characters/" + "double-y" + "/world");
				image = getSheetSubImage(sheet);
			}
			return image;
		}

		////////// RENDER ////////////

		@Override
		public void render(Graphics g) {
			float alpha = 1f - ((float) time / 15f);
			int gap = 6 * time;
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.max(0, alpha)));
			g.drawImage(getImage(), getX() - gap, getY() - gap, getWidth() + 2 * gap, getHeight() + 2 * gap, null);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}

	}

}
