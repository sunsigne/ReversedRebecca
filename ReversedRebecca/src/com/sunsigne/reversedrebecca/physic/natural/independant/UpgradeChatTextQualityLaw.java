package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.sunsigne.reversedrebecca.menu.chat.ChatContent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class UpgradeChatTextQualityLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new UpgradeChatTextQualityLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {

	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		if (object instanceof ChatContent == false)
			return;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (object instanceof ChatContent == false)
			return;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	}

}
