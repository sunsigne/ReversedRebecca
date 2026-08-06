package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class DiscoArrowWallObject extends DiscoArrowObject {

	public DiscoArrowWallObject(Puzzle puzzle, DIRECTION facing, int x, int y) {
		super(puzzle, facing, x, y);
	}

	////////// NAME ////////////

	protected String getName() {
		return "ARROW WALL";
	}

	////////// ACTIVATION ////////////

	private boolean activated;

	public boolean isActivated() {
		return activated;
	}

	public void activate(DiscoPlayerArrowObject[] player_arrows) {
		this.activated = true;
		image = null;

		for (var temp_player_arrows : player_arrows)
			if (getFacing() == temp_player_arrows.getFacing())
				movingToPlayerArrow(temp_player_arrows);
	}

	public void movingToPlayerArrow(DiscoPlayerArrowObject player) {
		float diffX = getX() - player.getX();
		float diffY = getY() - player.getY();
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)) / 6;

		setVelX(2 * Math.round((-1 / distance) * diffX));
		setVelY(2 * Math.round((-1 / distance) * diffY));
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 5 + (isActivated() ? 1 : 0);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
