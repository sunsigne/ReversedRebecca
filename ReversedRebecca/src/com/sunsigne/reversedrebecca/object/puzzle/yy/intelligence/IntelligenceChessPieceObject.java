package com.sunsigne.reversedrebecca.object.puzzle.yy.intelligence;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;

public class IntelligenceChessPieceObject extends PuzzleObject implements SheetableImage {

	public IntelligenceChessPieceObject(Puzzle puzzle, int col, int row, boolean white, CHESS_PIECE chess_piece) {
		super(puzzle, false, Size.M * (6 + col), Size.S + row * Size.M, Size.M, Size.M);

		this.white = white;
		this.chess_piece = chess_piece;
	}

	private boolean white;
	private CHESS_PIECE chess_piece;

	////////// NAME ////////////

	@Override
	public String toString() {
		return "PUZZLE : " + "CHESS BOARD";
	}

	////////// USEFULL ////////////

	protected int getCol(int col) {
		return (col / Size.M);
	}

	protected int getRow(int row) {
		return (row / Size.M);
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

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

}
