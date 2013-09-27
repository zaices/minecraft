package com.magic.modules.world;

import org.lwjgl.input.Keyboard;

import com.magic.main.Objects;
import com.magic.modules.Module;

public class Xray extends Module{
	
	public Xray() {
		super("Wallhack", Keyboard.KEY_X, 0xffffff);
	}
	
	public void onEnable() {
		Objects.mc.renderGlobal.loadRenderers();
	}
	
	public void onDisable() {
		Objects.mc.renderGlobal.loadRenderers();
	}

}
