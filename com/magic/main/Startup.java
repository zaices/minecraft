package com.magic.main;

import com.magic.utilities.FileManager;

public class Startup {
	
	public Startup() {
		FileManager.createMain();
		FileManager.loadFriends();
		System.out.println("Friends Loaded.");
		FileManager.checkUpdate();
		System.out.println("Status checked.");
	}
}
