package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.ConditionAnalyser;
import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
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
	public void doAction(LivingObject living, String target) {

		ConditionAnalyser condition = ConditionAnalyser.create(target);

		if (condition != null)
			checkFacing(condition, living, target);
		else
			doFacing(living, target);
	}

	private void checkFacing(ConditionAnalyser condition, LivingObject living, String target) {

		if (living.getFacing().getName().equalsIgnoreCase(condition.getValueToCheck()))
			condition.setMet(true);

		Instruction instruction = InstructionList.getList().getObject(new GotoInstruction());
		instruction.doAction(living, condition.getAction());
	}

	private void doFacing(LivingObject living, String target) {

		// if facing is a clear direction (ex : UP, LEFT, etc.)
		for (DIRECTION tempFacing : DIRECTION.values()) {
			if (tempFacing.getName().equalsIgnoreCase(target)) {
				living.setMotionless();
				living.setFacing(tempFacing);
				return;
			}
		}

		// if facing is a character
		for (Updatable tempUpdatable : living.getHandler().getList()) {
			if (tempUpdatable instanceof LivingObject == false)
				continue;

			LivingObject tempLiving = (LivingObject) tempUpdatable;

			if (tempLiving.getName().equalsIgnoreCase(target)) {
				PathFinder pathFinder = new PathFinder(living, tempLiving, true);
				living.setMotionless();
				living.setFacing(pathFinder.getPath());
				return;
			}
		}
	}

}
