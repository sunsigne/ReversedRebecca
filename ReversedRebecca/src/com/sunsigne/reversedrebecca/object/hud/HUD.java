package com.sunsigne.reversedrebecca.object.hud;

import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;

public interface HUD extends SheetableImage, Position {

	////////// HUD ////////////

	HUD getHUD();
	
	boolean isVisible();
	
	void setVisible(boolean visible);

}
