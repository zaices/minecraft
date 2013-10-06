package com.magic.gui.click;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.GuiScreen;
import com.magic.main.Objects;
import com.magic.modules.Module;

/**
 * creds to Aarow.
 *
 */

public class Click extends GuiScreen{
	
	public static List<Panel> panels = new ArrayList<Panel>();
	
	public Click() {
		if(panels.isEmpty()) {
			loadPanels();
		}
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		
		for(Panel panel : panels) {
			panel.drawPanel(par1, par2, par3);
		}
		
		super.drawScreen(par1, par2, par3);
	}
	
	protected void mouseMovedOrUp(int par1, int par2, int par3) {
		if(par3 == 0) {
			for(Panel panel : panels) {
				panel._dragging = false;
			}
		}
		super.mouseMovedOrUp(par1, par2, par3);
	}
	
	@Override
	public void mouseClicked(int par1, int par2, int par3) {
		for(int temp = 0; temp < panels.size(); temp++) {
			Panel panel = panels.get(temp);
			if(panel.isMouseOverBoth(par1, par2)) {
				if(temp == panels.size() - 1) {
					panel.mouseClicked(par1, par2, par3);
				}
				
				Panel panel1 = panel;
				panels.remove(panel);
				panels.add(panel1);
			}
		}
		super.mouseClicked(par1, par2, par3);
	}
	
	public void loadPanels() {
		//(15 * n) + 20
		panels.clear();
		panels.add(new Panel("Player", 2, 2, 100, 95));
		panels.add(new Panel("World", 103, 2, 100, 95));
		panels.add(new Panel("Combat", 204, 2, 100, 50));
	}

}
