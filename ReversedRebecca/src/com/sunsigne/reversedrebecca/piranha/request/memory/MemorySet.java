package com.sunsigne.reversedrebecca.piranha.request.memory;

import java.util.HashSet;

public class MemorySet {

	////////// MAP OR LIST ////////////

	private static HashSet<String> set = new HashSet<>();

	public static HashSet<String> getSet() {
		return set;
	}

}
