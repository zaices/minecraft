package com.magic.utilities;

import java.util.ArrayList;

public class FriendsManager {
	
	private ArrayList<String> friends = new ArrayList<String>();
	private ArrayList<String> enemies = new ArrayList<String>();

	public ArrayList<String> getFriends() {
		return friends;
	}
	
	public ArrayList<String> getNotes() {
		return enemies;
	}
}
