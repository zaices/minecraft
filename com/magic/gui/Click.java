package com.magic.gui;

import java.util.ArrayList;
import net.minecraft.src.GuiScreen;

public class Click extends GuiScreen{
	
	private ArrayList<Panel> panels = new ArrayList<Panel>();
	
	public Click() {
		if(panels.isEmpty()) {
			loadPanels();
		}
	}
	
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
	}
	
	protected void mouseMovedOrUp(int par1, int par2, int par3) {
		super.mouseMovedOrUp(par1, par2, par3);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		
		drawDefaultBackground();
		
		for(Panel panel : panels) {
			panel.drawPanel(par1, par2);
		}
		
		super.drawScreen(par1, par2, par3);
	}
	
	public void loadPanels() {
		panels.add(new Panel("Player"));
	}
}
