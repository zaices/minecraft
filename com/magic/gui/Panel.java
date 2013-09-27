package com.magic.gui;

import java.util.ArrayList;

import com.magic.main.Objects;

import net.minecraft.src.Gui;

public class Panel {
	
	private int _posX, _posY, _dragX, _dragY, _width, _height;
	private boolean _dragging;
	private String _name;
	private ArrayList<Element> elements = new ArrayList<Element>();
	
	public Panel(String name /*might want to add in some params to set width and height*/) {
		_name = name;
		addElements();
	}
	
	public void drawPanel(int par1, int par2) {
		Gui.drawRect(_posX, _posY, _posX + _width, _posY + 10, 0xFF00FFFB);
		Gui.drawRect(_posX, _posY + 10, _posX + _width, _posY + _height, 0xFF00FFFB);
		Objects.fr.drawStringWithShadow(_name, _posX + 6, _posY, 0xffffff);
	}
	
	public void mouseClicked(int par1, int par2, int par3) {
		
	}
	
	private boolean isMouseOver(int par1, int par2) {
		return par1 >= _posX && par1 <= _posX + _width && par2 >= _posY && par2 <= _posY + _height;
	}
	
	public void addElements() {}

}
