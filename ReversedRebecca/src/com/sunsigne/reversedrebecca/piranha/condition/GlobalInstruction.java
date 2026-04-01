package com.sunsigne.reversedrebecca.piranha.condition;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public abstract class GlobalInstruction extends LocalInstruction {

	////////// GLOBAL INSTRUCTION ////////////

	public abstract GlobalInstruction getGlobalInstruction();

	public abstract String getConditionType();

	protected void analyse(String condition) {
		newcreateInstructionAnalyzerForAllObject(condition);

		// loadAllPiranha();
		// createInstructionAnalyzerForAllObject(condition);
	}

	protected void createInstructionAnalyzerForAllObject(String condition) {
		for (PiranhaObject tempObject : getExceptionsList().getList()) {
			if (object_list.getList().contains(tempObject))
				object_list.removeObject(tempObject);
		}

		for (PiranhaObject tempObject : object_list.getList()) {
			analyse(tempObject, condition);
		}
	}

	protected void newcreateInstructionAnalyzerForAllObject(String condition) {
		ConcurrentLinkedQueue<GenericListener> instructionEvent = null;

		for (PiranhaObject tempObject : getPiranhaList().getList()) {
			if (excludeException(condition, tempObject))
				continue;

			GenericListener instruction = () -> analyse(tempObject, condition);

			if (instructionEvent == null)
				instructionEvent = new ConcurrentLinkedQueue<>();

			instructionEvent.add(instruction);
		}

		if (instructionEvent == null)
			return;

		GenericListener event;
		while ((event = instructionEvent.poll()) != null)
			event.doAction();
	}

	protected boolean excludeException(String condition, PiranhaObject object) {
		return getExceptionsList().getList().contains(object);
	}

	////////// MAP OR LIST ////////////

	private GameList<PiranhaObject> object_list = new GameList<PiranhaObject>(LISTTYPE.LINKED);

	private static GameList<PiranhaObject> piranha_list = new GameList<PiranhaObject>(LISTTYPE.ARRAY);

	protected GameList<PiranhaObject> getList() {
		return object_list;
	}

	public static GameList<PiranhaObject> getPiranhaList() {
		if (piranha_list.getList().isEmpty() == false)
			return piranha_list;

		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.isMapLayer() == false)
				break;

			var list = new ListCloner().deepCloneByClass(tempLayer.getHandler(), PiranhaObject.class);
			piranha_list.getList().addAll(list.getList());
		}

		return piranha_list;
	}

	protected void loadAllPiranha() {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.isMapLayer() == false)
				return;

			var list = new ListCloner().deepCloneByClass(tempLayer.getHandler(), PiranhaObject.class);
			object_list.getList().addAll(list.getList());
		}
	}

	////////// OPTIMIZATION ////////////

	public abstract GameList<PiranhaObject> getExceptionsList();

	protected void resetExceptions() {
		getExceptionsList().clear();
	}

	protected void optimize(PiranhaObject object, String content) {
		if (content.contains(getConditionType()) == false)
			getExceptionsList().addObject(object);
	}

}
