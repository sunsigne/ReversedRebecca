package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoArrowObject.CASE;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class DiscoTextObject extends PuzzleObject {

	public DiscoTextObject(Puzzle puzzle, int x, CASE caze) {
		super(puzzle, false, x, 80);
		this.caze = caze;
		setVelY(-2);
	}

	private CASE caze;

	////////// NAME ////////////

	protected String getName() {
		return "TEXT";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName() + " : " + caze;
	}

	////////// TICK ////////////

	private final int MAX_TIME = 20;
	private int time;

	@Override
	public void tick() {
		time++;

		if(time == 8)
			setVelY(0);
		
		if (time >= MAX_TIME)
			LAYER.PUZZLE.getHandler().removeObject(this);
	}

	////////// TEXTURE ////////////

	private Color color;

	private Color getColor() {
		if (color != null)
			return color;

		switch (caze) {
		case FAIL:
			color = new Color(255, 0, 0);
			break;
		case GOOD:
			color = new Color(0, 255, 0);
			break;
		case PERFECT:
			color = new Color(0, 255, 255);
			break;
		}

		return color;
	}

	private String text;

	private String getText() {
		if (text != null)
			return text;

		String valueToRead = "disco" + caze.getName();
		text = new Translatable().getTranslatedText(valueToRead, FilePath.PUZZLE);
		text = new FormattedString().getNoSpecialCharacter(text);
		text = text.toUpperCase();

		return text;
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("F5.6-Regular.otf", 50f);

	@Override
	public void render(Graphics g) {
		new TextDecoration().drawOutlinesString(g, font, getText().toUpperCase(), getColor(), Color.BLACK, DIRECTION.NULL, getRect());
	}

}
