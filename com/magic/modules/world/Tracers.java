package com.magic.modules.world;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.RenderManager;
import net.minecraft.src.StringUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import com.magic.main.Objects;
import com.magic.modules.Category;
import com.magic.modules.Module;
import com.magic.modules.chat.Commands;

/**
 * @author GlobalHF
 * @since Sept 27 ,2013
 */

public class Tracers extends Module{
	
	public static boolean _uwot;
	
	public Tracers() {
		super("Tracers", Keyboard.KEY_P, 0xffFF00FF, true, Category.WORLD);
	}
	
	public void onEnable() {
		_uwot = true;
	}
	
	public void onDisable() {
		_uwot = false;
	}
	
	public static void drawTracerLine(float par3)
	{
		 GL11.glPushMatrix();
         GL11.glDisable(GL11.GL_LIGHTING);
         GL11.glDisable(GL11.GL_DEPTH_TEST );
         
      for (int j = 0; j < Objects.mc.theWorld.playerEntities.size(); j++) 
      {
          EntityPlayer e = (EntityPlayer) Objects.mc.theWorld.playerEntities.get(j);
          
          if(e != Objects.mc.thePlayer && e != null && e.isEntityAlive())
          {
              double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)par3 - RenderManager.instance.renderPosX;
              double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)par3 - RenderManager.instance.renderPosY;
              double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)par3 - RenderManager.instance.renderPosZ;
              double distance =  Objects.mc.thePlayer.getDistanceToEntity(e);
              float distance2 = (float)((distance / 100F));
              
              GL11.glBlendFunc(770, 771);
              GL11.glEnable(GL11.GL_BLEND);
              GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
              if(Objects.fManager.getFriends().contains(StringUtils.stripControlCodes(((EntityPlayer)e).getEntityName())/*.toLowerCase()*/)) {
                      GL11.glColor3f(0f, 4f, 11f);
              }else if(Objects.fManager.getNotes().contains(StringUtils.stripControlCodes(((EntityPlayer)e).getEntityName())/*.toLowerCase()*/)) {
            	  	  GL11.glColor3f(153F, 0F, 153F);
              }else if(e.isSneaking()) {
                      GL11.glColor3f(255F, 255F, 0f);
              }else if(distance <= 50) {
                  GL11.glColor4f(153F, (float)distance / 45, 0F, 153F);
              }else {
                      GL11.glColor3f(0F, 2F, 0F);
              }
              GL11.glEnable(GL11.GL_LINE_SMOOTH);
              GL11.glLineWidth(1F);
              GL11.glDisable(GL11.GL_TEXTURE_2D);
              GL11.glBegin(GL11.GL_LINES);
              GL11.glVertex2d(0.0D, 0.0D);
              GL11.glVertex3d(x, y, z);
              GL11.glEnd();
              GL11.glDisable(GL11.GL_BLEND);
              GL11.glEnable(GL11.GL_TEXTURE_2D);
              GL11.glDisable(GL11.GL_LINE_SMOOTH);
              GL11.glEnable(GL11.GL_LIGHTING);
           }
      }
      GL11.glEnable(GL11.GL_DEPTH_TEST );
      GL11.glPopMatrix();
    } /**called in EntityRenderer.java**/
	
	public static void drawBoundingBox()
	{
        		GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LINE_SMOOTH);
                //GL11.glLineWidth(1f);
                GL11.glDepthMask(false);
                       
                        for(Object o: Objects.mc.theWorld.playerEntities)
                        {
                                EntityPlayer e = (EntityPlayer)o;
                               
                                double x = e.posX - RenderManager.renderPosX;
                                double z = e.posZ - RenderManager.renderPosZ;
                                double y = e.posY - RenderManager.renderPosY;
                                double height = y + e.height + 0.2;
                                double correctMeasureX = x + 0.4;
                                double correctMeasureZ = z + 0.4;
                                double distance =  Objects.mc.thePlayer.getDistanceToEntity(e);
                                
                                if(e != Objects.mc.thePlayer && e.isEntityAlive()){
                            	if(Objects.fManager.getFriends().contains(StringUtils.stripControlCodes(((EntityPlayer)e).getEntityName())/*.toLowerCase()*/))
                            	{
                            		GL11.glColor3f(0F, 4F, 11F);
                            	}else
                            	{
                            		if(e.hurtTime > 0) 
                            		{
                            			GL11.glColor3f(1F, 0F, 0F);
                            		}else if(Objects.fManager.getNotes().contains(StringUtils.stripControlCodes(((EntityPlayer)e).getEntityName())/*.toLowerCase()*/))
                            		{
                            			GL11.glColor3f(153F, 0F, 153F);
                            		}
                            		else if(e.isSneaking())
                            		{
                            			GL11.glColor3f(255f, 255f, 0f);
                            		}else
                            		{
                            			GL11.glColor3f(0f, 0.75f, 0f);
                            		}
                            	}
                                drawOutlinedBoundingBox(new AxisAlignedBB(correctMeasureX - 0.8, y, correctMeasureZ - 0.8, correctMeasureX, height, correctMeasureZ));
                        }
                        }
                GL11.glPopMatrix();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_LINE_SMOOTH);
                GL11.glDepthMask(true);
        }
         
             public static void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB)
             {
                GL11.glLineWidth(0.75F);
                GL11.glBegin(GL11.GL_LINES);
                GL11.glVertex3d(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
                GL11.glVertex3d(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINES);
                GL11.glVertex3d(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
                GL11.glVertex3d(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINES);
                GL11.glVertex3d(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
                GL11.glVertex3d(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINES);
                GL11.glVertex3d(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
                GL11.glVertex3d(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINES);
                GL11.glVertex3d(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
                GL11.glVertex3d(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINES);
                GL11.glVertex3d(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
                GL11.glVertex3d(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
                GL11.glEnd();
                Objects.mc.renderGlobal.drawOutlinedBoundingBox(par1AxisAlignedBB);
            }


}
