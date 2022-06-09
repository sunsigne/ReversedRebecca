package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class PeripheralNetworkMap extends PeripheralObject {

	public PeripheralNetworkMap(Puzzle puzzle) {
		super(puzzle, "Net-Map");
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "network_map";
	}

}
