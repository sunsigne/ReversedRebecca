package com.sunsigne.reversedrebecca.object.characteristics.interactive;

public class TripleAction {

	public TripleAction(String noActionText, Action action1, Action action2, Action action3) {
		this.noActionText = noActionText;

		this.action1 = action1;
		this.action2 = action2;
		this.action3 = action3;
	}

	////////// INTERACTION ////////////

	private String noActionText;

	public String getNoActionText() {
		return noActionText;
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

	////////// USEFUL ////////////

	public TripleAction removeAction(Action action) {
		if (action == action1)
			return new TripleAction(noActionText, null, action2, action3);
		if (action == action2)
			return new TripleAction(noActionText, action1, null, action3);
		if (action == action3)
			return new TripleAction(noActionText, action1, action2, null);
		
		return new TripleAction(noActionText, action1, action2, action3);
	}

	///// no action /////

	public boolean cannotDoAnyAction() {
		if (action1 != null) {
			if (action1.canDoAction())
				return false;
		}
		if (action2 != null) {
			if (action2.canDoAction())
				return false;
		}
		if (action3 != null) {
			if (action3.canDoAction())
				return false;
		}
		return true;
	}

	///// one action /////

	public boolean canDoExactlyOneAction() {
		if (action1 != null & action2 == null & action3 == null)
			return action1.canDoAction();
		if (action1 == null & action2 != null & action3 == null)
			return action2.canDoAction();
		if (action1 == null & action2 == null & action3 != null)
			return action3.canDoAction();
		return false;
	}

	public Action getTheOnlyOnePerformableAction() {
		if (!canDoExactlyOneAction())
			return null;
		if (action1 != null)
			return action1;
		if (action2 != null)
			return action2;
		if (action3 != null)
			return action3;
		return null;
	}

	///// two actions /////

	public boolean canDoExactlyTwoActions() {
		if (action1 != null & action2 != null & action3 == null)
			return action1.canDoAction() & action2.canDoAction();
		if (action1 != null & action2 == null & action3 != null)
			return action1.canDoAction() & action3.canDoAction();
		if (action1 == null & action2 != null & action3 != null)
			return action2.canDoAction() & action3.canDoAction();

		if (action1 != null & action2 != null & action3 != null) {
			if (action1.canDoAction() & action2.canDoAction() & !action3.canDoAction())
				return true;
			if (action1.canDoAction() & !action2.canDoAction() & action3.canDoAction())
				return true;
			if (!action1.canDoAction() & action2.canDoAction() & action3.canDoAction())
				return true;
		}
		return false;
	}

	public Action[] getTheOnlyTwoPerformableActions() {
		if (!canDoExactlyTwoActions())
			return null;

		if (action1 == null)
			return new Action[] { action2, action3 };
		if (action2 == null)
			return new Action[] { action1, action3 };
		if (action3 == null)
			return new Action[] { action1, action2 };

		if (!action1.canDoAction())
			return new Action[] { action2, action3 };
		if (!action2.canDoAction())
			return new Action[] { action1, action3 };
		if (!action3.canDoAction())
			return new Action[] { action1, action2 };

		return null;
	}

	///// three actions /////

	public boolean canDoExactlyThreeActions() {
		boolean canAction1 = false;
		boolean canAction2 = false;
		boolean canAction3 = false;

		if (action1 == null | action2 == null | action3 == null)
			return false;

		if (action1 != null)
			canAction1 = action1.canDoAction();
		if (action2 != null)
			canAction2 = action2.canDoAction();
		if (action3 != null)
			canAction3 = action3.canDoAction();

		return (canAction1 & canAction2 & canAction3);
	}

}
