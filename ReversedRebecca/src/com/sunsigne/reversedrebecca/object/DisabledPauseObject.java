package com.sunsigne.reversedrebecca.object;

import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.PausePreventer;
import com.sunsigne.reversedrebecca.system.Window;

public class DisabledPauseObject extends GameObject {

	public DisabledPauseObject() {
		super(Window.WIDHT / 2, 0);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "DISABLED PAUSE OBJECT";
		return clazz + " " + " : " + PausePreventer.state;
	}

	////////// TEXT ////////////

	private String title;
	private String text;

	public String getTitle() {
		if (title == null)
			title = new Translatable().getTranslatedText("DisabledPause", FilePath.MENU);
		return title;
	}

	public String getText() {
		if (text == null) {
			if (PausePreventer.state == null)
				return "error";

			text = new Translatable().getTranslatedText(PausePreventer.state.getValueToRead(), FilePath.MENU);
		}

		return text;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.MENU;
	}

	////////// TICK ////////////

	private final int MAX_TIME = 400;
	private int time = MAX_TIME;

	@Override
	public void tick() {
		time--;

		// destroy
		if (time == 0)
			LAYER.DEBUG.getHandler().removeObject(this);
	}

	////////// TEXTURE ////////////

	private final Font fontTitle = new FontTask().createNewFont("dogicabold.ttf", 25f);
	private final Font fontText = new FontTask().createNewFont("dogicabold.ttf", 25f);

	@Override
	public void render(Graphics g) {
		drawTitle(g);
		drawText(g);
	}

	private void drawTitle(Graphics g) {
		int[] rect = new int[] { getX(), getY() - getHeight() / 5, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, fontTitle, getTitle(), DIRECTION.NULL, rect);
	}

	private void drawText(Graphics g) {
		int[] rect = new int[] { getX(), getY() + getHeight() / 3, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, fontText, getText(), DIRECTION.NULL, rect);
	}

}
