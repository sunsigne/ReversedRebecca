package com.sunsigne.reversedrebecca.object.puzzle.yy.intelligence;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class IntelligenceChessBoardObject extends PuzzleObject implements TickFree {

	public IntelligenceChessBoardObject(Puzzle puzzle) {
		super(puzzle, false, 0, 0, 8 * Size.M, 8 * Size.M);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		return "PUZZLE : " + "CHESS BOARD";
	}

	////////// PIECES ////////////

	private GameList<IntelligenceChessPieceObject> pieces = new GameList<>(LISTTYPE.ARRAY);

	private void createPiece(IntelligenceChessPieceObject piece) {
		pieces.addObject(piece);
		LAYER.PUZZLE.addObject(piece);
	}

	public void resetPieces() {
		pieces.getList().forEach(piece -> piece.removeObject());
		pieces.clear();

		// white
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 1, 1, true, CHESS_PIECE.ROOK));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 1, 2, true, CHESS_PIECE.KNIGHT));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 1, 3, true, CHESS_PIECE.BISHOP));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 1, 4, true, CHESS_PIECE.QUEEN));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 1, 5, true, CHESS_PIECE.KING));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 1, 6, true, CHESS_PIECE.BISHOP));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 1, 7, true, CHESS_PIECE.KNIGHT));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 1, 8, true, CHESS_PIECE.ROOK));
		for (int index = 1; index <= 8; index++)
			createPiece(new IntelligenceChessPieceObject(getPuzzle(), 2, index, true, CHESS_PIECE.PAWN));

		// black
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 8, 1, false, CHESS_PIECE.ROOK));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 8, 2, false, CHESS_PIECE.KNIGHT));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 8, 3, false, CHESS_PIECE.BISHOP));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 8, 4, false, CHESS_PIECE.QUEEN));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 8, 5, false, CHESS_PIECE.KING));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 8, 6, false, CHESS_PIECE.BISHOP));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 8, 7, false, CHESS_PIECE.KNIGHT));
		createPiece(new IntelligenceChessPieceObject(getPuzzle(), 8, 8, false, CHESS_PIECE.ROOK));
		for (int index = 1; index <= 8; index++)
			createPiece(new IntelligenceChessPieceObject(getPuzzle(), 7, index, false, CHESS_PIECE.PAWN));
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// VELOCICY ////////////

	@Override
	public void setVelX(int velX) {
		pieces.getList().forEach(piece -> piece.setVelX(velX));
		super.setVelX(velX);
	}

	@Override
	public void setVelY(int velY) {
		pieces.getList().forEach(piece -> piece.setVelY(velY));
		super.setVelY(velY);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + "chess");
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
