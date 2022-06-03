package com.sunsigne.reversedrebecca.menu.chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ChatContent implements Updatable {

	private String living_name;
	private String mood;

	public ChatContent(String living_name, String mood, String text) {
		this.living_name = living_name;
		this.mood = mood;

		// WARNING ! you should NOT use more than one "@" by line
		this.sentence[0] = text.split("@")[0];
		this.sentence[1] = text.contains("@") ? text.split("@")[1] : null;

		this.currentText[0] = "";
		this.currentText[1] = "";

		lettersAttributionFromSentences();
		pausetime = 10; // offset the loading delay
	}

	////////// TEXT ////////////

	private static final int NUM_OF_SENTENCES = 2;
	private String[] sentence = new String[NUM_OF_SENTENCES];
	private String[] currentText = new String[NUM_OF_SENTENCES];
	private char[][] letter = new char[NUM_OF_SENTENCES][64];

	private boolean[] stop = new boolean[2];

	private void lettersAttributionFromSentences() {
		int size = 0;

		for (int sentenceNumber = 0; sentenceNumber < NUM_OF_SENTENCES; sentenceNumber++) {
			if (sentence[sentenceNumber] != null) {
				size = sentence[sentenceNumber].length();
				for (int i = 0; i < size; i++) {
					letter[sentenceNumber][i] = sentence[sentenceNumber].charAt(i);
				}
			}
		}
	}

	public boolean fulldisplayed;

	public boolean isFulldisplayed() {
		return fulldisplayed;
	}

	public void setFulldisplayed(boolean fulldisplayed) {
		this.fulldisplayed = fulldisplayed;
		if (fulldisplayed) {
			stop[0] = true;
			stop[1] = true;
			currentText[0] = sentence[0];
			currentText[1] = sentence[1];
		}
	}

	////////// TICK ////////////

	private int pausetime;
	private int count, index;

	@Override
	public void tick() {

		if (pausetime > 0) {
			pausetime--;
			return;
		}

		if (!stop[0]) {
			readSentence(0);
			return;
		}

		if (stop[0] && !stop[1])
			readSentence(1);
	}

	private void pause() {
		pausetime = 15;
	}

	private void readSentence(int sentenceNum) {
		index++;
		playTalkingSound();

		if (index == 1) {
			nextChar(sentenceNum);
		}

		if (index == 2) {
			index = 0;
			nextChar(sentenceNum);
		}
	}

	private void nextChar(int line) {
		char newchar = ' ';
		String newletter;

		int size = sentence[line].length();
		for (int i = 0; i < size; i++) {

			if (count == i) {
				newchar = letter[line][i];
				newletter = String.valueOf(newchar);
				boolean isPauseChar = ".!?…".contains(Character.toString(letter[line][i]));
				if (isPauseChar)
					pause();

				currentText[line] = currentText[line].concat(newletter);
			}
		}
		count++;

		if (count == size) {
			count = 0;
			stop[line] = true;
			if (line == 0 && sentence[1] == null)
				setFulldisplayed(true);
			else if (line == 1)
				setFulldisplayed(true);
		}
	}

	private boolean talkingSoundPause;

	// talking sound played once every two ticks
	private void playTalkingSound() {
		if (talkingSoundPause) {
			talkingSoundPause = false;
			return;
		}

		new SoundTask().play(SOUNDTYPE.VOICE, "sound/voice/" + living_name);
		talkingSoundPause = true;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private void loadImage() {
		String imagePath = "textures/characters/" + living_name + "/chat/" + mood;
		image = new ImageTask().loadImage(imagePath, true);

		// load error character instead of missing texture
		if (image == null) {
			String fixedPath = "textures/characters/" + "error" + "/mood_" + "neutral";
			image = new ImageTask().loadImage(fixedPath);
		}
	}

	public BufferedImage getImage() {
		if (image == null)
			loadImage();
		return image;
	}

	////////// RENDER ////////////

	private int x = 380;
	private int y = 800;

	@Override
	public void render(Graphics g) {
		drawFacial(g);
		drawText(g);
		drawActionKey(g);
	}

	private void drawFacial(Graphics g) {
		int size = 144;

		g.drawImage(getImage(), x, y + 30, -size, size, null);
	}

	private Font font = new Font("arial", 1, 50);

	private void drawText(Graphics g) {
		g.setColor(Color.white);
		g.setFont(font);

		int x0 = x + 90;

		g.drawString(currentText[0], x0, y + 83);
		if (sentence[1] != null)
			g.drawString(currentText[1], x0, y + 158);
	}

	private String registeredKey = "[" + new ActionOneKey().getRegisteredKey() + "]";

	private void drawActionKey(Graphics g) {
		g.drawString(registeredKey, x + 1290, y + 180);
	}

}
