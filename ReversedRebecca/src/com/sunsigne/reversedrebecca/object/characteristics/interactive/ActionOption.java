package com.sunsigne.reversedrebecca.object.characteristics.interactive;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class ActionOption {

	////////// ACTION OPTION ////////////

	private String file = "options.csv";
	private boolean userData = true;

	private static ACTION_HIGHLIGHT highlight;

	public static ACTION_HIGHLIGHT getHighlight() {
		if (highlight == null)
			highlight = new ActionOption().getRegisteredHighlight();
		return highlight;
	}

	private ACTION_HIGHLIGHT getRegisteredHighlight() {
		String registeredHighlight = new FileTask().read(userData, getHighlightValueToRead(), file);
		for (ACTION_HIGHLIGHT tempHighlight : ACTION_HIGHLIGHT.values()) {
			if (tempHighlight.getName().equalsIgnoreCase(registeredHighlight))
				return tempHighlight;
		}

		// should not occurs, exept if someone messed up with options.csv
		return ACTION_HIGHLIGHT.BRIGHT;
	}

	private static String getHighlightValueToRead() {
		return "Highlight";
	}

	public void registerHighlight(ACTION_HIGHLIGHT actionHighlight) {
		new FileTask().write(getHighlightValueToRead(), file, actionHighlight.getName());
		highlight = null;
	}
	
	private static ACTION_DESIGN design;

	public static ACTION_DESIGN getDesign() {
		if (design == null)
			design = new ActionOption().getRegisteredDesign();
		return design;
	}

	private ACTION_DESIGN getRegisteredDesign() {
		String registeredDesign = new FileTask().read(userData, getDesignValueToRead(), file);
		for (ACTION_DESIGN tempDesign : ACTION_DESIGN.values()) {
			if (tempDesign.getName().equalsIgnoreCase(registeredDesign))
				return tempDesign;
		}

		// should not occurs, exept if someone messed up with options.csv
		return ACTION_DESIGN.COLOR;
	}

	private static String getDesignValueToRead() {
		return "Design";
	}

	public void registerDesign(ACTION_DESIGN actionDesign) {
		new FileTask().write(getDesignValueToRead(), file, actionDesign.getName());
		design = null;
	}

	////////// ACTION HIGHLIGHT ////////////

	public enum ACTION_HIGHLIGHT {
		NEUTRAL("neutral"), BRIGHT("bright");

		ACTION_HIGHLIGHT(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public ACTION_HIGHLIGHT getPrevious() {
			switch (name) {
			case "neutral":
				return ACTION_HIGHLIGHT.BRIGHT;
			case "bright":
				return ACTION_HIGHLIGHT.NEUTRAL;
			}
			return null;
		}

		public ACTION_HIGHLIGHT getNext() {
			switch (name) {
			case "bright":
				return ACTION_HIGHLIGHT.NEUTRAL;
			case "neutral":
				return ACTION_HIGHLIGHT.BRIGHT;
			}
			return null;
		}
	}
	
	////////// ACTION DESIGN ////////////
	
	public enum ACTION_DESIGN {
		COLOR("color"), NUMBER("number");
	
		ACTION_DESIGN(String name) {
			this.name = name;
		}
	
		private String name;
	
		public String getName() {
			return name;
		}
	
		public ACTION_DESIGN getPrevious() {
			switch (name) {
			case "color":
				return ACTION_DESIGN.NUMBER;
			case "number":
				return ACTION_DESIGN.COLOR;
			}
			return null;
		}
	
		public ACTION_DESIGN getNext() {
			switch (name) {
			case "number":
				return ACTION_DESIGN.COLOR;
			case "color":
				return ACTION_DESIGN.NUMBER;
			}
			return null;
		}
	}

}
