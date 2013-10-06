package com.magic.modules.player;

import org.lwjgl.input.Keyboard;

import com.magic.main.Objects;
import com.magic.modules.Category;
import com.magic.modules.Module;

public class Freecam extends Module{
	
	public Freecam() {
		super("Freecam", Keyboard.KEY_NONE, 0xffffffff, true, Category.PLAYER);
	}
	
	public double _posX, _posY, _posZ;
	
	public void onPreMotionUpdates() {
		 Objects.mc.thePlayer.capabilities.isFlying = true;
	}
	 
	 public void onEnable() {
		 Objects.mc.thePlayer.noClip = true;
		 _posX = Objects.mc.thePlayer.posX;
		 _posY = Objects.mc.thePlayer.posY;
		 _posZ = Objects.mc.thePlayer.posZ;
	 }

	 public void onDisable() {
		 Objects.mc.thePlayer.noClip = false;
		 Objects.mc.thePlayer.capabilities.isFlying = false;
		 Objects.mc.thePlayer.setPosition(_posX, _posY, _posZ);
	 }

}
