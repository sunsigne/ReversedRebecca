package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class DemoEndScreen implements Updatable {

	public DemoEndScreen() {
		loadImages();
		loadMusic();

		loadText();
		createContinueButton();
	}

	private void loadMusic() {
		new SoundTask().playMusic("3_Joys_&_the_Truth", false, false);
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
		if (time == t0 + 31 * DELAY)
			displayer[19] = true;

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
		text[0] = "Merci d'avoir joue";

		text[1] = "Dans le jeu final :";
		text[2] = "- des dizaines de mini-jeux";
		text[3] = "- plus de 6 fins differents";
		text[4] = "- des voyages dans le temps";
		text[5] = "- pres de 40 niveaux";
		text[6] = "Et plein d'autres surprises";

		text[7] = "La page steam du jeu :";
		text[8] = "L'ajouter a votre liste de souhait";
		text[9] = "c'est aider a son developpement";
		text[10] = "Cliquez pour quitter";

		for (int index = 0; index < text.length; index++)
			text[index] = text[index].toUpperCase();
	}

	////////// TEXTURE ////////////

	private BufferedImage[] image = new BufferedImage[8];

	private void loadImages() {
		String path = "textures/other/";

		image[0] = new ImageTask().loadImage(path + "Capture1");
		image[1] = new ImageTask().loadImage(path + "Capture2");
		image[2] = new ImageTask().loadImage(path + "Capture3");

		image[3] = new ImageTask().loadImage(path + "QR-steam_1");
		image[4] = new ImageTask().loadImage(path + "QR-steam_2");
		image[5] = new ImageTask().loadImage(path + "QR-steam_3");
		image[6] = new ImageTask().loadImage(path + "QR-steam_4");
		image[7] = new ImageTask().loadImage(path + "QR-steam_5");
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
		g.drawString(text[0], 470, 150);

		g.setFont(font);
		drawFistPage(g);
		drawSecondPage(g);
	}

	private void drawFistPage(Graphics g) {
		int gap = 70;
		int x0 = 30;
		int y0 = 370;

		if (displayer[1])
			g.drawString(text[1], x0 + 180, y0);
		if (displayer[2])
			g.drawString(text[2], x0 + 45, y0 + 30 + gap);
		if (displayer[3])
			g.drawString(text[3], x0 + 45, y0 + 30 + 2 * gap);
		if (displayer[4])
			g.drawString(text[4], x0 + 45, y0 + 30 + 3 * gap);
		if (displayer[5])
			g.drawString(text[5], x0 + 125, y0 + 30 + 4 * gap);
		if (displayer[6])
			g.drawString("...", x0 + 400, y0 + 30 + 5 * gap);
		if (displayer[7])
			g.drawString(text[6], x0 + 50, y0 + 30 + 6 * gap + 10);

		float size = 0.34f;
		if (displayer[8])
			g.drawImage(image[2], 1050, 520, (int) (1792 * size), (int) (1024 * size), null);
		if (displayer[9])
			g.drawImage(image[1], 1000, 290, (int) (1792 * size), (int) (1024 * size), null);
		if (displayer[10])
			g.drawImage(image[0], 1250, 440, (int) (1792 * size), (int) (1024 * size), null);
	}

	private void drawSecondPage(Graphics g) {
		int y0 = -15;

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

		if (displayer[17])
			g.drawString(text[8], 430, y0 + 810);
		if (displayer[18])
			g.drawString(text[9], 485, y0 + 880);

		g.setFont(button_font);
		if (displayer[19])
			g.drawString(text[10], 590, 1030);
	}

	////////// BUTTON ////////////

	private void createContinueButton() {
		GenericListener onPress = () -> loadTitleScreen();

		ButtonObject button = new ButtonObject(null, 0, 0, Window.WIDHT, Window.HEIGHT, onPress, null) {

			@Override
			public boolean isSelected() {
				return (time > 50 + 31 * DELAY);
			}

			@Override
			public void render(Graphics g) {

			}
		};
		LAYER.MENU.addObject(button);
	}

	private void loadTitleScreen() {
		LAYER.LOADING.addObject(new LoadingScreen());

		if (World.get() != null)
			World.get().destroy();
		new TitleScreen(TitleScreen.PLAY);

		LAYER.LOADING.getHandler().clear();
	}

}
