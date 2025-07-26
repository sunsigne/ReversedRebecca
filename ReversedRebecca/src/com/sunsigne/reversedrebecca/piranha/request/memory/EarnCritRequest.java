package com.sunsigne.reversedrebecca.piranha.request.memory;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class EarnCritRequest implements Request {

	////////// REQUEST ////////////

	public EarnCritRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new EarnCritRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "EARNCRIT";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		ToolPlayer tool = getTool(target.split(",")[0]);
		int criticalChance = Integer.parseInt(target.split(",")[1]);
		
		tool.setCriticalChance(tool.getCriticalChance() + criticalChance);
	}

	private ToolPlayer getTool(String tool) {
		for (ToolPlayer tempTool : ToolList.getList().getList()) {
			if (tempTool.getName().equalsIgnoreCase(tool))
				return tempTool;
		}
		return null;
	}
	
}
