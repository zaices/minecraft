package com.magic.modules.combat;

import java.util.ArrayList;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.MathHelper;
import net.minecraft.src.StringUtils;

import org.lwjgl.input.Keyboard;

import com.magic.main.Objects;
import com.magic.modules.Category;
import com.magic.modules.Module;
import com.magic.utilities.TimeManager;

/**
 * @author GlobalHF
 * @since Sept 21, 2013
 */

public class KillAura extends Module{
	
	public KillAura() {
		super("Kill Aura", Keyboard.KEY_R, 0xffff0000, true, Category.COMBAT);
	}
	
    private EntityLivingBase targetEntity;
    
    public ArrayList <String> friends = new ArrayList<String>();
   
    private final TimeManager timer = new TimeManager();
   
    public float range = 3.85f;
   
    public float rotationYaw;
    public float rotationPitch;
    private boolean lockview;
   
    public float aps = 6.1f;
   
    @Override
    public void onDisable() {
            targetEntity = null;
    }
   
    @Override
    public final void onPreMotionUpdates() {
    this.rotationYaw = Objects.mc.thePlayer.rotationYaw;
            this.rotationPitch = Objects.mc.thePlayer.rotationPitch;
            targetEntity = getEntity();
            if(targetEntity == null) return;
            faceEntity(targetEntity);
    }
   
    @Override
    public final void onPostMotionUpdates() {
            if(targetEntity == null) return;
            if(timer.sleep(timer.convertToMillis(aps))){
            attackEntity(targetEntity);
            timer.resetTime();
            }
            if(!lockview) {
            	Objects.mc.thePlayer.rotationYaw = this.rotationYaw;
            	Objects.mc.thePlayer.rotationPitch = this.rotationPitch;
            }
    }
   
    public boolean canAttackEntity(EntityLivingBase entity) {
            return entity != null && entity.isEntityAlive() && Objects.mc.thePlayer.isEntityAlive() && Objects.mc.thePlayer.canEntityBeSeen(entity) && Objects.mc.thePlayer.getDistanceToEntity(entity) <= range && entity != Objects.mc.thePlayer && entity instanceof EntityLivingBase;
    }
   
    public final void attackEntity(EntityLivingBase entity) {
            Objects.mc.thePlayer.swingItem();
            Objects.mc.thePlayer.setSprinting(false);
            Objects.mc.playerController.attackEntity(Objects.mc.thePlayer, entity);
            Objects.mc.thePlayer.setSprinting(false);
    }
   
    private EntityLivingBase getEntity() {
    	for(final Object o : Objects.mc.theWorld.loadedEntityList) {
    		if (!(o instanceof EntityLivingBase)) continue;
      	  	final EntityLivingBase entity = (EntityLivingBase) o;
      	 	if (entity == Objects.mc.thePlayer) continue;
      	 	if (hasFriend(entity)) continue;
      	 	if (!Objects.mc.thePlayer.canEntityBeSeen(entity)) continue;
      	 	if (canAttackEntity(entity)) {
                if(Objects.crits.isEnabled()) {
                    Objects.crits.runCrits();
                } return entity;
      	 	}
    	} return null;
    }

	public synchronized void faceEntity(Entity entity)
	{
		double var4 = entity.posX - Objects.mc.thePlayer.posX;
		double var8 = entity.posZ - Objects.mc.thePlayer.posZ;
		double var6 = entity.posY - Objects.mc.thePlayer.posY + entity.height / 1.5;
		double var14 = (double)MathHelper.sqrt_double(var4 * var4 + var8 * var8);
    	float var12 = (float)(Math.atan2(var8, var4) * 180.0D / Math.PI) - 90.0F;
    	float var13 = (float)(-(Math.atan2(var6, var14) * 180.0D / Math.PI));
    	Objects.mc.thePlayer.rotationPitch =  Objects.mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(var13 - Objects.mc.thePlayer.rotationPitch);
    	Objects.mc.thePlayer.rotationYaw =  Objects.mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(var12 - Objects.mc.thePlayer.rotationYaw);
	}

	public boolean hasFriend(Entity entity) {
		return Objects.fManager.getFriends().contains(StringUtils.stripControlCodes(entity.getEntityName()));
	}

}
