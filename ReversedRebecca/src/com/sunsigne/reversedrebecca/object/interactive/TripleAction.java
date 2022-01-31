package com.sunsigne.reversedrebecca.object.interactive;

public class TripleAction {

	public TripleAction(Action action1, Action action2, Action action3) {
		this.action1 = action1;
		this.action2 = action2;
		this.action3 = action3;
	}

	private Action action1;
	private Action action2;
	private Action action3;

	public Action getAction(int index) {
		if (index == 0)
			return action1;
		if (index == 1)
			return action2;
		if (index == 2)
			return action3;
		return null;
	}

}
