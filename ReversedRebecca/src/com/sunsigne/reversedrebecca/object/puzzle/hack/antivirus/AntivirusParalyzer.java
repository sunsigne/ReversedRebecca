package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class AntivirusParalyzer extends AntivirusObject {

	public AntivirusParalyzer(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("AntivirusParalyzer", FilePath.PUZZLE));
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return super.getName() + "_" + "paralyzer";
	}

	////////// ANTIVIRUS ////////////

	@Override
	public void antivirusAction() {

		if (isCritical() == false) {
			new MousePos().setX(Window.WIDHT / 2);
			new MousePos().setY(Window.HEIGHT / 2);
		}

		playSound("laser_little");
	}

	@Override
	public void destroyAntivirus() {

	}

	////////// TICK ////////////

	private final int PARALYZING_TIME = 80;
	private int time = 0;

	@Override
	public void tick() {
		super.tick();

		time--;
		if (time < 0) {
			time = PARALYZING_TIME;
			antivirusAction();
		}
	}

	////////// TEXTURE ////////////

	private BufferedImage image;
	private BufferedImage blueImage;

	@Override
	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask()
					.loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName() + "_" + "white");
		return image;
	}

	public BufferedImage getBlueImage() {
		if (blueImage == null)
			blueImage = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName());
		return blueImage;
	}

	////////// RENDER ////////////

	protected String text;

	@Override
	public void render(Graphics g) {
		super.render(g);

		Graphics2D g2d = (Graphics2D) g;
		float alpha = (float) time / (float) (PARALYZING_TIME / 2);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(alpha, 1f)));
		
		g2d.drawImage(getBlueImage(), getX(), getY(), getWidth(), getHeight(), null);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
