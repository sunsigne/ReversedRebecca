package com.sunsigne.reversedrebecca.instructions.instruction;

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
	public void doAction(LivingObject living, String target) {

		String goal = String.valueOf(target.split(",")[0]);
		boolean isGoalInSight = false;

		String inSighAction = String.valueOf(target.split(",")[1]);
		String unseenAction = String.valueOf(target.split(",")[2]);

		for (Updatable tempUpdatable : living.getHandler().getList()) {
			if (tempUpdatable instanceof LivingObject == false)
				continue;

			LivingObject tempLiving = (LivingObject) tempUpdatable;

			if (goal.equalsIgnoreCase("PLAYER")) {
				isGoalInSight = new SightFinder(living, new PlayerFinder().getPlayer()).isGoalInSight();
				break;
			}

			if (tempLiving.getName().equalsIgnoreCase(target)) {
				isGoalInSight = new SightFinder(living, tempLiving).isGoalInSight();
				break;
			}
		}

		String sighAction = isGoalInSight ? inSighAction : unseenAction;
		Instruction instruction = InstructionList.getList().getObject(new GotoInstruction());
		instruction.doAction(living, sighAction);
	}

}
