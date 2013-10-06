package com.magic.modules.combat;

import org.lwjgl.input.Keyboard;
import com.magic.modules.Category;
import com.magic.modules.Module;

public class AntiVelocity extends Module  {
	
	public AntiVelocity() {
		super("Velocity", Keyboard.KEY_NONE, 0xFFFF8000, true, Category.COMBAT);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}

}
