package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.ConditionAnalyser;
import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
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
		return "SEE";
	}

	@Override
	public boolean isShortcut() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		ConditionAnalyser condition = ConditionAnalyser.create(target);

		for (Updatable tempUpdatable : object.getHandler().getList()) {
			if (tempUpdatable instanceof LivingObject == false)
				continue;

			LivingObject tempLiving = (LivingObject) tempUpdatable;

			if (condition.getValueToCheck().equalsIgnoreCase("PLAYER")) {
				condition.setMet(new SightFinder(object, new PlayerFinder().getPlayer()).isGoalInSight());
				break;
			}

			if (tempLiving.getName().equalsIgnoreCase(condition.getValueToCheck())) {
				condition.setMet(new SightFinder(object, tempLiving).isGoalInSight());
				break;
			}
		}

		Instruction instruction = InstructionList.getList().getObject(new GotoInstruction());
		instruction.doAction(object, condition.getAction());
	}

}
