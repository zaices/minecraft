package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class EntityLeashKnot extends EntityHanging
{
    public EntityLeashKnot(World par1World)
    {
        super(par1World);
    }

    public EntityLeashKnot(World par1World, int par2, int par3, int par4)
    {
        super(par1World, par2, par3, par4, 0);
        this.setPosition((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D);
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    public void setDirection(int par1) {}

    public int func_82329_d()
    {
        return 9;
    }

    public int func_82330_g()
    {
        return 9;
    }

    /**
     * Checks if the entity is in range to render by using the past in distance and comparing it to its average edge
     * length * 64 * renderDistanceWeight Args: distance
     */
    public boolean isInRangeToRenderDist(double par1)
    {
        return par1 < 1024.0D;
    }

    public void func_110128_b(Entity par1Entity) {}

    /**
     * adds the ID of this entity to the NBT given
     */
    public boolean addEntityID(NBTTagCompound par1NBTTagCompound)
    {
        return false;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}

    public boolean func_130002_c(EntityPlayer par1EntityPlayer)
    {
        ItemStack var2 = par1EntityPlayer.getHeldItem();
        boolean var3 = false;
        double var4;
        List var6;
        Iterator var7;
        EntityLiving var8;

        if (var2 != null && var2.itemID == Item.field_111214_ch.itemID && !this.worldObj.isRemote)
        {
            var4 = 7.0D;
            var6 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getAABBPool().getAABB(this.posX - var4, this.posY - var4, this.posZ - var4, this.posX + var4, this.posY + var4, this.posZ + var4));

            if (var6 != null)
            {
                var7 = var6.iterator();

                while (var7.hasNext())
                {
                    var8 = (EntityLiving)var7.next();

                    if (var8.func_110167_bD() && var8.func_110166_bE() == par1EntityPlayer)
                    {
                        var8.func_110162_b(this, true);
                        var3 = true;
                    }
                }
            }
        }

        if (!this.worldObj.isRemote && !var3)
        {
            this.setDead();

            if (par1EntityPlayer.capabilities.isCreativeMode)
            {
                var4 = 7.0D;
                var6 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getAABBPool().getAABB(this.posX - var4, this.posY - var4, this.posZ - var4, this.posX + var4, this.posY + var4, this.posZ + var4));

                if (var6 != null)
                {
                    var7 = var6.iterator();

                    while (var7.hasNext())
                    {
                        var8 = (EntityLiving)var7.next();

                        if (var8.func_110167_bD() && var8.func_110166_bE() == this)
                        {
                            var8.func_110160_i(true, false);
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * checks to make sure painting can be placed there
     */
    public boolean onValidSurface()
    {
        int var1 = this.worldObj.getBlockId(this.xPosition, this.yPosition, this.zPosition);
        return Block.blocksList[var1] != null && Block.blocksList[var1].getRenderType() == 11;
    }

    public static EntityLeashKnot func_110129_a(World par0World, int par1, int par2, int par3)
    {
        EntityLeashKnot var4 = new EntityLeashKnot(par0World, par1, par2, par3);
        var4.field_98038_p = true;
        par0World.spawnEntityInWorld(var4);
        return var4;
    }

    public static EntityLeashKnot func_110130_b(World par0World, int par1, int par2, int par3)
    {
        List var4 = par0World.getEntitiesWithinAABB(EntityLeashKnot.class, AxisAlignedBB.getAABBPool().getAABB((double)par1 - 1.0D, (double)par2 - 1.0D, (double)par3 - 1.0D, (double)par1 + 1.0D, (double)par2 + 1.0D, (double)par3 + 1.0D));
        Object var5 = null;

        if (var4 != null)
        {
            Iterator var6 = var4.iterator();

            while (var6.hasNext())
            {
                EntityLeashKnot var7 = (EntityLeashKnot)var6.next();

                if (var7.xPosition == par1 && var7.yPosition == par2 && var7.zPosition == par3)
                {
                    return var7;
                }
            }
        }

        return null;
    }
}
