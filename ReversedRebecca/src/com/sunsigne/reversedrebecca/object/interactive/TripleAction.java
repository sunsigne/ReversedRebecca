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

	public boolean canUseTool() {
		boolean canAction1 = false;
		boolean canAction2 = false;
		boolean canAction3 = false;

		if (action1 == null & action2 == null & action3 == null)
			return true;

		if (action1 != null)
			canAction1 = action1.canUseTool();
		if (action2 != null)
			canAction2 = action2.canUseTool();
		if (action3 != null)
			canAction3 = action3.canUseTool();

		return (canAction1 | canAction2 | canAction3);
	}

}
