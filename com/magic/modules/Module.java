package com.magic.modules;

public class Module {
	
	private String _name;
	private int _keybind;
	private int _color;
	private Category _category;
	private boolean _state, _visibility;
	
	public Module(String name, int keybind, int color, boolean visibility, Category category) {
		_name = name;
		_keybind = keybind;
		_color = color;
		_category = category;
		_visibility = visibility;
	}
	
	public String getName() {
		return _name;
	}
	
	public int getKey() {
		return _keybind;
	}
	
	public int getColor() {
		return _color;
	}
	
	public Category getCategory() {
		return _category;
	}
	
	public boolean isVisible() {
		return _visibility;
	}
	
	public boolean isEnabled() {
		return _state;
	}
	
	public void toggle() {
		_state = !_state;
		
		if(isEnabled()) {
			onEnable();
		}else {
			onDisable();
		}
	}
	
	public void onEnable() {
		_state = true;
	}
	
	public void onDisable() {
		_state = false;
	}
	
	public void onPreMotionUpdates() {}
	public void onPostMotionUpdates() {}

}
