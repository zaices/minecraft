package com.magic.modules.player;

import org.lwjgl.input.Keyboard;

import com.magic.main.Objects;
import com.magic.modules.Module;

public class Step extends Module{
	
	public Step() {
		super("Step", Keyboard.KEY_Y, 0xffffff);
	}
	
	public void onPreMotionUpdates() {
		if(canStep() && !Objects.mc.thePlayer.isOnLadder()) {
			Objects.mc.thePlayer.boundingBox.offset(0, 0.5, 0);
			Objects.mc.thePlayer.motionY -= 10000;
			Objects.mc.thePlayer.isCollidedHorizontally = false;
		}
	}
	
	public boolean canStep() {			 
		return Objects.mc.thePlayer.onGround && Objects.mc.thePlayer.isCollidedHorizontally;	  
	}
}
