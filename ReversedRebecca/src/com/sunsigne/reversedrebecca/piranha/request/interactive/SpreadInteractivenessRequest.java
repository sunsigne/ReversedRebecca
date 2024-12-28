package com.sunsigne.reversedrebecca.piranha.request.interactive;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class SpreadInteractivenessRequest implements IndexRequest {

	////////// REQUEST ////////////

	public SpreadInteractivenessRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new SpreadInteractivenessRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SPREAD_INTERACTIVENESS";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		String action = target.split(",")[0].toUpperCase();

		switch (action) {

		case "ADD":
			addAction(object, target);
			break;
		case "REMOVE":
			removeAction(object, target);
			break;
		case "CLEAR":
			object.getSpreadInteractivenessList().clear();
			break;
		}
	}

	private GoalObject getGoal(String target) {
		String pos = String.valueOf(target.split(",")[1]);
		int x = Integer.parseInt(pos.split("-")[0]);
		int y = Integer.parseInt(pos.split("-")[1]);

		return new GoalObject(x, y, false);
	}

	private void addAction(PiranhaObject object, String target) {

		// determinate the position
		GoalObject goal = getGoal(target);
		int x = goal.getX();
		int y = goal.getY();

		// determinate the type of object
		Velocity interactive = determinateCreation(object, x, y);

		// creation of the object
		object.getSpreadInteractivenessList().addObject(interactive);

	}

	private void removeAction(PiranhaObject object, String target) {

		// determinate the position

		GoalObject goal = getGoal(target);
		int x = goal.getX();
		int y = goal.getY();

		var clone = new ListCloner().deepClone(object.getSpreadInteractivenessList());
		for (Velocity tempInteractive : clone.getList())
			if (x == tempInteractive.getX() && y == tempInteractive.getY())
				object.getSpreadInteractivenessList().removeObject(tempInteractive);
	}

	private Velocity determinateCreation(PiranhaObject object, int x, int y) {
		Velocity interactive = new Velocity() {

			@Override
			public Handler getHandler() {
				return object.getHandler();
			}

			////////// POSITION ////////////

			@Override
			public int getX() {
				return x;
			}

			@Override
			public int getY() {
				return y;
			}

			@Override
			public void setX(int x) {

			}

			@Override
			public void setY(int y) {

			}

			////////// SIZE ////////////

			@Override
			public int getWidth() {
				return object.getWidth();
			}

			@Override
			public int getHeight() {
				return object.getHeight();
			}

			////////// VELOCICY ////////////

			@Override
			public int getVelX() {
				return 0;
			}

			@Override
			public int getVelY() {
				return 0;
			}

			@Override
			public void setVelX(int velX) {

			}

			@Override
			public void setVelY(int velY) {

			}

			////////// TICK ////////////

			@Override
			public void tick() {

			}

			////////// RENDER ////////////

			@Override
			public void render(Graphics g) {

			}

		};

		return interactive;
	}

}
