package com.sunsigne.reversedrebecca.system.controllers.mouse;

public interface MousePreseting {

	final PresetMousePos NULL = new PresetMousePos(0, 0);

	PresetMousePos getPreset();

	PresetMousePos getDefaultPreset();

	void setPreset(PresetMousePos preset);

	default boolean isPresetNull() {
		return getPreset() == NULL || getPreset() == null;
	}

}
