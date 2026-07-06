package com.sunsigne.reversedrebecca.menu.chat;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class ChatContentMindblow extends ChatContent {

	public ChatContentMindblow(String living_name, String mood, String text, String voice) {
		super(living_name, mood, text, voice);
	}

	////////// SOUND ////////////

	private boolean flag;

	private void playSound() {
		flag = true;
		time = -70;
		SoundTask task = new SoundTask();
		task.stopMusic(false);
		task.playSound(SOUNDTYPE.SOUND, "mindblow");
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		if (flag == false)
			playSound();

		time++;
		super.tick();

	}

	////////// RENDER ////////////

	private int x = 380;
	private int y = 800;

	@Override
	public void render(Graphics g) {
		super.render(g);

		if (time >= 0)
			drawMindblow(g);
	}

	private void drawMindblow(Graphics g) {
		float alpha = 1f - ((float) time / 40f);
		int gap = 6 * time;
		int size = 144;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.max(0, alpha)));
		g.drawImage(getImage(), x + gap, y - gap, -size - 2 * gap, size + 2 * gap, null);
		g.drawImage(getImage(), x, y + 30, -size, size, null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
