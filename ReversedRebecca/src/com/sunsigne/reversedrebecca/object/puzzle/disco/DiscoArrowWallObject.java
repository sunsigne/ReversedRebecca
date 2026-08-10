package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class DiscoArrowWallObject extends DiscoArrowObject {

	public DiscoArrowWallObject(Puzzle puzzle, DIRECTION facing, int x, int y) {
		super(puzzle, facing, x, y);
		speed = 1;
		this.x = getX();
		this.y = getY();
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

	private int speed;

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void movingToPlayerArrow(DiscoPlayerArrowObject player) {
		float diffX = player.getX() - getX();
		float diffY = player.getY() - getY();
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)) / 6;
		velX = speed * diffX / distance;
		velY = speed * diffY / distance;
	}

	////////// PLAY ////////////

	@Override
	protected void play(CASE caze) {
		if (caze == CASE.GOOD)
			caze = CASE.PERFECT;

		super.play(caze);
	}

	////////// TICK ////////////

	private float x, y;
	private float velX, velY;

	@Override
	public void tick() {
		x = x + velX;
		y = y + velY;
		super.tick();
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 5 + (isActivated() ? 1 : 0);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), (int) x, (int) y, getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////

	@Override
	public Rectangle getBounds() {
		int x = (int) this.x;
		int y = (int) this.y;
		int w = getWidth();
		int h = getHeight();
		return new Rectangle(x, y, w, h);
	}

}
