package com.magic.modules.combat;

import org.lwjgl.input.Keyboard;

import com.magic.main.Objects;
import com.magic.modules.Category;
import com.magic.modules.Module;

/**
 * @author GlobalHF
 * @since Sept 28, 2013
 */

public final class Criticals extends Module {

	 public Criticals() {
		 super("Criticals", Keyboard.KEY_L, 0xFFAD7D7D, true, Category.COMBAT);
	 }
	 
	 public final void runCrits() {
		 if(canPlayerCriticalyHit()) {
			 Objects.mc.thePlayer.onGround = false;
			 Objects.mc.thePlayer.motionY += 0.245;
			 Objects.mc.thePlayer.fallDistance = 1.45f;
		 }
	 }
		 
	 private boolean canPlayerCriticalyHit() {
		 return Objects.mc.thePlayer.onGround && !Objects.mc.gameSettings.keyBindJump.pressed && Objects.mc.thePlayer.swingProgress > 0 &&!Objects.mc.thePlayer.handleWaterMovement() && !Objects.mc.thePlayer.handleLavaMovement();
	 }
}
