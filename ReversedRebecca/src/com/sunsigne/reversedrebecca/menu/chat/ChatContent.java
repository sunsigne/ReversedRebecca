package com.sunsigne.reversedrebecca.menu.chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DialogueKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.KeyAnalyzer;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ChatContent implements Updatable {

	private String living_name;
	private String mood;

	private boolean userData = false;

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
	private static final int NUM_OF_CHARACTERS = 64;
	private String[] sentence = new String[NUM_OF_SENTENCES];
	private String[] currentText = new String[NUM_OF_SENTENCES];
	private char[][] letter = new char[NUM_OF_SENTENCES][NUM_OF_CHARACTERS];

	private boolean[] stop = new boolean[2];

	private void lettersAttributionFromSentences() {
		int size = 0;

		for (int sentenceNumber = 0; sentenceNumber < NUM_OF_SENTENCES; sentenceNumber++) {
			if (sentence[sentenceNumber] != null) {
				size = sentence[sentenceNumber].length();
				if (size > NUM_OF_CHARACTERS)
					size = NUM_OF_CHARACTERS;
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

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.CHAT;
	}

	////////// TICK ////////////

	private int pausetime;
	private int count;

	@Override
	public void tick() {
		updateDialogueKey();

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

	private int registeredKey;

	private void updateDialogueKey() {
		if (registeredKey == DialogueKey.getKey())
			return;

		registeredKey = DialogueKey.getKey();
		dialogue_key = "[" + new DialogueKey().getRegisteredKey() + "]";
	}

	private void pause() {
		pausetime = 15;
	}

	private void readSentence(int sentenceNum) {
		if (LAYER.LOADING.getHandler().getList().isEmpty())
			playTalkingSound();

		nextChar(sentenceNum);
	}

	private void nextChar(int line) {
		char newchar = letter[line][count];

		if (".!?�".indexOf(newchar) != -1)
			pause();

		currentText[line] = currentText[line].concat(String.valueOf(newchar));

		count++;
		if (count == sentence[line].length() || count == NUM_OF_CHARACTERS) {
			count = 0;
			stop[line] = true;
			if (line == 0 && sentence[1] == null)
				setFulldisplayed(true);
			else if (line == 1)
				setFulldisplayed(true);
		}
	}

	private static int firstDialogue = 11;
	private boolean talkingSoundPause;

	private void playTalkingSound() {
		// talking sound played once every two ticks
		if (talkingSoundPause) {
			talkingSoundPause = false;
			return;
		}

		// prevent the first dialogue of the game to do a lot of noises
		firstDialogue--;
		if (firstDialogue > 0 && firstDialogue % 5 != 0)
			return;

		String voice = living_name.contains("_") ? living_name.split("_")[0] : living_name;
		new SoundTask().playSound(SOUNDTYPE.VOICE, voice);
		talkingSoundPause = true;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getSheetSubImage(BufferedImage image, String moodFilePath) {
		BufferedImage img = null;
		int width = 144;
		int height = 144;

		try {
			String data = new FileTask().read(userData, mood, moodFilePath);
			if (data.split(",").length < 2) {
				System.err
						.println("Problem encounter with following text dialogue : " + sentence[0] + " " + sentence[1]);
				System.err.println(
						"Image for following character and mood can't be found : " + living_name + " - " + mood);
			}

			int col = Integer.parseInt(data.split(",")[1]);
			int row = Integer.parseInt(data.split(",")[0]);
			img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return img;
	}

	private void loadImage() {
		String path = "textures/characters/" + living_name + "/";
		BufferedImage sheet = new ImageTask().loadImage(path + "dialogue", true);

		if (living_name.equalsIgnoreCase("error") == false)
			image = getSheetSubImage(sheet, path + "mood.txt");

		// load error character instead of missing texture
		if (image == null) {
			String fixedPath = "textures/characters/" + "error" + "/" + "dialogue";
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

		String text0 = stop[0] ? sentence[0] : currentText[0];
		String text1 = stop[1] ? sentence[1] : currentText[1];

		text0 = drawGamepadButton(g, text0, x0, y + 83);
		text1 = drawGamepadButton(g, text1, x0, y + 158);

		g.drawString(text0, x0, y + 83);
		if (sentence[1] != null)
			g.drawString(text1, x0, y + 158);
	}

	private String drawGamepadButton(Graphics g, String text, int x, int y) {
		if (ControllerManager.getInstance().isUsingGamepad() == false || text == null)
			return text;

		while (text.contains("[")) {

			BufferedImage gamepadButton = null;
			if (text.contains("]"))
				gamepadButton = KeyAnalyzer.getGamepadButton(text);

			String replacement = "     ";
			int key_gap = getGapBeforeWithinKeyText(text, replacement);
			text = new FormattedString().replaceWithinKeyText(text, replacement);

			g.drawImage(gamepadButton, x + key_gap, y - 45, Size.S, Size.S, null);
		}

		return text;
	}

	private int getGapBeforeWithinKeyText(String text, String replacement) {
		text = text.split("\\[")[0];
		text = text.replace(replacement, "   ");
		int gap = (int) ((29.5f * Math.pow(text.length(), 0.936d)));

		return gap;
	}

	private String dialogue_key;
	private Font fontKey = new Font("arial", 1, 35);

	private void drawActionKey(Graphics g) {
		if (isFulldisplayed() == false)
			return;

		int[] rect = { x + 1365, y + 180, 0, 0 };
		Color color = new Color(255, 255, 255, 180);
		fontKey = new Font("arial", 1, 35);

		if (ControllerManager.getInstance().isUsingGamepad())
			g.drawImage(DialogueKey.getGamepadButton(), rect[0] - Size.S, rect[1] - Size.XS - 8, Size.S, Size.S, null);
		else
			new TextDecoration().drawCenteredString(g, fontKey, dialogue_key, color, DIRECTION.RIGHT, rect);
	}

}
