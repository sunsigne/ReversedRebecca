package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler;

import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.yychallenge.YYChallengeObject;
import com.sunsigne.reversedrebecca.object.puzzler.yychallenge.YYChallengeObject.CHALLENGE_TYPE;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableYYChallenge implements MappablePuzzler {

	private MappableYYChallenge() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableYYChallenge();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public PuzzlerObject getPuzzlerObject(DEV_LVL devDifficulty, LVL difficulty, COLOR color, DIRECTION facing,
			boolean horizontal, int x, int y) {

		CHALLENGE_TYPE type = getType(facing);
		return new YYChallengeObject(difficulty, type, x, y);		
	}

	private CHALLENGE_TYPE getType(DIRECTION facing) {
		
		
		switch (facing) {
		case LEFT:
		case RIGHT:
		case UP:
		case DOWN:
			return CHALLENGE_TYPE.INTELLIGENCE;
		case NULL:
			return CHALLENGE_TYPE.STRENGHT;
		}

		return CHALLENGE_TYPE.STRENGHT;
	}

	@Override
	public int[] rgbCode() {
		// green = 255 to X
		// blue = 0 to 5
		int[] rgb = { 8, 256, -1 };
		return rgb;
	}

}
