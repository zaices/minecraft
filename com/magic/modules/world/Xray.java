package com.magic.modules.world;

import org.lwjgl.input.Keyboard;

import com.magic.main.Objects;
import com.magic.modules.Category;
import com.magic.modules.Module;

public class Xray extends Module{
	
	public static boolean _uwot;
	public static int _opacity = 70;
	
	public Xray() {
		super("Wallhack", Keyboard.KEY_X, 0xffBDBDBD, true, Category.WORLD);
	}
	
	public void onEnable() {
		_uwot = true;
		Objects.mc.renderGlobal.loadRenderers();
	}
	
	public void onDisable() {
		_uwot = false;
		Objects.mc.renderGlobal.loadRenderers();
	}

}
