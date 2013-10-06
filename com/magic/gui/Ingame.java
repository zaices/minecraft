package com.magic.gui;
import java.awt.Font;

import net.minecraft.src.Gui;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ScaledResolution;

import com.magic.main.Objects;
import com.magic.utilities.CustomFont;
import com.magic.utilities.FileManager;

public class Ingame extends GuiIngame{
	
	ScaledResolution sr = new ScaledResolution(Objects.mc.gameSettings, Objects.mc.displayWidth, Objects.mc.displayHeight);
    int width = sr.getScaledWidth();
    int height = sr.getScaledHeight();
	
	public Ingame(Minecraft par1Minecraft) {
		super(par1Minecraft);
	}
	
	@Override
	public void renderGameOverlay(float par1, boolean par2, int par3, int par4) {
		super.renderGameOverlay(par1, par2, par3, par4);
		
		Objects.customFont.drawStringS(new Gui(), "Magic Client", 4, -2, 0xff7F38EC);
		
		if(!FileManager.isUpToDate) {
			Objects.customFont.drawStringS(new Gui(), "Outdated Version.", 4, 15, 0xffffffff);
		}
		
		Objects.aManager.renderArray();
		
		//MainMenu.renderMagicTitle(height, width);
	}
}
