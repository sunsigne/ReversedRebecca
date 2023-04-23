package com.sunsigne.reversedrebecca.system.controllers.mouse;

public interface MousePreseting {

	final PresetMousePos NULL = new PresetMousePos(0, 0);

	PresetMousePos getDefaultPreset();
	
	PresetMousePos getPreset();

	void setPreset(PresetMousePos preset);

	default boolean isPresetNull() {
		return getPreset() == NULL || getPreset() == null;
	}

}
