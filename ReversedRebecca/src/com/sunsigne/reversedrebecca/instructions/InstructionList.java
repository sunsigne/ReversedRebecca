package com.sunsigne.reversedrebecca.instructions;

import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class InstructionList {
	
	////////// MAP OR LIST ////////////
	
	private static GameLimitedList<Instruction> list = new GameLimitedList<>(LISTTYPE.ARRAY);
	
	public static GameLimitedList<Instruction> getList() {
		return list;
	}

}
