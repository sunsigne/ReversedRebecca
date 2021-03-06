package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.object.gui.GUI;
import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.physic.Physic;
import com.sunsigne.reversedrebecca.piranha.Piranha;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class RessourceLoader {

	protected void loadMinimalRessources() {
		Language.getInstance();
	}

	protected void loadRessources() {

		new LayerDualizer().dualizeSameFloorLayers();
		new ForceInit().loadAllClassesInPackage(GUI.class.getPackageName());
		new ForceInit().loadAllClassesInPackage(Mappable.class.getPackageName());

		new Physic().loadRessources();
		new Piranha().loadRessources();
	}

}
