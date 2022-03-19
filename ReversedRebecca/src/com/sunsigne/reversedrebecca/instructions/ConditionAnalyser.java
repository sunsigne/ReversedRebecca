package com.sunsigne.reversedrebecca.instructions;

public class ConditionAnalyser {

	public static ConditionAnalyser create(String target) {
		if (target.contains("?"))
			return new ConditionAnalyser(target);
		else
			return null;
	}

	private ConditionAnalyser(String target) {
		valueToCheck = String.valueOf(target.split("\\?")[0]);

		trueAction = String.valueOf(String.valueOf(target.split("\\?")[1])).split(":")[0];
		falseAction = String.valueOf(String.valueOf(target.split("\\?")[1])).split(":")[1];
	}

	////////// VALUE ////////////

	private String valueToCheck;

	public String getValueToCheck() {
		return valueToCheck;
	}

	////////// OUTPUT ////////////

	private boolean isMet;

	public void setMet(boolean isMet) {
		this.isMet = isMet;
	}

	private String trueAction;
	private String falseAction;

	public String getAction() {
		return isMet ? trueAction : falseAction;
	}

}
