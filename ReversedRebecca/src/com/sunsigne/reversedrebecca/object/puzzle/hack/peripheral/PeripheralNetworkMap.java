package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralNetworkMap extends PeripheralObject {

	public PeripheralNetworkMap(Puzzle puzzle, int x, int y) {
		super(puzzle, "Net-Map", x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "network_map";
	}

}
