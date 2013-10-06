package com.magic.utilities;

import net.minecraft.src.Gui;
import net.minecraft.src.ScaledResolution;

import com.magic.main.Objects;
import com.magic.modules.Module;

public class ArraylistManager {
	
	public void renderArray() {
		
		ScaledResolution sr = new ScaledResolution(Objects.mc.gameSettings, Objects.mc.displayWidth, Objects.mc.displayHeight);
		
		int _posY = 2;
		int _posX = sr.getScaledWidth();
		
		for(Module mod : Objects.mManager.getModules()) {			
			if(mod.isEnabled() && mod.isVisible()) {
				Objects.customFont.drawStringS(new Gui(), mod.getName(), _posX * 2 - Objects.customFont.getStringWidth(mod.getName()) - 5, _posY - 5, mod.getColor());
				_posY += 18;
			}
		}
	}
}