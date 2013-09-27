package com.magic.hooks;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Session;
import net.minecraft.src.World;

import com.magic.main.Objects;
import com.magic.modules.chat.Commands;

public class MEntityClientPlayerMP extends EntityClientPlayerMP{
	
	public MEntityClientPlayerMP(Minecraft par1Minecraft, World par2World, Session par3Session, NetClientHandler par4NetClientHandler) {
		super(par1Minecraft, par2World, par3Session, par4NetClientHandler);
	}
	
	@Override
	public void sendMotionUpdates() {
		
		float[] angles = {Objects.mc.thePlayer.rotationYaw, Objects.mc.thePlayer.rotationYawHead, Objects.mc.thePlayer.rotationPitch};
		
		Objects.moManager.runPreMotions();
		
		Objects.mc.thePlayer.rotationYaw = angles[0];
		Objects.mc.thePlayer.rotationYawHead = angles[1];
		Objects.mc.thePlayer.rotationPitch = angles[2];
		
		super.sendMotionUpdates();
		
		Objects.moManager.runPostMotions();
	}
	
	@Override
	public void sendChatMessage(String par1Str) {
		if(par1Str.startsWith(".")) {
			Commands.sendCommands(par1Str);
			return;
		}
		super.sendChatMessage(par1Str);
	}
}