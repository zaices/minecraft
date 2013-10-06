package com.magic.gui.click;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.Gui;

import com.magic.gui.Methods;
import com.magic.main.Objects;
import com.magic.modules.Category;
import com.magic.modules.Module;

public class Panel {
	
	public String _name;
	public int _posX, _posY, _height, _width, _pheight, _pwidth, _dragX, _dragY;
	public boolean _open, _dragging;
	public List<Element> elements = new ArrayList<Element>();

	public Panel(String par1Str, int par2, int par3, int par4, int par5) {
		_name = par1Str;
		_posX = par2;
		_posY = par3;
		_pwidth = par4;
		_pheight = par5;
	}

	public void drawPanel(int par1, int par2, float par3) {
		if(_dragging) {
			_posX = par1 + _dragX;
			_posY = par2 + _dragY;
		}
		
		addElements();
		Methods.drawRect(_posX, _posY, _posX + _pwidth, _posY + 14, 0xFF666666);
		Methods.drawVerticalLine(_posX, _posY, 2, 2);
		Methods.drawBorderedRect(_posX, _posY /*+14*/, _posX + _pwidth, _posY + _pheight, 1, 0xff000000, 0xAF1c1c1b);
		Objects.customFont.drawStringS(new Gui(), _name, _posX * 2 + 5, _posY * 2, 0xffffffff);
		
		int pos = 0;
		for(int i = 0; i < elements.size(); i++){
			Element element = elements.get(i);
			element.drawElement(_posX, _posY + pos + 13, par1, par2);
			pos += element._height;
		}

			_width = pos + 16;
			_height = 114;
	}

	public void mouseClicked(int par1, int par2, int par3) {
		if(isMouseOverTitle(par1, par2) && par3 == 0) {
			_dragging = true;
			_dragX = _posX - par1;
			_dragY = _posY - par2;
		}

		for(Element elements : this.elements){
			elements.mouseClicked(par1, par2, par3);
		}
	}
	
	public boolean isMouseOverTitle(int par1, int par2) {
		return par1 >= _posX && par2 >= _posY && par1 <= _posX + _height - 12 && par2 <= _posY + 11;
	}

	public boolean isMouseOverPanel(int par1, int par2) {
		return par1 >= _posX && par2 >= _posY && par1 <= _posX + _height && par2 <= _posY + _width;
	}

	public boolean isMouseOverBoth(int par1, int par2) {
		if(isMouseOverPanel(par1, par2)) {
			for(Panel panel : Click.panels) {
				if(panel.isMouseOverPanel(par1, par2) && Click.panels.indexOf(panel) > Click.panels.indexOf(this)) {
					return true;
				}
			} return true;
		}else {
			return false;
		}
	}
	
	public void addElements() {
		elements.clear();
		for(Module mod : Objects.mManager.getModules()) {
			if(_name == mod.getCategory().getCatString()) {
				if(!elements.contains(mod)) {
					elements.add(new Element(mod));
				}
			}
		}
	}

}
