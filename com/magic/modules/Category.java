package com.magic.modules;

public enum Category {
	
	PLAYER("Player"),
	COMBAT("Combat"),
	WORLD("World");
	
	private String _name;

	private Category(String par1Str) {
		_name = par1Str;
	}
	
	public String getCatString() {
		return _name;
	}
}
