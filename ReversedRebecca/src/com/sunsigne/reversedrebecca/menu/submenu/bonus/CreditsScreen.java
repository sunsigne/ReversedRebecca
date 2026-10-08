package com.sunsigne.reversedrebecca.menu.submenu.bonus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.submenu.BonusScreen;
import com.sunsigne.reversedrebecca.menu.submenu.SubMenuScreen;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;

public class CreditsScreen extends SubMenuScreen {

	public CreditsScreen() {
		super(BACK);
		loadText();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "credits";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new BonusScreen(BACK);
	}

	////////// TEXT ////////////

	private boolean isTextReady;

	private String creatorTitle;
	private String creatorText;
	private String musicTitle;
	private String[] musicText = new String[2];
	private String comicTitle;
	private String comicText;
	private String drawingTitle;
	private String[] drawingText = new String[2];
	private String levelTitle;
	private String levelText;
	private String thankTitle;
	private String[] thankText = new String[3];

	private void loadText() {
		font = new FontTask().createNewFont("dogicabold.ttf", 35f);
		color = new Color(255, 204, 0);

		creatorTitle = "Createur";
		creatorText = "Sunsigne";
		musicTitle = "Musiques";
		musicText[0] = "Danosongs";
		musicText[1] = "Sunsigne & IA";
		comicTitle = "Illustrations & BD";
		comicText = "Melichat";
		drawingTitle = "Aide Dessin";
		drawingText[0] = "Aiko, Drey, Miyuka";
		drawingText[1] = "Loomy, Neyro2008, Non248";
		levelTitle = "Aide Level Design";
		levelText = "Meanwhile";
		thankTitle = "Remerciements";
		thankText[0] = "Melichat, Faedryn, Yan";
		thankText[1] = "leVen0m, Krolaf, Tomaoak";
		thankText[2] = "Ma femme et mes enfants";

		isTextReady = true;
	}

	////////// RENDER ////////////

	private Font font;
	protected Color color;

	@Override
	public void render(Graphics g) {
		drawTransluantLayer((Graphics2D) g);

		if (isTextReady == false)
			return;

		var text = new TextDecoration();
		int[] rect;
		int x = (Window.WIDHT / 2) - 500;
		int y = 0;
		int gap = 70;

		// me
		y = 20;
		rect = new int[] { Window.WIDHT / 2, y, 0, 80 };
		text.drawOutlinesString(g, font, creatorTitle, color, Color.BLACK, DIRECTION.NULL, rect);
		rect = new int[] { Window.WIDHT / 2, y + gap, 0, 80 };
		text.drawOutlinesString(g, font, creatorText, DIRECTION.NULL, rect);

		// musics
		y = 200;
		rect = new int[] { x, y, 0, 80 };
		text.drawOutlinesString(g, font, musicTitle, color, Color.BLACK, DIRECTION.NULL, rect);
		rect = new int[] { x, y + gap, 0, 80 };
		text.drawOutlinesString(g, font, musicText[0], DIRECTION.NULL, rect);
		rect = new int[] { x, y + gap * 2, 0, 80 };
		text.drawOutlinesString(g, font, musicText[1], DIRECTION.NULL, rect);

		// comics
		y = y + 200 + gap;
		rect = new int[] { x, y, 0, 80 };
		text.drawOutlinesString(g, font, comicTitle, color, Color.BLACK, DIRECTION.NULL, rect);
		rect = new int[] { x, y + gap, 0, 80 };
		text.drawOutlinesString(g, font, comicText, DIRECTION.NULL, rect);

		// drawings
		y = y + 200;
		rect = new int[] { x, y, 0, 80 };
		text.drawOutlinesString(g, font, drawingTitle, color, Color.BLACK, DIRECTION.NULL, rect);
		rect = new int[] { x, y + gap, 0, 80 };
		text.drawOutlinesString(g, font, drawingText[0], DIRECTION.NULL, rect);
		rect = new int[] { x, y + gap * 2, 0, 80 };
		text.drawOutlinesString(g, font, drawingText[1], DIRECTION.NULL, rect);
		
		// level design
		x = (Window.WIDHT / 2) + 500;
		y = 200;
		rect = new int[] { x, y, 0, 80 };
		text.drawOutlinesString(g, font, levelTitle, color, Color.BLACK, DIRECTION.NULL, rect);
		rect = new int[] { x, y + gap, 0, 80 };
		text.drawOutlinesString(g, font, levelText, DIRECTION.NULL, rect);
		
		// tanks
		y = y + 200 + gap * 3;
		rect = new int[] { x, y, 0, 80 };
		text.drawOutlinesString(g, font, thankTitle, color, Color.BLACK, DIRECTION.NULL, rect);
		rect = new int[] { x, y + gap, 0, 80 };
		text.drawOutlinesString(g, font, thankText[0], DIRECTION.NULL, rect);
		rect = new int[] { x, y + gap * 2, 0, 80 };
		text.drawOutlinesString(g, font, thankText[1], DIRECTION.NULL, rect);
		rect = new int[] { x, y + gap * 3, 0, 80 };
		text.drawOutlinesString(g, font, thankText[2], DIRECTION.NULL, rect);
	}

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
