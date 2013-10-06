package com.magic.modules.world;

import org.lwjgl.input.*;
import com.magic.main.Objects;
import com.magic.modules.*;

public class Brightness extends Module{

	public Brightness() {
		super("Brightness", Keyboard.KEY_C, 0xFFF5DA81, true, Category.WORLD);
	}
	
	public void onEnable() {
		Objects.mc.gameSettings.gammaSetting = 1000F;
	}
	
	public void onDisable() {
		Objects.mc.gameSettings.gammaSetting = 0.0F;
	}
}