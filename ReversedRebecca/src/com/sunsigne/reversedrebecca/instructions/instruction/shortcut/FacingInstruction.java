package com.sunsigne.reversedrebecca.instructions.instruction.shortcut;

import com.sunsigne.reversedrebecca.instructions.ConditionAnalyser;
import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.instruction.GotoInstruction;
import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.physic.PathFinder;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class FacingInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public FacingInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new FacingInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "FACING";
	}

	@Override
	public boolean isShortcut() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		ConditionAnalyser condition = ConditionAnalyser.create(target);

		if (condition != null)
			checkFacing(condition, object, target);
		else
			doFacing(object, target);
	}

	private void checkFacing(ConditionAnalyser condition, ExtraBehaviorsObject object, String target) {

		if (object.getFacing().getName().equalsIgnoreCase(condition.getValueToCheck()))
			condition.setMet(true);

		Instruction instruction = InstructionList.getList().getObject(new GotoInstruction());
		instruction.doAction(object, condition.getAction());
	}

	private void doFacing(ExtraBehaviorsObject object, String target) {

		// if facing is a clear direction (ex : UP, LEFT, etc.)
		for (DIRECTION tempFacing : DIRECTION.values()) {
			if (tempFacing.getName().equalsIgnoreCase(target)) {
				object.setMotionless();
				object.setFacing(tempFacing);
				return;
			}
		}

		// if facing is a character
		for (Updatable tempUpdatable : object.getHandler().getList()) {
			if (tempUpdatable instanceof LivingObject == false)
				continue;

			LivingObject tempLiving = (LivingObject) tempUpdatable;

			if (tempLiving.getName().equalsIgnoreCase(target)) {
				PathFinder pathFinder = new PathFinder(object, tempLiving, true);
				object.setMotionless();
				object.setFacing(pathFinder.getPath());
				return;
			}
		}
	}

}
