package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class DemoEndScreen implements Updatable, SheetableImage {

	public DemoEndScreen() {
		new Save().resetProgression();

		loadImages();
		loadMusic();
		loadText();
	}

	private void loadMusic() {
		new SoundTask().playMusic("3_joys_and_the_truth_clapless_version", false, false);
	}

	////////// TICK ////////////

	private int time;
	private boolean[] displayer = new boolean[20];
	private final int DELAY = 35;

	@Override
	public void tick() {
		time++;

		int t0 = 40;

		if (time >= 0)
			displayer[0] = true;
		if (time == t0 + DELAY)
			displayer[1] = true;
		if (time == t0 + 2 * DELAY)
			displayer[2] = true;
		if (time == t0 + 3 * DELAY)
			displayer[3] = true;
		if (time == t0 + 4 * DELAY)
			displayer[4] = true;
		if (time == t0 + 5 * DELAY)
			displayer[5] = true;
		if (time == t0 + 6 * DELAY)
			displayer[6] = true;
		if (time == t0 + 7 * DELAY)
			displayer[7] = true;

		if (time == t0 + 11 * DELAY)
			displayer[8] = true;
		if (time == t0 + 13 * DELAY)
			displayer[9] = true;
		if (time == t0 + 15 * DELAY)
			displayer[10] = true;
		
		t0 = t0 + 10;
		if (time == t0 + 17 * DELAY) {
			for (int index = 0; index <= 10; index++)
				displayer[index] = false;
			displayer[11] = true;
		}

		if (time == t0 + 18 * DELAY)
			displayer[12] = true;
		if (time == t0 + 19 * DELAY)
			displayer[13] = true;
		if (time == t0 + 20 * DELAY)
			displayer[14] = true;
		if (time == t0 + 21 * DELAY)
			displayer[15] = true;
		if (time == t0 + 23 * DELAY)
			displayer[16] = true;
		if (time == t0 + 27 * DELAY)
			displayer[17] = true;
		if (time == t0 + 29 * DELAY)
			displayer[18] = true;
		if (time == t0 + 31 * DELAY) {
			displayer[19] = true;
			createContinueButton();
		}

		if (time < t0 + 31 * DELAY)
			return;

		if (time / Game.SEC % 2 == 0)
			displayer[19] = false;
		else
			displayer[19] = true;
			
	}

	////////// TEXT ////////////

	private String[] text = new String[11];

	private void loadText() {
		text[0] = "Thank for playing";

		text[1] = "In the full game:";
		text[2] = "- dozens of mini-games";
		text[3] = "- more than 8 endings";
		text[4] = "- time travels";
		text[5] = "- around 40 levels";
		text[6] = "And many other surprises";

		text[7] = "The game's steam page:";
		text[8] = "Adding it to your wishlist";
		text[9] = "is helping its development";
		text[10] = "Click to quit";

		if (Language.getInstance().getLang().equalsIgnoreCase("french")) {
			text[0] = "Merci d'avoir joue";

			text[1] = "Dans le jeu final :";
			text[2] = "- des dizaines de mini-jeux";
			text[3] = "- plus de 8 fins differents";
			text[4] = "- des voyages dans le temps";
			text[5] = "- pres de 40 niveaux";
			text[6] = "Et plein d'autres surprises";

			text[7] = "La page steam du jeu :";
			text[8] = "L'ajouter a votre liste de souhait";
			text[9] = "c'est aider a son developpement";
			text[10] = "Cliquez pour quitter";
		}

		for (int index = 0; index < text.length; index++)
			text[index] = text[index].toUpperCase();
	}

	////////// TEXTURE ////////////

	private BufferedImage[] image = new BufferedImage[8];
	private BufferedImage rebecca;

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	private void loadImages() {
		rebecca = new ImageTask().loadImage("textures/menu/title");

		String path = "textures/other/demoend_";
		BufferedImage sheet = null;
		sheet = new ImageTask().loadImage(path + "puzzle");

		image[0] = getSheetSubImage(sheet, 1, 1, 1792, 1024);
		image[1] = getSheetSubImage(sheet, 2, 1, 1792, 1024);
		image[2] = getSheetSubImage(sheet, 3, 1, 1792, 1024);

		sheet = new ImageTask().loadImage(path + "qr");

		image[3] = getSheetSubImage(sheet, 1, 1, 300, 300);
		image[4] = getSheetSubImage(sheet, 2, 1, 300, 300);
		image[5] = getSheetSubImage(sheet, 3, 1, 300, 300);
		image[6] = getSheetSubImage(sheet, 4, 1, 300, 300);
		image[7] = getSheetSubImage(sheet, 5, 1, 300, 300);
	}

	////////// RENDER ////////////

	private Font title_font = new FontTask().createNewFont("dogicabold.ttf", 50f);
	private Font button_font = new FontTask().createNewFont("dogicabold.ttf", 35f);
	private Font font = new FontTask().createNewFont("dogicabold.ttf", 30f);

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);
		g.setColor(Color.WHITE);

		g.setFont(title_font);
		int size = 300;
		g.drawImage(rebecca, Window.WIDHT / 2 - size - 50, 0, 2 * size, size, null);
		text[0] = "";
		g.drawString(text[0], 470, 210);

		g.setFont(font);
		drawFistPage(g);
		drawSecondPage(g);
	}

	private void drawFistPage(Graphics g) {
		int gap = 70;
		int x0 = 30;
		int y0 = 400;

		int x1[] = {215, 105, 115, 225, 165, 400, 90};
		
		if (Language.getInstance().getLang().equalsIgnoreCase("french")) {
			x1 = new int[] {180, 45, 45, 45, 128, 400, 50};
		}
		
		if (displayer[1])
			g.drawString(text[1], x0 + x1[0], y0);
		if (displayer[2])
			g.drawString(text[2], x0 + x1[1], y0 + 30 + gap);
		if (displayer[3])
			g.drawString(text[3], x0 + x1[2], y0 + 30 + 2 * gap);
		if (displayer[4])
			g.drawString(text[4], x0 + x1[3], y0 + 30 + 3 * gap);
		if (displayer[5])
			g.drawString(text[5], x0 + x1[4], y0 + 30 + 4 * gap);
		if (displayer[6])
			g.drawString("...", x0 + x1[5], y0 + 30 + 5 * gap);
		if (displayer[7])
			g.drawString(text[6], x0 + x1[6], y0 + 30 + 6 * gap + 10);

		float size = 0.34f;
		if (displayer[8])
			g.drawImage(image[2], 1050, 520, (int) (1792 * size), (int) (1024 * size), null);
		if (displayer[9])
			g.drawImage(image[1], 1000, 290, (int) (1792 * size), (int) (1024 * size), null);
		if (displayer[10])
			g.drawImage(image[0], 1250, 440, (int) (1792 * size), (int) (1024 * size), null);
	}

	private void drawSecondPage(Graphics g) {
		int y0 = 15;

		if (displayer[11])
			g.drawString(text[7], 605, y0 + 340);

		if (displayer[12])
			g.drawImage(image[3], 780, y0 + 410, 300, 300, null);
		if (displayer[13])
			g.drawImage(image[4], 780, y0 + 410, 300, 300, null);
		if (displayer[14])
			g.drawImage(image[5], 780, y0 + 410, 300, 300, null);
		if (displayer[15])
			g.drawImage(image[6], 780, y0 + 410, 300, 300, null);
		if (displayer[16])
			g.drawImage(image[7], 780, y0 + 410, 300, 300, null);

		int x1[] = {540, 540, 710};
		
		if (Language.getInstance().getLang().equalsIgnoreCase("french")) {
			x1 = new int[] {430, 485, 590};
		}
		
		if (displayer[17])
			g.drawString(text[8], x1[0], y0 + 810);
		if (displayer[18])
			g.drawString(text[9], x1[1], y0 + 880);

		g.setFont(button_font);
		if (displayer[19])
			g.drawString(text[10], x1[2], 1030);
	}

	////////// BUTTON ////////////

	private void createContinueButton() {
		GenericListener onPress = () -> new Conductor().stopApp();

		ButtonObject button = new ButtonObject(null, 0, 0, Window.WIDHT, Window.HEIGHT, onPress, null) {

			@Override
			public boolean isSelected() {
				return true;
			}

			@Override
			public void render(Graphics g) {

			}
		};
		LAYER.MENU.addObject(button);
	}

}
