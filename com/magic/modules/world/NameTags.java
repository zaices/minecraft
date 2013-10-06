package com.magic.modules.world;

import net.minecraft.src.EntityPlayer;

import org.lwjgl.input.Keyboard;

import com.magic.main.Objects;
import com.magic.modules.Category;
import com.magic.modules.Module;

public class NameTags extends Module {
	
	public static boolean _uwot;
	public static int _distance;
	
	public NameTags() {
		super("Nametags", Keyboard.KEY_NONE, 0xffffffff, false, Category.WORLD);
	}
	
	public void onEnable() {
		for (int j = 0; j < Objects.mc.theWorld.playerEntities.size(); j++) {
	          EntityPlayer e = (EntityPlayer) Objects.mc.theWorld.playerEntities.get(j);
	          _distance = (int) Objects.mc.thePlayer.getDistanceToEntity(e);
	    }
		
		_uwot = true;
	}
	
	public void onDisable() {
		_uwot = false;
	}

}
