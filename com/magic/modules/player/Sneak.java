package com.magic.modules.player;

import org.lwjgl.input.Keyboard;
import com.magic.main.Objects;
import com.magic.modules.Category;
import com.magic.modules.Module;

public class Sneak extends Module {
	
	public Sneak() {
		super("Sneak", Keyboard.KEY_Z, 0xffffffff, true, Category.PLAYER);
	}
	
	public void onEnable() {
		Objects.mc.gameSettings.keyBindSneak.pressed = true;
	}
	
	public void onDisable() {
		Objects.mc.gameSettings.keyBindSneak.pressed = false;
	}

}
