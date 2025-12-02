package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;

public class ProcessorHorse extends ProcessorEatable {

	public ProcessorHorse(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("Horse", FilePath.PUZZLE));
		loadImages();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "image";
	}
	////////// TICK ////////////

	private float alpha = 0.55f;
	private float alphaSpeed = 0.006f;
	
	@Override
	public void tick() {
		super.tick();
		alpha = alpha + alphaSpeed;

	    if (alpha >= 0.55f) {
	        alpha = 0.55f;
	        alphaSpeed = -alphaSpeed;
	    } else if (alpha <= 0.15f) {
	        alpha = 0.15f;
	        alphaSpeed = -alphaSpeed;
	    }
	}

	////////// TEXTURE ////////////

	private BufferedImage infectiousImage;

	private void loadImages() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "hack_processor");
		infectiousImage = getSheetSubImage(sheet, 6, 4, getSheetWidth(), getSheetHeight());
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("square_sans_serif_7.ttf", 35f);

	@Override
	protected void drawText(Graphics g) {
		// this line MUST be after the image, but before the locker image
		drawInfectiousImage(g);
		
		if (getComputer().hasKeyboard() == false) {
			super.drawText(g);
			return;
		}

		Color dark_green = new Color(53, 93, 48);
		int[] rect = { getRect()[0] + Size.XS / 2, getRect()[1] + Size.XS + Size.XS / 3, getRect()[2], getRect()[3] };
		new TextDecoration().drawOutlinesString(g, font, text, dark_green, Color.green, DIRECTION.DOWN, rect);		
	}

	private void drawInfectiousImage(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.drawImage(infectiousImage, getX(), getY(), getWidth(), getHeight(), null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		super.doVirusAction();
		getVirus().setDisguised(true);
		createDisguiseAnimation();
	}

	private void createDisguiseAnimation() {
		VirusObject virus = getComputer().getVirus();
		var animation = new VirusDisguiseAnimationObject(getPuzzle(), virus.getX(), virus.getY(), isCritical());
		animation.setReversed(getVirus().isReversed());
		LAYER.PUZZLE.addObject(animation);
	}

	@Override
	public String getVirusActionSound() {
		String sound = new RandomGenerator().getBoolean() ? "horse_0" : "horse_1";
		return sound;
	}

}
