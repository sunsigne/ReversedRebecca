package com.sunsigne.reversedrebecca.system.camera;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class CameraOption {

	////////// CAMERA OPTION ////////////

	String file = new FileTask().getUserDataPath() + "options.csv";

	private static CAMERA_TYPE type;

	public static CAMERA_TYPE getType() {
		if (type == null)
			type = new CameraOption().getRegisteredType();
		return type;
	}

	private CAMERA_TYPE getRegisteredType() {
		String registeredType = new FileTask().read(getValueToRead(), file);
		for (CAMERA_TYPE tempType : CAMERA_TYPE.values()) {
			if (tempType.getName().equalsIgnoreCase(registeredType))
				return tempType;
		}

		// should not occurs, exept if someone messed up with options.csv
		return CAMERA_TYPE.STATIC;
	}

	private static String getValueToRead() {
		return "Camera";
	}

	public void registerType(CAMERA_TYPE cameraType) {
		new FileTask().write(getValueToRead(), file, cameraType.getName());
		type = null;
	}

	////////// CAMERA TYPE ////////////

	public enum CAMERA_TYPE {
		STATIC("static"), DYNAMIC("dynamic");

		CAMERA_TYPE(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public CAMERA_TYPE getPrevious() {
			switch (name) {
			case "dynamic":
				return CAMERA_TYPE.STATIC;
			case "static":
				return CAMERA_TYPE.DYNAMIC;
			}
			return null;
		}

		public CAMERA_TYPE getNext() {
			switch (name) {
			case "static":
				return CAMERA_TYPE.DYNAMIC;
			case "dynamic":
				return CAMERA_TYPE.STATIC;
			}
			return null;
		}
	}

}
