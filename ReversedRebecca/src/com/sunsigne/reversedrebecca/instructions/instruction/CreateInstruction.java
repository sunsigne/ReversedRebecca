package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class CreateInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public CreateInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new CreateInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "CREATE";
	}

	@Override
	public boolean isShortcut() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		// determinate the position

		String pos = String.valueOf(target.split(":")[1]);
		int x = Integer.parseInt(pos.split("-")[0]);
		int y = Integer.parseInt(pos.split("-")[1]);
		GoalObject goal = new GoalObject(x, y, false);

		// determinate the type of object

		String type = String.valueOf(target.split(":")[0]);

		GameObject creation = determinateCreation(type, goal.getX(), goal.getY());

		// creation of the object

		if (creation != null)
			object.getHandler().getList().add(0, creation);
	}

	private GameObject determinateCreation(String type, int x, int y) {

		int red = Integer.parseInt(type.split("-")[0]);
		int green = Integer.parseInt(type.split("-")[1]);
		int blue = Integer.parseInt(type.split("-")[2]);

		for (Mappable tempMappable : new MapCreator().getList().getList()) {

			int tempRed = tempMappable.getRedCode();
			int tempGreen = tempMappable.getGreenCode();
			int tempBlue = tempMappable.getBlueCode();

			if (red == tempRed && green == tempGreen && blue == tempBlue) {
				return tempMappable.createObject(x, y);
			}
		}

		return null;
	}

}
