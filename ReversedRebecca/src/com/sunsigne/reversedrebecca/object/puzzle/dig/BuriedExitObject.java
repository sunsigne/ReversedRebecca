package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class BuriedExitObject extends BuriedObject {

	public BuriedExitObject(Puzzle puzzle, int w, int h) {
		super(puzzle, w, h);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "EXIT";
	}

	////////// HIGHLIGHT ////////////

	@Override
	public boolean getHighlightCondition() {
		DIG_STATE state = getPuzzle().getState();
		return isSelected() && (isCritical() || state == DIG_STATE.DIG || state == DIG_STATE.DIGPICK);
	}

	
	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 2;
	}

	@Override
	public int getSheetColCriterion() {
		return state.getState();
	}

	private BufferedImage image;
	private BufferedImage highlight_image;

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "dig_digable");
			image = getSheetSubImage(sheet);
			highlight_image = getSheetSubImage(sheet, getSheetColCriterion(), 3, getSheetWidth(), getSheetHeight());
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		super.render(g);
		drawHighlight(g, highlight_image);
	}

	////////// MOUSE ////////////

	private Cycloid<Integer> state = new Cycloid<>(1, 2, 3, 4);

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		DIG_STATE game_state = getPuzzle().getState();
		if (isCritical() == false && (game_state == DIG_STATE.DIG || game_state == DIG_STATE.DIGPICK) == false) {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "dig_fail");
			return;
		}

		new SoundTask().playSound(SOUNDTYPE.SOUND, "dig");

		if (state.getState() == 3) {
			setClickable(false);
			if (getPuzzle().stillContainsExit(this) == false)
				getPuzzle().closePuzzle(true);
		}

		state.cycle();
		image = null;
	}

}
