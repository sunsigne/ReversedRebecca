package com.sunsigne.reversedrebecca.object.puzzle.yy.intelligence;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
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
		return PhysicLinker.PUZZLE_MOVER;
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
				() -> move10(), () -> move11(), () -> move12());
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
		pieces.put("c3", knight);
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
		// rooks
		pieces.get("a7").goes(7, 8);
		pieces.get("h7").goes(7, 1);
	}

	private void move09() {
		new CameraShaker().shaking(SHAKE.TINY);

		var queen = pieces.get("d1");
		pieces.remove("d1");
		pieces.put("a8", queen);
		queen.goes(8, 1, true);

		// pieces
		pieces.get("a7").goes(4, 8);
		pieces.get("h7").goes(4, 1);
		pieces.get("b5").goes(6, 3, true);
		pieces.get("g5").goes(6, 6, true);
		pieces.get("c3").goes(3, 2, true);
		pieces.get("f3").goes(3, 7, true);
	}

	private void move10() {
		new CameraShaker().shaking(SHAKE.TINY);

		var queen = pieces.get("a8");
		pieces.remove("a8");
		pieces.put("h8", queen);
		queen.goes(8, 8, true);

		// pieces
		pieces.get("a7").goes(5, 7);
		pieces.get("h7").goes(5, 2);
		pieces.get("b5").goes(6, 2, true);
		pieces.get("g5").goes(6, 7, true);
		pieces.get("c3").goes(3, 3, true);
		pieces.get("f3").goes(3, 6, true);
	}

	private void move11() {
		new CameraShaker().shaking(SHAKE.LITTLE);

		var king = pieces.get("e8");
		pieces.remove("e8");
		pieces.put("e5", king);
		king.goes(5, 5);

		// pieces
		pieces.get("a2").goes(8, 1);
		pieces.get("b2").goes(8, 2);
		pieces.get("c2").goes(8, 3, true);
		pieces.get("d4").goes(8, 4, true);
		pieces.get("e4").goes(8, 5, true);
		pieces.get("f2").goes(8, 6, true);
		pieces.get("g2").goes(8, 7, true);
	}

	private void move12() {
		new CameraShaker().shaking(SHAKE.LITTLE);

		// queens
		pieces.get("a2").goes(6, 6);
		pieces.get("b2").goes(6, 5);
		pieces.get("c2").goes(4, 4);
		pieces.get("d4").goes(4, 5, true);
		pieces.get("e4").goes(5, 6, true);
		pieces.get("f2").goes(4, 6, true);
		pieces.get("g2").goes(5, 4, true);
		pieces.get("h8").goes(6, 4, true);

		// pieces
		pieces.get("a7").goes(6, 3, true);
		pieces.get("h7").goes(7, 5, true);
		pieces.get("b5").goes(6, 7, true);
		pieces.get("g5").goes(4, 7, true);
		pieces.get("c3").goes(3, 5, true);
		pieces.get("f3").goes(4, 3, true);
		pieces.get("h2").goes(2, 12, true);

		move13();
	}

	private void move13() {
		var king = pieces.get("e1");
		pieces.remove("e1");
		pieces.put("e5", king);
		int time = 20;
		new GameTimer(time + 10, true, () -> {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "hit_small");
			king.goes(5, 5, true);
		});
		new GameTimer(time + 35, true, () -> new SoundTask().playSound(SOUNDTYPE.SOUND, "hit_large"));
		new GameTimer(time + 40, true, () -> throwingBoard());
	}

	private void throwingBoard() {
		new CameraShaker().shaking(SHAKE.STRONG);
		setVelX(40);

		for (IntelligenceChessPieceObject piece : pieces.values()) {
			int row = new RandomGenerator().getIntBetween(-10, 10);
			piece.goes(15, row, true);
		}
	}

}
