package com.magic.modules.world;

import org.lwjgl.input.Keyboard;
import com.magic.modules.Category;
import com.magic.modules.Module;

public class DepthMask extends Module{
	
	/**
	 * Init RendererLivingEntity.java
	 */
	
	public static boolean _uwot;
	
	public DepthMask() {
		super("Depth Mask", Keyboard.KEY_NONE, 0xffffffff, false, Category.WORLD);
	}
	
	public void onEnable() {
		_uwot = true;
		
		/*
		 * Just because I'm too lazy to use DepthMask.isEnabled();
		 */
	}
	
	public void onDisable() {
		_uwot = false;
	}
}
