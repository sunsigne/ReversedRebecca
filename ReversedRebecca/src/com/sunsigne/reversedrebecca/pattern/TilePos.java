package com.sunsigne.reversedrebecca.pattern;

public class TilePos {

	public int getTilePos(int pos, int tileSize) {
		int modulus = pos % tileSize;

		int tilePos = modulus < tileSize / 2 ? pos - modulus : pos - modulus + tileSize;
		return tilePos;
	}

}
