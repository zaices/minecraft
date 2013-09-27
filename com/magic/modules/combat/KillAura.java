package com.magic.modules.combat;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;

import org.lwjgl.input.Keyboard;

import com.magic.hooks.MEntityClientPlayerMP;
import com.magic.main.Objects;
import com.magic.modules.Module;
import com.magic.utilities.TimeManager;

/**
 * @author GlobalHF
 * @since Sept 21, 2013
 */

public class KillAura extends Module{
	
	public KillAura() {
		super("Kill Aura", Keyboard.KEY_R, 0xff0000);
	}
	
	private float reach = 4f;
	private float aps = 5f;
	private TimeManager timer = new TimeManager();
	
	public void onPreMotionUpdates() {
		for(Object o: Objects.mc.theWorld.loadedEntityList) {
                Entity e = (Entity)o;
                if(canHarmEntity(e,reach + 0.5)) {
                        if(e instanceof EntityPlayer) /*&& ChatCommands.friends.contains(StringUtils.stripControlCodes(((EntityPlayer)e).getEntityName().toLowerCase())) && e instanceof EntityPlayer)*/ continue;
                        faceEntity(e);
                        break;
                }
        }
	}
	
	public void onPostMotionUpdates() {
        for(Object o: Objects.mc.theWorld.loadedEntityList) {
                Entity e = (Entity)o;
                if(canHarmEntity(e,reach)) {
                        if(e instanceof EntityPlayer) /*&& ChatCommands.friends.contains(StringUtils.stripControlCodes(((EntityPlayer)e).getEntityName())))*/ continue;
                        if(timer.sleep(timer.convertToMillis(aps))) {
                        	attackEntity(e);
                        	timer.resetTime();
                        	break;
                        }
                }
        }
	}
	
    public void attackEntity(Entity e) {
    	Objects.mc.thePlayer.swingItem();
        Objects.mc.thePlayer.setSprinting(false);
        Objects.mc.playerController.attackEntity(Objects.mc.thePlayer, e);
    }
       
    public boolean canHarmEntity(Entity e, double distance) {
    	return e != null && e.isEntityAlive() && Objects.mc.thePlayer.getDistanceToEntity(e) <= distance && Objects.mc.thePlayer.canEntityBeSeen(e) && e instanceof EntityLivingBase && e != Objects.mc.thePlayer && !Objects.mc.thePlayer.isEating() && Objects.mc.thePlayer.isEntityAlive();
    }
       
    public void faceEntity(Entity e) {
        double x = e.posX - Objects.mc.thePlayer.posX;
        double z = e.posZ - Objects.mc.thePlayer.posZ;
        double y = e.posY - Objects.mc.thePlayer.posY + (double)(e.height) / 1.5;
        double var14 = (double)MathHelper.sqrt_double(x * x + z * z);
        float var12 = (float)(Math.atan2(z, x) * 180.0D / Math.PI) - 90.0F;
        float var13 = (float)(-(Math.atan2(y, var14) * 180.0D / Math.PI));
        Objects.mc.thePlayer.rotationPitch = (float) (Objects.mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_double(var13 - Objects.mc.thePlayer.rotationPitch));
        Objects.mc.thePlayer.rotationYaw = (float) (Objects.mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_double(var12 - Objects.mc.thePlayer.rotationYaw));
    }

}
