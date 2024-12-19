package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class UnlockedToolRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public UnlockedToolRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new UnlockedToolRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "UNLOCKEDTOOL";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {

	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return null;
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {
		String valueToCheck = target.split("\\?")[0];
		ToolPlayer tool = getTool(valueToCheck.split(":")[0]);
		LVL lvl = getLvl(valueToCheck.split(":")[1]);

		return new DifficultyComparator().canUseTool(lvl, tool.getDifficulty());
	}

	private ToolPlayer getTool(String tool) {
		for (ToolPlayer tempTool : ToolList.getList().getList()) {
			if (tempTool.getName().equalsIgnoreCase(tool))
				return tempTool;
		}
		return null;
	}

	private LVL getLvl(String lvl) {
		for (LVL tempLvl : LVL.values()) {
			if (tempLvl.getName().equalsIgnoreCase(lvl))
				return tempLvl;
		}
		return LVL.NULL;
	}

}
