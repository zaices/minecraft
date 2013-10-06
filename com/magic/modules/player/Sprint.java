package com.magic.modules.player;

import net.minecraft.src.Material;

import org.lwjgl.input.Keyboard;

import com.magic.main.Objects;
import com.magic.modules.Category;
import com.magic.modules.Module;

/**
 * @author GlobalHF
 * @since Sept 21, 2013
 */

public class Sprint extends Module{
	
	public Sprint() {
		super("Speed (Sprint)", Keyboard.KEY_V, 0xFFFFBF00, true, Category.PLAYER);
	}
	
	public void onPreMotionUpdates() {
        if(canSprintCheck()) {
            Objects.mc.thePlayer.setSprinting(true);
            if(Objects.mc.gameSettings.keyBindBack.pressed) {
            	Objects.mc.thePlayer.rotationYaw = Objects.mc.thePlayer.rotationYaw - 180;
            	if(Objects.mc.thePlayer.onGround) {
            		Objects.mc.thePlayer.motionX = Objects.mc.thePlayer.motionX *= 1.2;
            		Objects.mc.thePlayer.motionZ = Objects.mc.thePlayer.motionZ *= 1.2;
            	}
            }
                
            if(Objects.mc.gameSettings.keyBindForward.pressed && Objects.mc.thePlayer.onGround) {
            	Objects.mc.thePlayer.motionX = Objects.mc.thePlayer.motionX *= 1.015;
            	Objects.mc.thePlayer.motionZ = Objects.mc.thePlayer.motionZ *= 1.015;       
            }
        }
	}
 

	public boolean canSprintCheck() {
		return !Objects.mc.thePlayer.isInsideOfMaterial(Material.water)
				&& !Objects.mc.thePlayer.isInsideOfMaterial(Material.lava) && !Objects.mc.thePlayer.isCollidedHorizontally
				&& Objects.mc.gameSettings.keyBindForward.pressed || Objects.mc.gameSettings.keyBindBack.pressed
				|| Objects.mc.gameSettings.keyBindRight.pressed  || Objects.mc.gameSettings.keyBindLeft.pressed
				&& Objects.mc.thePlayer.swingProgress <= 0
				&& !Objects.mc.thePlayer.isSneaking();

	}
}
