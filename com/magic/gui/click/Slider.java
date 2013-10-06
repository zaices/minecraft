package com.magic.gui.click;

import net.minecraft.src.Gui;

import org.lwjgl.opengl.GL11;

import com.magic.gui.Methods;
import com.magic.main.Objects;

public class Slider {
	
	private String _name;
	private int _posX, _posY, _height, _width, ID;
	private boolean _dragging;
	private float _value, _maxValue;
	
	public Slider(int parID, String name, int posX, int posY, int height, int width, int value) {
		ID = parID;
		_name = name;
		_posX = posX;
		_posY = posY;
		_height = height;
		_width = width;
		_value = value;
	}
	
	public void drawSlider() {
		Gui.drawRect(_posX - 2, _posY + 1, _posX + _width + 2, _posY + 5, 0xaa636462);
		Gui.drawRect(_posX - 2, _posY + 1, (int)(_posX + 2 + ((_value/_maxValue) * _width)), _posY + 5, 0xff00c1ff);
		GL11.glScaled(0.5, 0.5, 0.5);
		Methods.drawCircle((int)(_posX + ((_value/_maxValue) * _width))*2, (_posY + 3)*2, 5, 0xff323232);
		GL11.glScaled(2, 2, 2);
		Objects.fr.drawString(_name, _posX, _posY - 7, 0xffffffff);
		Objects.fr.drawString("" + getValue(), _posX + _width - Objects.fr.getStringWidth("" + getValue())/2, _posY - 7, 0xffffff);
	}
	
	public void mouseClicked(int i, int j, int k) {
		if(k == 0 && i >= this._posX + 3 && i <= this._posX + 3 + this._width && j >= this._posY + 10 && j <= this._posY + this._height) {
			_dragging = true;
		}
	}
	
	private void drag(int i, int j) {
		if(_dragging) {
			_value = (float)(i - (this._posX + 3)) / (float)_width;
			if(_value < 0.0F) {
				_value = 0.0F;
			}
			if(_value > 1.0F) {
				_value = 1.0F;
			}
		}
	}
	
	   private void setValue(float value) {
		      /*if(value < this.minValue) {
		         value = this.minValue;
		      }*/

		      if(value > _maxValue) {
		         value = _maxValue;
		      }

		      _value = value / _maxValue;
		   }

		   public float getValue() {
		      return _value;
		   }

}
