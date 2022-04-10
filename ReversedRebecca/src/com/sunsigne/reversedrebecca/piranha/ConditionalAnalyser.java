package com.sunsigne.reversedrebecca.piranha;

public class ConditionalAnalyser {

	public static ConditionalAnalyser create(String target) {
		if (target.contains("?"))
			return new ConditionalAnalyser(target);
		else
			return null;
	}

	private ConditionalAnalyser(String target) {
		valueToCheck = String.valueOf(target.split("\\?")[0]);

		String action = String.valueOf(target.split("\\?")[1]);		
		trueAction = String.valueOf(action.split("\\/")[0]);
		falseAction = String.valueOf(action.split("\\/")[1]);
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
