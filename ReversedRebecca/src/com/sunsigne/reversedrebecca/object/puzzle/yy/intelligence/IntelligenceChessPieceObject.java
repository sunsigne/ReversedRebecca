package com.sunsigne.reversedrebecca.object.puzzle.yy.intelligence;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;

public class IntelligenceChessPieceObject extends PuzzleObject
		implements SheetableImage, CollisionDetector, CollisionReactor {

	public IntelligenceChessPieceObject(Puzzle puzzle, int col, int row, boolean white, CHESS_PIECE chess_piece) {
		super(puzzle, false, Size.M * (6 + col), Size.S + row * Size.M, Size.M, Size.M);

		this.white = white;
		this.chess_piece = chess_piece;
		this.goalX = getX();
		this.goalY = getY();
	}

	private boolean white;
	private CHESS_PIECE chess_piece;

	////////// NAME ////////////

	@Override
	public String toString() {
		return "PUZZLE : " + "CHESS BOARD";
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}

	////////// TICK ////////////

	private int goalX, goalY;

	public void goes(int col, int row) {
		new SoundTask().playSound(SOUNDTYPE.SOUND, "button");
		goalX = Size.M * (6 + col);
		goalY = Size.S + row * Size.M;
	}

	@Override
	public void tick() {
		if (goalX != getX())
			setVelX((goalX - getX()) / 10);
		else
			setVelX(0);

		if (goalY != getY())
			setVelY((goalY - getY()) / 10);
		else
			setVelY(0);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 2;
	}

	@Override
	public int getSheetColCriterion() {
		int color = white ? 0 : 6;
		int col = 0;

		switch (chess_piece) {
		case PAWN:
			col = 1;
			break;
		case ROOK:
			col = 2;
			break;
		case KNIGHT:
			col = 3;
			break;
		case BISHOP:
			col = 4;
			break;
		case QUEEN:
			col = 5;
			break;
		case KING:
			col = 6;
			break;
		}

		return col + color;
	}

	private BufferedImage image;

	private BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "yy");
			image = getSheetSubImage(sheet);
		}

		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////

	@Override
	public Rectangle getBounds() {
		return CollisionDetector.super.getBounds();
	}

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (white || chess_piece == CHESS_PIECE.KING)
			return;

		removeObject();
	}

}
