package com.sunsigne.reversedrebecca.system;

import java.io.File;

import com.sunsigne.reversedrebecca.characteristics.Characteristic;
import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.physic.Physic;
import com.sunsigne.reversedrebecca.piranha.Piranha;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.Options;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.achievement.Achievements;
import com.sunsigne.reversedrebecca.ressources.images.Textures;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.lang.TranslationAnalyzer;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.system.DifficultyOption.GAME_DIFFICULTY;
import com.sunsigne.reversedrebecca.system.camera.Camera;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class RessourceLoader {

	private void loadUserdata() {
		if (new FileTask().doesExist(true, ""))
			return;

		File folder = new File(FilePath.USERDATA_PATH.substring(0, FilePath.USERDATA_PATH.length() - 1));
		folder.mkdir();
	}

	protected void loadMinimalRessources() {
		loadUserdata();
		new Options().loadRessources();
		new Camera().setZoom(1f);
		Language.getInstance();
	}

	protected void loadRessources() {
		
		new TranslationAnalyzer().loadRessources();
		new LayerDualizer().dualizeSameFloorLayers();
		new ForceInit().loadAllClassesInPackage(HUD.class.getPackageName());
		new ForceInit().loadAllClassesInPackage(Mappable.class.getPackageName());
		new ForceInit().loadAllClassesInPackage(Characteristic.class.getPackageName());

		new Physic().loadRessources();
		new Piranha().loadRessources();
		new Textures().loadRessources();
		new Achievements().loadRessources();
		new PausePreventer().loadRessources();

		if (DifficultyOption.getDifficulty() == GAME_DIFFICULTY.HARD)
			new Save().resetProgression();
	}

}
