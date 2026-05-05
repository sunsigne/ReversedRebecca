package com.sunsigne.reversedrebecca.object.puzzle.yy.intelligence;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
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

	public void play() {
		moves.getState().doAction();
		moves.cycle();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		return "PUZZLE : " + "CHESS BOARD";
	}

	////////// PIECES ////////////

	private Map<String, IntelligenceChessPieceObject> pieces = new HashMap<>();

	private void createPiece(String squares, IntelligenceChessPieceObject piece) {
		pieces.put(squares, piece);
		LAYER.PUZZLE.addObject(piece);
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// VELOCICY ////////////

	@Override
	public void setVelX(int velX) {
		for (IntelligenceChessPieceObject piece : pieces.values())
			piece.setVelX(velX);

		super.setVelX(velX);
	}

	@Override
	public void setVelY(int velY) {
		for (IntelligenceChessPieceObject piece : pieces.values())
			piece.setVelY(velY);

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

	////////// ARRANGEMENTS ////////////

	private Cycloid<GenericListener> moves = createMoves();

	private Cycloid<GenericListener> createMoves() {
		Cycloid<GenericListener> moves;
		moves = new Cycloid<>(() -> move01(), () -> {
		}, () -> move02(), () -> {
		}, () -> move03(), () -> {
		}, () -> move04(), () -> move05(), () -> move06(), () -> move07(), () -> move08(), () -> move09(),
				() -> move10());
		return moves;
	}

	public void resetPieces() {
		for (IntelligenceChessPieceObject piece : pieces.values())
			LAYER.PUZZLE.getHandler().removeObject(piece);

		pieces.clear();

		// white
		createPiece("a1", new IntelligenceChessPieceObject(getPuzzle(), 1, 1, true, CHESS_PIECE.ROOK));
		createPiece("b1", new IntelligenceChessPieceObject(getPuzzle(), 1, 2, true, CHESS_PIECE.KNIGHT));
		createPiece("c1", new IntelligenceChessPieceObject(getPuzzle(), 1, 3, true, CHESS_PIECE.BISHOP));
		createPiece("d1", new IntelligenceChessPieceObject(getPuzzle(), 1, 4, true, CHESS_PIECE.QUEEN));
		createPiece("e1", new IntelligenceChessPieceObject(getPuzzle(), 1, 5, true, CHESS_PIECE.KING));
		createPiece("f1", new IntelligenceChessPieceObject(getPuzzle(), 1, 6, true, CHESS_PIECE.BISHOP));
		createPiece("g1", new IntelligenceChessPieceObject(getPuzzle(), 1, 7, true, CHESS_PIECE.KNIGHT));
		createPiece("h1", new IntelligenceChessPieceObject(getPuzzle(), 1, 8, true, CHESS_PIECE.ROOK));

		createPiece("a2", new IntelligenceChessPieceObject(getPuzzle(), 2, 1, true, CHESS_PIECE.PAWN));
		createPiece("b2", new IntelligenceChessPieceObject(getPuzzle(), 2, 2, true, CHESS_PIECE.PAWN));
		createPiece("c2", new IntelligenceChessPieceObject(getPuzzle(), 2, 3, true, CHESS_PIECE.PAWN));
		createPiece("d2", new IntelligenceChessPieceObject(getPuzzle(), 2, 4, true, CHESS_PIECE.PAWN));
		createPiece("e2", new IntelligenceChessPieceObject(getPuzzle(), 2, 5, true, CHESS_PIECE.PAWN));
		createPiece("f2", new IntelligenceChessPieceObject(getPuzzle(), 2, 6, true, CHESS_PIECE.PAWN));
		createPiece("g2", new IntelligenceChessPieceObject(getPuzzle(), 2, 7, true, CHESS_PIECE.PAWN));
		createPiece("h2", new IntelligenceChessPieceObject(getPuzzle(), 2, 8, true, CHESS_PIECE.PAWN));

		// black
		createPiece("a8", new IntelligenceChessPieceObject(getPuzzle(), 8, 1, false, CHESS_PIECE.ROOK));
		createPiece("b8", new IntelligenceChessPieceObject(getPuzzle(), 8, 2, false, CHESS_PIECE.KNIGHT));
		createPiece("c8", new IntelligenceChessPieceObject(getPuzzle(), 8, 3, false, CHESS_PIECE.BISHOP));
		createPiece("d8", new IntelligenceChessPieceObject(getPuzzle(), 8, 4, false, CHESS_PIECE.QUEEN));
		createPiece("e8", new IntelligenceChessPieceObject(getPuzzle(), 8, 5, false, CHESS_PIECE.KING));
		createPiece("f8", new IntelligenceChessPieceObject(getPuzzle(), 8, 6, false, CHESS_PIECE.BISHOP));
		createPiece("g8", new IntelligenceChessPieceObject(getPuzzle(), 8, 7, false, CHESS_PIECE.KNIGHT));
		createPiece("h8", new IntelligenceChessPieceObject(getPuzzle(), 8, 8, false, CHESS_PIECE.ROOK));

		createPiece("a7", new IntelligenceChessPieceObject(getPuzzle(), 7, 1, false, CHESS_PIECE.PAWN));
		createPiece("b7", new IntelligenceChessPieceObject(getPuzzle(), 7, 2, false, CHESS_PIECE.PAWN));
		createPiece("c7", new IntelligenceChessPieceObject(getPuzzle(), 7, 3, false, CHESS_PIECE.PAWN));
		createPiece("d7", new IntelligenceChessPieceObject(getPuzzle(), 7, 4, false, CHESS_PIECE.PAWN));
		createPiece("e7", new IntelligenceChessPieceObject(getPuzzle(), 7, 5, false, CHESS_PIECE.PAWN));
		createPiece("f7", new IntelligenceChessPieceObject(getPuzzle(), 7, 6, false, CHESS_PIECE.PAWN));
		createPiece("g7", new IntelligenceChessPieceObject(getPuzzle(), 7, 7, false, CHESS_PIECE.PAWN));
		createPiece("h7", new IntelligenceChessPieceObject(getPuzzle(), 7, 8, false, CHESS_PIECE.PAWN));
	}

	private void move01() {
		var pawn = pieces.get("e2");
		pieces.remove("e2");
		pieces.put("e4", pawn);
		pawn.goes(4, 5);
	}

	private void move02() {
		var knight = pieces.get("g1");
		pieces.remove("g1");
		pieces.put("f3", knight);
		knight.goes(3, 6);
	}

	private void move03() {
		var pawn = pieces.get("d2");
		pieces.remove("d2");
		pieces.put("d4", pawn);
		pawn.goes(4, 4);
	}

	private void move04() {
		var knight = pieces.get("b1");
		pieces.remove("b1");
		pieces.put("c2", knight);
		knight.goes(3, 3);
	}

	private void move05() {
		var bishop = pieces.get("f1");
		pieces.remove("f1");
		pieces.put("b5", bishop);
		bishop.goes(5, 2);
	}

	private void move06() {
		var bishop = pieces.get("c1");
		pieces.remove("c1");
		pieces.put("g5", bishop);
		bishop.goes(5, 7);
	}

	private void move07() {
		var rook1 = pieces.get("a1");
		pieces.remove("a1");
		pieces.put("a7", rook1);
		rook1.goes(7, 1);

		var rook2 = pieces.get("h1");
		pieces.remove("h1");
		pieces.put("h7", rook2);
		rook2.goes(7, 8);
	}

	private void move08() {
		pieces.get("a7").goes(7, 8);
		pieces.get("h7").goes(7, 1);
	}

	private void move09() {
		var queen = pieces.get("d1");
		pieces.remove("d1");
		pieces.put("a8", queen);
		queen.goes(8, 1);

		var rook1 = pieces.get("a7");
		pieces.remove("a7");
		pieces.put("h4", rook1);
		rook1.goes(4, 8);

		var rook2 = pieces.get("h7");
		pieces.remove("h7");
		pieces.put("a4", rook2);
		rook2.goes(4, 1);
	}

	private void move10() {
		var queen = pieces.get("a8");
		pieces.remove("a8");
		pieces.put("h8", queen);
		queen.goes(8, 8);

		var rook1 = pieces.get("a4");
		pieces.remove("a4");
		pieces.put("c4", rook1);
		rook1.goes(4, 3);

		var rook2 = pieces.get("h4");
		pieces.remove("h4");
		pieces.put("f4", rook2);
		rook2.goes(4, 6);
	}

}
