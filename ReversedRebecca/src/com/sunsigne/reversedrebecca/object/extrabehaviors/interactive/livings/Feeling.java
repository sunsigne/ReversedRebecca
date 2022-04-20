package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings;

public interface Feeling {

	public CONDITION getCondition();

	public void setCondition(CONDITION condition);

	////////// CONDITION ////////////

	public enum CONDITION {
		GOOD("good"), SICK("sick");

		private String name;

		CONDITION(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
