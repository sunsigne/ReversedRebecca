package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class AntivirusTerminator extends AntivirusObject {

	public AntivirusTerminator(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("Antivirus" + "Terminator", FilePath.PUZZLE));
		loadImages();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return super.getName() + "_" + "terminator";
	}

	////////// ANTIVIRUS ////////////

	private Color beam_color;

	private void beamToRed() {
		beam_color = new Color(255, 0, 0, 120);
	}

	private void beamToYellow() {
		beam_color = new Color(255, 255, 0, 120);
	}

	private boolean virusJustArrived;

	public void virusJustArrived() {
		if (getVirus().isDisguised())
			return;

		virusJustArrived = true;
		beamToRed();
		playSound("laser_aiming");
	}

	@Override
	public void antivirusAction() {
		if (isCritical() == false)
			getPuzzle().closePuzzle(false);

		beamToRed();
		new SoundTask().playSound(SOUNDTYPE.SOUND, "laser_shoot");
	}

	@Override
	public void destroyAntivirus() {

	}

	////////// TEXTURE ////////////

	private BufferedImage on_img;
	private BufferedImage off_img;

	private void loadImages() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "hack_processor");
		on_img = getSheetSubImage(sheet, 5);
		off_img = getSheetSubImage(sheet, 6);
	}

	@Override
	public BufferedImage getImage() {
		if (getVirus().isDisguised())
			return off_img;

		else
			return on_img;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (getVirus().isDisguised() == false)
			drawArc(g);

		super.render(g);
	}

	private void drawArc(Graphics g) {

		// récupération des coordonées

		int vX = getVirus().getX() + getVirus().getWidth() / 2;
		int vY = getVirus().getY() + getVirus().getHeight() / 2;
		int tX = getX() + getWidth() / 2;
		int tY = getY() + getHeight() / 2 - getWidth() / 4;

		// établissement des dimensions de l'arc

		int size = 4000;
		int x = tX - size / 2;
		int y = tY - size / 2;
		double divX = (double) (vX - tX);
		double divY = (double) (tY - vY);
		int angle = (int) (Math.atan2(divY, divX) * 180 / Math.PI);
		int arc = (int) (60 - 0.02f * (Math.abs(divX) + Math.abs(divY)));

		// création du dégradé

		Color none = new Color(255, 0, 0, 0);
		float radius = size / 3;
		float[] fractions = { 0.0f, 1.0f };
		Color[] colors = { beam_color, none };
		Paint paint2 = new RadialGradientPaint(tX, tY, radius, fractions, colors);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(paint2);

		// rendu final

		g2d.fillArc(x, y, size, size, angle - arc / 2, arc);
	}

	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {
		if (isClickable() == false)
			return;

		if (virusJustArrived == false)
			beamToYellow();

		if (getVirus().isDisguised() || isCritical())
			super.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isClickable() == false)
			return;

		if (getVirus().isDisguised())
			return;

		if (virusJustArrived == false)
			antivirusAction();

		virusJustArrived = false;
	}

}
