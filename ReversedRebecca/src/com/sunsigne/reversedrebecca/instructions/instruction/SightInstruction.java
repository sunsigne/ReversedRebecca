package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.ConditionAnalyser;
import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.SightFinder;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class SightInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public SightInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new SightInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "SIGHT";
	}
	
	@Override
	public boolean isShortcut() {
		return false;
	}
	
	@Override
	public void doAction(LivingObject living, String target) {

		ConditionAnalyser condition = ConditionAnalyser.create(target);
	
		for (Updatable tempUpdatable : living.getHandler().getList()) {
			if (tempUpdatable instanceof LivingObject == false)
				continue;

			LivingObject tempLiving = (LivingObject) tempUpdatable;

			if (condition.getValueToCheck().equalsIgnoreCase("PLAYER")) {
				condition.setMet(new SightFinder(living, new PlayerFinder().getPlayer()).isGoalInSight());
				break;
			}

			if (tempLiving.getName().equalsIgnoreCase(condition.getValueToCheck())) {
				condition.setMet(new SightFinder(living, tempLiving).isGoalInSight());
				break;
			}
		}

		Instruction instruction = InstructionList.getList().getObject(new GotoInstruction());
		instruction.doAction(living, condition.getAction());
	}

}
