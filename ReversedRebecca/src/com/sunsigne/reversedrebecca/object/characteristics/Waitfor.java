package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;

public interface Waitfor {

	////////// WAITFOR ////////////
	
	ConditionalListener getWaitfor();
	
	void setWaitfor(ConditionalListener listener);
	
}
