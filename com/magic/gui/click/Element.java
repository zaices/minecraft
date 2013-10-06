package com.magic.gui.click;

import net.minecraft.src.Gui;
import net.minecraft.src.Tessellator;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.magic.gui.Methods;
import com.magic.main.Objects;
import com.magic.modules.Module;

public class Element {
	
	public int _posX, _posY, _width, _height;
	public Module _mod;
	
	public Element(Module mod){
		_mod = mod;
		_height = 15;
		_width = 20;
	}
	
	public void mouseClicked(int par1, int par2, int par3){
		if(isMouseOverElement(par1, par2)){
			_mod.toggle();
		}
	}
	
	public boolean isMouseOverElement(int par1, int par2){
		return par1 >= _posX && par2 >= _posY + 2  && par1 <= _posX + 31 && par2 <= _posY + 15;
	}
	
	public void drawElement(int par1, int par2, int par3, int par4){
		_posX = par1;
		_posY = par2;
		
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScaled(0.5, 0.5, 0.5);
		int colorActive;
		colorActive = (_mod.isEnabled() ? 60:30);
		int colorMouseOver;
		colorMouseOver = (Mouse.isButtonDown(0) && isMouseOverElement(par3, par4) ? 120:isMouseOverElement(par3, par4) ? 60:0);
		//GL11.glBindTexture(GL11.GL_TEXTURE_2D, Reaction.getClient().getWrapper().getMinecraft().renderEngine.getTexture("/textures/reaction/clicktextures.png"));
		//drawTexturedModalRect(_posX * 2 + 7, _posY * 2 + 4, colorMouseOver, colorActive, 57, 28);
		Methods.drawBorderedRect(_posX * 2 + 60, _posY * 2 + 13, _posX *2 + 10, _posY * 2 + 33, 1, 0xff666666, 0xAF1c1c1b);
		GL11.glScaled(2, 2, 2);
		Objects.customFont.drawStringS(new Gui(), _mod.getName(), _posX * 2 + 70, _posY * 2 + 8, (_mod.isEnabled() ? 0xFFFFFFFF:isMouseOverElement(par3, par4) ? 0x80FFFFFF:0x50FFFFFF));
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	protected float zLevel = 0.0F;
    
    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV((double) (par1 + 0), (double) (par2 + par6), (double) this.zLevel, (double) ((float) (par3 + 0) * var7), (double) ((float) (par4 + par6) * var8));
        var9.addVertexWithUV((double) (par1 + par5), (double) (par2 + par6), (double) this.zLevel, (double) ((float) (par3 + par5) * var7), (double) ((float) (par4 + par6) * var8));
        var9.addVertexWithUV((double) (par1 + par5), (double) (par2 + 0), (double) this.zLevel, (double) ((float) (par3 + par5) * var7), (double) ((float) (par4 + 0) * var8));
        var9.addVertexWithUV((double) (par1 + 0), (double) (par2 + 0), (double) this.zLevel, (double) ((float) (par3 + 0) * var7), (double) ((float) (par4 + 0) * var8));
        var9.draw();
    }

}
