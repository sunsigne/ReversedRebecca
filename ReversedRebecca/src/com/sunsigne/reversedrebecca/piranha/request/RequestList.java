package com.sunsigne.reversedrebecca.piranha.request;

import java.util.HashMap;

public class RequestList {

	////////// MAP OR LIST ////////////

	private static HashMap<String, Request> map = new HashMap<>();

	public void addRequest(Request request, String type) {
		if (map.containsValue(request))
			return;

		map.put(type, request);
	}

	public Request getRequestFromType(String type) {
		return map.get(type);
	}

}
