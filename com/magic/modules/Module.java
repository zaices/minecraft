package com.magic.modules;

public class Module {
	
	private String _name;
	private int _keybind;
	private int _color;
	private boolean _state;
	
	public Module(String name, int keybind, int color) {
		_name = name;
		_keybind = keybind;
		_color = color;
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
