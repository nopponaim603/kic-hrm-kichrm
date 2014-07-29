package com.kic.hrm.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ProfileUpdateEventHandler extends EventHandler{
	void onProfileUpdated(ProfileUpdateEvent event);
}
