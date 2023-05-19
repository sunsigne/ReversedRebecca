package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Size;

public class ProcessorHorse extends ProcessorEatable {

	public ProcessorHorse(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("Horse", FilePath.PUZZLE));
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "image_1";
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("square_sans_serif_7.ttf", 35f);

	@Override
	protected void drawText(Graphics g) {
		if (getComputer().hasKeyboard() == false) {
			super.drawText(g);
			return;
		}

		Color dark_green = new Color(53, 93, 48);
		int[] rect = { getRect()[0] + Size.XS / 2, getRect()[1] + Size.XS + Size.XS / 3, getRect()[2], getRect()[3] };
		new TextDecoration().drawOutlinesString(g, font, text, dark_green, Color.green, DIRECTION.DOWN, rect);
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		super.doVirusAction();
		getVirus().setDisguised(true);
	}

	@Override
	public String getVirusActionSound() {
		String sound = new RandomGenerator().getBoolean() ? "horse_0" : "horse_1";
		return sound;
	}

}
