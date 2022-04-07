package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.WaitforBehavior;
import com.sunsigne.reversedrebecca.pattern.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class WaitforInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public WaitforInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new WaitforInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "WAITFOR";
	}

	@Override
	public boolean isShortcut() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		String condition = String.valueOf(target.split(",")[0]);

		String type = String.valueOf(condition.split(":")[0]);
		String value = String.valueOf(condition.split(":")[1]);
		String action = String.valueOf(target.split(",")[1]);

		GenericListener generic = new GenericListener() {

			@Override
			public void doAction() {
				Instruction instruction = InstructionList.getList().getObject(new GotoInstruction());
				instruction.doAction(object, action);
			}
		};

		// search for listener
		ConditionalListener listener = null;

		if (type.equalsIgnoreCase("PLAYER_DISTANCE"))
			listener = getPlayerDistanceListener(generic, object, Integer.parseInt(value));

		if (listener != null)
			object.addBehavior(new WaitforBehavior(object, listener));
	}

	////////// INSTRUCTION ////////////

	private ConditionalListener getPlayerDistanceListener(GenericListener generic, ExtraBehaviorsObject object,
			int distance) {

		return new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				return new PlayerFinder().isPlayerFutherThan(object, distance);
			}

			@Override
			public void doAction() {
				generic.doAction();
			}
		};
	}

}
