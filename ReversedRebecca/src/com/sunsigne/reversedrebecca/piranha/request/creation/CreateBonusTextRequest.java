package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.other.BonusText;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;

public class CreateBonusTextRequest implements IndexRequest {

	////////// REQUEST ////////////

	public CreateBonusTextRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new CreateBonusTextRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CREATE_BONUSTEXT";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		// determinate the position
		String pos = String.valueOf(target.split(":")[0]);
		boolean onTheSpot = pos.split("-")[0].equalsIgnoreCase("onthespot");
		int x = onTheSpot ? (object.getX() / Size.M) : Integer.parseInt(pos.split("-")[0]);
		int y = onTheSpot ? (object.getY() / Size.M) : Integer.parseInt(pos.split("-")[1]);

		// refine position
		GoalObject goal = new GoalObject(x, y, false);
		x = goal.getX();
		y = goal.getY();

		// determinate the text
		String dataText = String.valueOf(target.split(":")[1]);
		String path = object.getPiranhaFile().substring(0, object.getPiranhaFile().length() - 10);
		path = path.concat(FilePath.BONUS_TEXT);
		String text = new Translatable().getTranslatedText(dataText, path).toUpperCase();

		// creation of the object
		BonusText creation = new BonusText(text, x, y);
		LAYER.WORLD_TEXT.addObject(creation);
	}

}
