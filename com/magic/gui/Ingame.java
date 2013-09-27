package com.magic.gui;

import com.magic.main.Objects;

import net.minecraft.src.*;

public class Ingame extends GuiIngame{

	public Ingame(Minecraft par1Minecraft) {
		super(par1Minecraft);
	}
	
	@Override
	public void renderGameOverlay(float par1, boolean par2, int par3, int par4) {
		super.renderGameOverlay(par1, par2, par3, par4);
		
		Objects.fr.drawStringWithShadow("Magic Client", 2, 2, 0xffffff);
		
		Objects.aManager.renderArray();
	}
}
