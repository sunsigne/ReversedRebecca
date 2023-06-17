package com.sunsigne.reversedrebecca.world.lvlstats;

public class Counter {

	public Counter(String name, int count) {
		this.setName(name);
		this.setCount(count);
	}

	////////// NAME ////////////

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	////////// COUNT ////////////

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void addCount(int count) {
		setCount(getCount() + count);
	}

	public void removeCount(int count) {
		setCount(getCount() - count);
	}

}
