package com.magic.utilities;

import net.minecraft.src.ScaledResolution;

import com.magic.main.Objects;
import com.magic.modules.Module;

public class ArraylistManager {
	
	public void renderArray() {
		
		ScaledResolution sr = new ScaledResolution(Objects.mc.gameSettings, Objects.mc.displayWidth, Objects.mc.displayHeight);
		
		int posY = 2;
		int posX = sr.getScaledWidth();
		
		for(Module mod : Objects.mManager.getModules()) {			
			if(mod.isEnabled()) {
				Objects.fr.drawStringWithShadow(mod.getName(), posX - Objects.fr.getStringWidth(mod.getName()) - 3, posY, mod.getColor());
				posY += 10;
			}
		}
	}
}