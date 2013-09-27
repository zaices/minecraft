package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class EntityHorse extends EntityAnimal implements IInvBasic
{
    private static final IEntitySelector field_110276_bu = new EntityHorseBredSelector();
    private static final Attribute field_110271_bv = (new RangedAttribute("horse.jumpStrength", 0.7D, 0.0D, 2.0D)).func_111117_a("Jump Strength").func_111112_a(true);
    private static final String[] field_110270_bw = new String[] {null, "textures/entity/horse/armor/horse_armor_iron.png", "textures/entity/horse/armor/horse_armor_gold.png", "textures/entity/horse/armor/horse_armor_diamond.png"};
    private static final String[] field_110273_bx = new String[] {"", "meo", "goo", "dio"};
    private static final int[] field_110272_by = new int[] {0, 5, 7, 11};
    private static final String[] field_110268_bz = new String[] {"textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png"};
    private static final String[] field_110269_bA = new String[] {"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
    private static final String[] field_110291_bB = new String[] {null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png"};
    private static final String[] field_110292_bC = new String[] {"", "wo_", "wmo", "wdo", "bdo"};
    private int field_110289_bD;
    private int field_110290_bE;
    private int field_110295_bF;
    public int field_110278_bp;
    public int field_110279_bq;
    protected boolean field_110275_br;
    private AnimalChest field_110296_bG;
    private boolean field_110293_bH;
    protected int field_110274_bs;
    protected float field_110277_bt;
    private boolean field_110294_bI;
    private float field_110283_bJ;
    private float field_110284_bK;
    private float field_110281_bL;
    private float field_110282_bM;
    private float field_110287_bN;
    private float field_110288_bO;
    private int field_110285_bP;
    private String field_110286_bQ;
    private String[] field_110280_bR = new String[3];

    public EntityHorse(World par1World)
    {
        super(par1World);
        this.setSize(1.4F, 1.6F);
        this.isImmuneToFire = false;
        this.func_110207_m(false);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.2D));
        this.tasks.addTask(1, new EntityAIRunAroundLikeCrazy(this, 1.2D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWander(this, 0.7D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.func_110226_cD();
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
        this.dataWatcher.addObject(19, Byte.valueOf((byte)0));
        this.dataWatcher.addObject(20, Integer.valueOf(0));
        this.dataWatcher.addObject(21, String.valueOf(""));
        this.dataWatcher.addObject(22, Integer.valueOf(0));
    }

    public void func_110214_p(int par1)
    {
        this.dataWatcher.updateObject(19, Byte.valueOf((byte)par1));
        this.func_110230_cF();
    }

    public int func_110265_bP()
    {
        return this.dataWatcher.getWatchableObjectByte(19);
    }

    public void func_110235_q(int par1)
    {
        this.dataWatcher.updateObject(20, Integer.valueOf(par1));
        this.func_110230_cF();
    }

    public int func_110202_bQ()
    {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    /**
     * Gets the username of the entity.
     */
    public String getEntityName()
    {
        if (this.hasCustomNameTag())
        {
            return this.getCustomNameTag();
        }
        else
        {
            int var1 = this.func_110265_bP();

            switch (var1)
            {
                case 0:
                default:
                    return StatCollector.translateToLocal("entity.horse.name");

                case 1:
                    return StatCollector.translateToLocal("entity.donkey.name");

                case 2:
                    return StatCollector.translateToLocal("entity.mule.name");

                case 3:
                    return StatCollector.translateToLocal("entity.zombiehorse.name");

                case 4:
                    return StatCollector.translateToLocal("entity.skeletonhorse.name");
            }
        }
    }

    private boolean func_110233_w(int par1)
    {
        return (this.dataWatcher.getWatchableObjectInt(16) & par1) != 0;
    }

    private void func_110208_b(int par1, boolean par2)
    {
        int var3 = this.dataWatcher.getWatchableObjectInt(16);

        if (par2)
        {
            this.dataWatcher.updateObject(16, Integer.valueOf(var3 | par1));
        }
        else
        {
            this.dataWatcher.updateObject(16, Integer.valueOf(var3 & ~par1));
        }
    }

    public boolean func_110228_bR()
    {
        return !this.isChild();
    }

    public boolean func_110248_bS()
    {
        return this.func_110233_w(2);
    }

    public boolean func_110253_bW()
    {
        return this.func_110228_bR();
    }

    public String func_142019_cb()
    {
        return this.dataWatcher.getWatchableObjectString(21);
    }

    public void func_110213_b(String par1Str)
    {
        this.dataWatcher.updateObject(21, par1Str);
    }

    public float func_110254_bY()
    {
        int var1 = this.getGrowingAge();
        return var1 >= 0 ? 1.0F : 0.5F + (float)(-24000 - var1) / -24000.0F * 0.5F;
    }

    public void func_98054_a(boolean par1)
    {
        if (par1)
        {
            this.func_98055_j(this.func_110254_bY());
        }
        else
        {
            this.func_98055_j(1.0F);
        }
    }

    public boolean func_110246_bZ()
    {
        return this.field_110275_br;
    }

    public void func_110234_j(boolean par1)
    {
        this.func_110208_b(2, par1);
    }

    public void func_110255_k(boolean par1)
    {
        this.field_110275_br = par1;
    }

    public boolean func_110164_bC()
    {
        return !this.func_110256_cu() && super.func_110164_bC();
    }

    protected void func_142017_o(float par1)
    {
        if (par1 > 6.0F && this.func_110204_cc())
        {
            this.func_110227_p(false);
        }
    }

    public boolean func_110261_ca()
    {
        return this.func_110233_w(8);
    }

    public int func_110241_cb()
    {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    public int func_110260_d(ItemStack par1ItemStack)
    {
        return par1ItemStack == null ? 0 : (par1ItemStack.itemID == Item.field_111215_ce.itemID ? 1 : (par1ItemStack.itemID == Item.field_111216_cf.itemID ? 2 : (par1ItemStack.itemID == Item.field_111213_cg.itemID ? 3 : 0)));
    }

    public boolean func_110204_cc()
    {
        return this.func_110233_w(32);
    }

    public boolean func_110209_cd()
    {
        return this.func_110233_w(64);
    }

    public boolean func_110205_ce()
    {
        return this.func_110233_w(16);
    }

    public boolean func_110243_cf()
    {
        return this.field_110293_bH;
    }

    public void func_110236_r(int par1)
    {
        this.dataWatcher.updateObject(22, Integer.valueOf(par1));
        this.func_110230_cF();
    }

    public void func_110242_l(boolean par1)
    {
        this.func_110208_b(16, par1);
    }

    public void func_110207_m(boolean par1)
    {
        this.func_110208_b(8, par1);
    }

    public void func_110221_n(boolean par1)
    {
        this.field_110293_bH = par1;
    }

    public void func_110251_o(boolean par1)
    {
        this.func_110208_b(4, par1);
    }

    public int func_110252_cg()
    {
        return this.field_110274_bs;
    }

    public void func_110238_s(int par1)
    {
        this.field_110274_bs = par1;
    }

    public int func_110198_t(int par1)
    {
        int var2 = MathHelper.clamp_int(this.func_110252_cg() + par1, 0, this.func_110218_cm());
        this.func_110238_s(var2);
        return var2;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        Entity var3 = par1DamageSource.getEntity();
        return this.riddenByEntity != null && this.riddenByEntity.equals(var3) ? false : super.attackEntityFrom(par1DamageSource, par2);
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    public int getTotalArmorValue()
    {
        return field_110272_by[this.func_110241_cb()];
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return this.riddenByEntity == null;
    }

    public boolean func_110262_ch()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posZ);
        this.worldObj.getBiomeGenForCoords(var1, var2);
        return true;
    }

    public void func_110224_ci()
    {
        if (!this.worldObj.isRemote && this.func_110261_ca())
        {
            this.dropItem(Block.chest.blockID, 1);
            this.func_110207_m(false);
        }
    }

    private void func_110266_cB()
    {
        this.func_110249_cI();
        this.worldObj.playSoundAtEntity(this, "eating", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1)
    {
        if (par1 > 1.0F)
        {
            this.playSound("mob.horse.land", 0.4F, 1.0F);
        }

        int var2 = MathHelper.ceiling_float_int(par1 * 0.5F - 3.0F);

        if (var2 > 0)
        {
            this.attackEntityFrom(DamageSource.fall, (float)var2);

            if (this.riddenByEntity != null)
            {
                this.riddenByEntity.attackEntityFrom(DamageSource.fall, (float)var2);
            }

            int var3 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 0.2D - (double)this.prevRotationYaw), MathHelper.floor_double(this.posZ));

            if (var3 > 0)
            {
                StepSound var4 = Block.blocksList[var3].stepSound;
                this.worldObj.playSoundAtEntity(this, var4.getStepSound(), var4.getVolume() * 0.5F, var4.getPitch() * 0.75F);
            }
        }
    }

    private int func_110225_cC()
    {
        int var1 = this.func_110265_bP();
        return this.func_110261_ca() && (var1 == 1 || var1 == 2) ? 17 : 2;
    }

    private void func_110226_cD()
    {
        AnimalChest var1 = this.field_110296_bG;
        this.field_110296_bG = new AnimalChest("HorseChest", this.func_110225_cC());
        this.field_110296_bG.func_110133_a(this.getEntityName());

        if (var1 != null)
        {
            var1.func_110132_b(this);
            int var2 = Math.min(var1.getSizeInventory(), this.field_110296_bG.getSizeInventory());

            for (int var3 = 0; var3 < var2; ++var3)
            {
                ItemStack var4 = var1.getStackInSlot(var3);

                if (var4 != null)
                {
                    this.field_110296_bG.setInventorySlotContents(var3, var4.copy());
                }
            }

            var1 = null;
        }

        this.field_110296_bG.func_110134_a(this);
        this.func_110232_cE();
    }

    private void func_110232_cE()
    {
        if (!this.worldObj.isRemote)
        {
            this.func_110251_o(this.field_110296_bG.getStackInSlot(0) != null);

            if (this.func_110259_cr())
            {
                this.func_110236_r(this.func_110260_d(this.field_110296_bG.getStackInSlot(1)));
            }
        }
    }

    /**
     * Called by InventoryBasic.onInventoryChanged() on a array that is never filled.
     */
    public void onInventoryChanged(InventoryBasic par1InventoryBasic)
    {
        int var2 = this.func_110241_cb();
        boolean var3 = this.func_110257_ck();
        this.func_110232_cE();

        if (this.ticksExisted > 20)
        {
            if (var2 == 0 && var2 != this.func_110241_cb())
            {
                this.playSound("mob.horse.armor", 0.5F, 1.0F);
            }

            if (!var3 && this.func_110257_ck())
            {
                this.playSound("mob.horse.leather", 0.5F, 1.0F);
            }
        }
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        this.func_110262_ch();
        return super.getCanSpawnHere();
    }

    protected EntityHorse func_110250_a(Entity par1Entity, double par2)
    {
        double var4 = Double.MAX_VALUE;
        Entity var6 = null;
        List var7 = this.worldObj.getEntitiesWithinAABBExcludingEntity(par1Entity, par1Entity.boundingBox.addCoord(par2, par2, par2), field_110276_bu);
        Iterator var8 = var7.iterator();

        while (var8.hasNext())
        {
            Entity var9 = (Entity)var8.next();
            double var10 = var9.getDistanceSq(par1Entity.posX, par1Entity.posY, par1Entity.posZ);

            if (var10 < var4)
            {
                var6 = var9;
                var4 = var10;
            }
        }

        return (EntityHorse)var6;
    }

    public double func_110215_cj()
    {
        return this.func_110148_a(field_110271_bv).func_111126_e();
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        this.func_110249_cI();
        int var1 = this.func_110265_bP();
        return var1 == 3 ? "mob.horse.zombie.death" : (var1 == 4 ? "mob.horse.skeleton.death" : (var1 != 1 && var1 != 2 ? "mob.horse.death" : "mob.horse.donkey.death"));
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        boolean var1 = this.rand.nextInt(4) == 0;
        int var2 = this.func_110265_bP();
        return var2 == 4 ? Item.bone.itemID : (var2 == 3 ? (var1 ? 0 : Item.rottenFlesh.itemID) : Item.leather.itemID);
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        this.func_110249_cI();

        if (this.rand.nextInt(3) == 0)
        {
            this.func_110220_cK();
        }

        int var1 = this.func_110265_bP();
        return var1 == 3 ? "mob.horse.zombie.hit" : (var1 == 4 ? "mob.horse.skeleton.hit" : (var1 != 1 && var1 != 2 ? "mob.horse.hit" : "mob.horse.donkey.hit"));
    }

    public boolean func_110257_ck()
    {
        return this.func_110233_w(4);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        this.func_110249_cI();

        if (this.rand.nextInt(10) == 0 && !this.isMovementBlocked())
        {
            this.func_110220_cK();
        }

        int var1 = this.func_110265_bP();
        return var1 == 3 ? "mob.horse.zombie.idle" : (var1 == 4 ? "mob.horse.skeleton.idle" : (var1 != 1 && var1 != 2 ? "mob.horse.idle" : "mob.horse.donkey.idle"));
    }

    protected String func_110217_cl()
    {
        this.func_110249_cI();
        this.func_110220_cK();
        int var1 = this.func_110265_bP();
        return var1 != 3 && var1 != 4 ? (var1 != 1 && var1 != 2 ? "mob.horse.angry" : "mob.horse.donkey.angry") : null;
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        StepSound var5 = Block.blocksList[par4].stepSound;

        if (this.worldObj.getBlockId(par1, par2 + 1, par3) == Block.snow.blockID)
        {
            var5 = Block.snow.stepSound;
        }

        if (!Block.blocksList[par4].blockMaterial.isLiquid())
        {
            int var6 = this.func_110265_bP();

            if (this.riddenByEntity != null && var6 != 1 && var6 != 2)
            {
                ++this.field_110285_bP;

                if (this.field_110285_bP > 5 && this.field_110285_bP % 3 == 0)
                {
                    this.playSound("mob.horse.gallop", var5.getVolume() * 0.15F, var5.getPitch());

                    if (var6 == 0 && this.rand.nextInt(10) == 0)
                    {
                        this.playSound("mob.horse.breathe", var5.getVolume() * 0.6F, var5.getPitch());
                    }
                }
                else if (this.field_110285_bP <= 5)
                {
                    this.playSound("mob.horse.wood", var5.getVolume() * 0.15F, var5.getPitch());
                }
            }
            else if (var5 == Block.soundWoodFootstep)
            {
                this.playSound("mob.horse.soft", var5.getVolume() * 0.15F, var5.getPitch());
            }
            else
            {
                this.playSound("mob.horse.wood", var5.getVolume() * 0.15F, var5.getPitch());
            }
        }
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110140_aT().func_111150_b(field_110271_bv);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(53.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22499999403953552D);
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 6;
    }

    public int func_110218_cm()
    {
        return 100;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.8F;
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 400;
    }

    public boolean func_110239_cn()
    {
        return this.func_110265_bP() == 0 || this.func_110241_cb() > 0;
    }

    private void func_110230_cF()
    {
        this.field_110286_bQ = null;
    }

    private void func_110247_cG()
    {
        this.field_110286_bQ = "horse/";
        this.field_110280_bR[0] = null;
        this.field_110280_bR[1] = null;
        this.field_110280_bR[2] = null;
        int var1 = this.func_110265_bP();
        int var2 = this.func_110202_bQ();
        int var3;

        if (var1 == 0)
        {
            var3 = var2 & 255;
            int var4 = (var2 & 65280) >> 8;
            this.field_110280_bR[0] = field_110268_bz[var3];
            this.field_110286_bQ = this.field_110286_bQ + field_110269_bA[var3];
            this.field_110280_bR[1] = field_110291_bB[var4];
            this.field_110286_bQ = this.field_110286_bQ + field_110292_bC[var4];
        }
        else
        {
            this.field_110280_bR[0] = "";
            this.field_110286_bQ = this.field_110286_bQ + "_" + var1 + "_";
        }

        var3 = this.func_110241_cb();
        this.field_110280_bR[2] = field_110270_bw[var3];
        this.field_110286_bQ = this.field_110286_bQ + field_110273_bx[var3];
    }

    public String func_110264_co()
    {
        if (this.field_110286_bQ == null)
        {
            this.func_110247_cG();
        }

        return this.field_110286_bQ;
    }

    public String[] func_110212_cp()
    {
        if (this.field_110286_bQ == null)
        {
            this.func_110247_cG();
        }

        return this.field_110280_bR;
    }

    public void func_110199_f(EntityPlayer par1EntityPlayer)
    {
        if (!this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == par1EntityPlayer) && this.func_110248_bS())
        {
            this.field_110296_bG.func_110133_a(this.getEntityName());
            par1EntityPlayer.func_110298_a(this, this.field_110296_bG);
        }
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

        if (var2 != null && var2.itemID == Item.monsterPlacer.itemID)
        {
            return super.interact(par1EntityPlayer);
        }
        else if (!this.func_110248_bS() && this.func_110256_cu())
        {
            return false;
        }
        else if (this.func_110248_bS() && this.func_110228_bR() && par1EntityPlayer.isSneaking())
        {
            this.func_110199_f(par1EntityPlayer);
            return true;
        }
        else if (this.func_110253_bW() && this.riddenByEntity != null)
        {
            return super.interact(par1EntityPlayer);
        }
        else
        {
            if (var2 != null)
            {
                boolean var3 = false;

                if (this.func_110259_cr())
                {
                    byte var4 = -1;

                    if (var2.itemID == Item.field_111215_ce.itemID)
                    {
                        var4 = 1;
                    }
                    else if (var2.itemID == Item.field_111216_cf.itemID)
                    {
                        var4 = 2;
                    }
                    else if (var2.itemID == Item.field_111213_cg.itemID)
                    {
                        var4 = 3;
                    }

                    if (var4 >= 0)
                    {
                        if (!this.func_110248_bS())
                        {
                            this.func_110231_cz();
                            return true;
                        }

                        this.func_110199_f(par1EntityPlayer);
                        return true;
                    }
                }

                if (!var3 && !this.func_110256_cu())
                {
                    float var7 = 0.0F;
                    short var5 = 0;
                    byte var6 = 0;

                    if (var2.itemID == Item.wheat.itemID)
                    {
                        var7 = 2.0F;
                        var5 = 60;
                        var6 = 3;
                    }
                    else if (var2.itemID == Item.sugar.itemID)
                    {
                        var7 = 1.0F;
                        var5 = 30;
                        var6 = 3;
                    }
                    else if (var2.itemID == Item.bread.itemID)
                    {
                        var7 = 7.0F;
                        var5 = 180;
                        var6 = 3;
                    }
                    else if (var2.itemID == Block.field_111038_cB.blockID)
                    {
                        var7 = 20.0F;
                        var5 = 180;
                    }
                    else if (var2.itemID == Item.appleRed.itemID)
                    {
                        var7 = 3.0F;
                        var5 = 60;
                        var6 = 3;
                    }
                    else if (var2.itemID == Item.goldenCarrot.itemID)
                    {
                        var7 = 4.0F;
                        var5 = 60;
                        var6 = 5;

                        if (this.func_110248_bS() && this.getGrowingAge() == 0)
                        {
                            var3 = true;
                            this.func_110196_bT();
                        }
                    }
                    else if (var2.itemID == Item.appleGold.itemID)
                    {
                        var7 = 10.0F;
                        var5 = 240;
                        var6 = 10;

                        if (this.func_110248_bS() && this.getGrowingAge() == 0)
                        {
                            var3 = true;
                            this.func_110196_bT();
                        }
                    }

                    if (this.func_110143_aJ() < this.func_110138_aP() && var7 > 0.0F)
                    {
                        this.heal(var7);
                        var3 = true;
                    }

                    if (!this.func_110228_bR() && var5 > 0)
                    {
                        this.func_110195_a(var5);
                        var3 = true;
                    }

                    if (var6 > 0 && (var3 || !this.func_110248_bS()) && var6 < this.func_110218_cm())
                    {
                        var3 = true;
                        this.func_110198_t(var6);
                    }

                    if (var3)
                    {
                        this.func_110266_cB();
                    }
                }

                if (!this.func_110248_bS() && !var3)
                {
                    if (var2 != null && var2.func_111282_a(par1EntityPlayer, this))
                    {
                        return true;
                    }

                    this.func_110231_cz();
                    return true;
                }

                if (!var3 && this.func_110229_cs() && !this.func_110261_ca() && var2.itemID == Block.chest.blockID)
                {
                    this.func_110207_m(true);
                    this.playSound("mob.chickenplop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    var3 = true;
                    this.func_110226_cD();
                }

                if (!var3 && this.func_110253_bW() && !this.func_110257_ck() && var2.itemID == Item.saddle.itemID)
                {
                    this.func_110199_f(par1EntityPlayer);
                    return true;
                }

                if (var3)
                {
                    if (!par1EntityPlayer.capabilities.isCreativeMode && --var2.stackSize == 0)
                    {
                        par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                    }

                    return true;
                }
            }

            if (this.func_110253_bW() && this.riddenByEntity == null)
            {
                if (var2 != null && var2.func_111282_a(par1EntityPlayer, this))
                {
                    return true;
                }
                else
                {
                    this.func_110237_h(par1EntityPlayer);
                    return true;
                }
            }
            else
            {
                return super.interact(par1EntityPlayer);
            }
        }
    }

    private void func_110237_h(EntityPlayer par1EntityPlayer)
    {
        par1EntityPlayer.rotationYaw = this.rotationYaw;
        par1EntityPlayer.rotationPitch = this.rotationPitch;
        this.func_110227_p(false);
        this.func_110219_q(false);

        if (!this.worldObj.isRemote)
        {
            par1EntityPlayer.mountEntity(this);
        }
    }

    public boolean func_110259_cr()
    {
        return this.func_110265_bP() == 0;
    }

    public boolean func_110229_cs()
    {
        int var1 = this.func_110265_bP();
        return var1 == 2 || var1 == 1;
    }

    /**
     * Dead and sleeping entities cannot move
     */
    protected boolean isMovementBlocked()
    {
        return this.riddenByEntity != null && this.func_110257_ck() ? true : this.func_110204_cc() || this.func_110209_cd();
    }

    public boolean func_110256_cu()
    {
        int var1 = this.func_110265_bP();
        return var1 == 3 || var1 == 4;
    }

    public boolean func_110222_cv()
    {
        return this.func_110256_cu() || this.func_110265_bP() == 2;
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return false;
    }

    private void func_110210_cH()
    {
        this.field_110278_bp = 1;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource par1DamageSource)
    {
        super.onDeath(par1DamageSource);

        if (!this.worldObj.isRemote)
        {
            this.func_110244_cA();
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (this.rand.nextInt(200) == 0)
        {
            this.func_110210_cH();
        }

        super.onLivingUpdate();

        if (!this.worldObj.isRemote)
        {
            if (this.rand.nextInt(900) == 0 && this.deathTime == 0)
            {
                this.heal(1.0F);
            }

            if (!this.func_110204_cc() && this.riddenByEntity == null && this.rand.nextInt(300) == 0 && this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - 1, MathHelper.floor_double(this.posZ)) == Block.grass.blockID)
            {
                this.func_110227_p(true);
            }

            if (this.func_110204_cc() && ++this.field_110289_bD > 50)
            {
                this.field_110289_bD = 0;
                this.func_110227_p(false);
            }

            if (this.func_110205_ce() && !this.func_110228_bR() && !this.func_110204_cc())
            {
                EntityHorse var1 = this.func_110250_a(this, 16.0D);

                if (var1 != null && this.getDistanceSqToEntity(var1) > 4.0D)
                {
                    PathEntity var2 = this.worldObj.getPathEntityToEntity(this, var1, 16.0F, true, false, false, true);
                    this.setPathToEntity(var2);
                }
            }
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.worldObj.isRemote && this.dataWatcher.hasChanges())
        {
            this.dataWatcher.func_111144_e();
            this.func_110230_cF();
        }

        if (this.field_110290_bE > 0 && ++this.field_110290_bE > 30)
        {
            this.field_110290_bE = 0;
            this.func_110208_b(128, false);
        }

        if (!this.worldObj.isRemote && this.field_110295_bF > 0 && ++this.field_110295_bF > 20)
        {
            this.field_110295_bF = 0;
            this.func_110219_q(false);
        }

        if (this.field_110278_bp > 0 && ++this.field_110278_bp > 8)
        {
            this.field_110278_bp = 0;
        }

        if (this.field_110279_bq > 0)
        {
            ++this.field_110279_bq;

            if (this.field_110279_bq > 300)
            {
                this.field_110279_bq = 0;
            }
        }

        this.field_110284_bK = this.field_110283_bJ;

        if (this.func_110204_cc())
        {
            this.field_110283_bJ += (1.0F - this.field_110283_bJ) * 0.4F + 0.05F;

            if (this.field_110283_bJ > 1.0F)
            {
                this.field_110283_bJ = 1.0F;
            }
        }
        else
        {
            this.field_110283_bJ += (0.0F - this.field_110283_bJ) * 0.4F - 0.05F;

            if (this.field_110283_bJ < 0.0F)
            {
                this.field_110283_bJ = 0.0F;
            }
        }

        this.field_110282_bM = this.field_110281_bL;

        if (this.func_110209_cd())
        {
            this.field_110284_bK = this.field_110283_bJ = 0.0F;
            this.field_110281_bL += (1.0F - this.field_110281_bL) * 0.4F + 0.05F;

            if (this.field_110281_bL > 1.0F)
            {
                this.field_110281_bL = 1.0F;
            }
        }
        else
        {
            this.field_110294_bI = false;
            this.field_110281_bL += (0.8F * this.field_110281_bL * this.field_110281_bL * this.field_110281_bL - this.field_110281_bL) * 0.6F - 0.05F;

            if (this.field_110281_bL < 0.0F)
            {
                this.field_110281_bL = 0.0F;
            }
        }

        this.field_110288_bO = this.field_110287_bN;

        if (this.func_110233_w(128))
        {
            this.field_110287_bN += (1.0F - this.field_110287_bN) * 0.7F + 0.05F;

            if (this.field_110287_bN > 1.0F)
            {
                this.field_110287_bN = 1.0F;
            }
        }
        else
        {
            this.field_110287_bN += (0.0F - this.field_110287_bN) * 0.7F - 0.05F;

            if (this.field_110287_bN < 0.0F)
            {
                this.field_110287_bN = 0.0F;
            }
        }
    }

    private void func_110249_cI()
    {
        if (!this.worldObj.isRemote)
        {
            this.field_110290_bE = 1;
            this.func_110208_b(128, true);
        }
    }

    private boolean func_110200_cJ()
    {
        return this.riddenByEntity == null && this.ridingEntity == null && this.func_110248_bS() && this.func_110228_bR() && !this.func_110222_cv() && this.func_110143_aJ() >= this.func_110138_aP();
    }

    public void setEating(boolean par1)
    {
        this.func_110208_b(32, par1);
    }

    public void func_110227_p(boolean par1)
    {
        this.setEating(par1);
    }

    public void func_110219_q(boolean par1)
    {
        if (par1)
        {
            this.func_110227_p(false);
        }

        this.func_110208_b(64, par1);
    }

    private void func_110220_cK()
    {
        if (!this.worldObj.isRemote)
        {
            this.field_110295_bF = 1;
            this.func_110219_q(true);
        }
    }

    public void func_110231_cz()
    {
        this.func_110220_cK();
        String var1 = this.func_110217_cl();

        if (var1 != null)
        {
            this.playSound(var1, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    public void func_110244_cA()
    {
        this.func_110240_a(this, this.field_110296_bG);
        this.func_110224_ci();
    }

    private void func_110240_a(Entity par1Entity, AnimalChest par2AnimalChest)
    {
        if (par2AnimalChest != null && !this.worldObj.isRemote)
        {
            for (int var3 = 0; var3 < par2AnimalChest.getSizeInventory(); ++var3)
            {
                ItemStack var4 = par2AnimalChest.getStackInSlot(var3);

                if (var4 != null)
                {
                    this.entityDropItem(var4, 0.0F);
                }
            }
        }
    }

    public boolean func_110263_g(EntityPlayer par1EntityPlayer)
    {
        this.func_110213_b(par1EntityPlayer.getCommandSenderName());
        this.func_110234_j(true);
        return true;
    }

    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public void moveEntityWithHeading(float par1, float par2)
    {
        if (this.riddenByEntity != null && this.func_110257_ck())
        {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            par1 = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
            par2 = ((EntityLivingBase)this.riddenByEntity).moveForward;

            if (par2 <= 0.0F)
            {
                par2 *= 0.25F;
                this.field_110285_bP = 0;
            }

            if (this.onGround && this.field_110277_bt == 0.0F && this.func_110209_cd() && !this.field_110294_bI)
            {
                par1 = 0.0F;
                par2 = 0.0F;
            }

            if (this.field_110277_bt > 0.0F && !this.func_110246_bZ() && this.onGround)
            {
                this.motionY = this.func_110215_cj() * (double)this.field_110277_bt;

                if (this.isPotionActive(Potion.jump))
                {
                    this.motionY += (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
                }

                this.func_110255_k(true);
                this.isAirBorne = true;

                if (par2 > 0.0F)
                {
                    float var3 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F);
                    float var4 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F);
                    this.motionX += (double)(-0.4F * var3 * this.field_110277_bt);
                    this.motionZ += (double)(0.4F * var4 * this.field_110277_bt);
                    this.playSound("mob.horse.jump", 0.4F, 1.0F);
                }

                this.field_110277_bt = 0.0F;
            }

            this.stepHeight = 1.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            if (!this.worldObj.isRemote)
            {
                this.setAIMoveSpeed((float)this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
                super.moveEntityWithHeading(par1, par2);
            }

            if (this.onGround)
            {
                this.field_110277_bt = 0.0F;
                this.func_110255_k(false);
            }

            this.prevLimbYaw = this.limbYaw;
            double var8 = this.posX - this.prevPosX;
            double var5 = this.posZ - this.prevPosZ;
            float var7 = MathHelper.sqrt_double(var8 * var8 + var5 * var5) * 4.0F;

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            this.limbYaw += (var7 - this.limbYaw) * 0.4F;
            this.limbSwing += this.limbYaw;
        }
        else
        {
            this.stepHeight = 0.5F;
            this.jumpMovementFactor = 0.02F;
            super.moveEntityWithHeading(par1, par2);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("EatingHaystack", this.func_110204_cc());
        par1NBTTagCompound.setBoolean("ChestedHorse", this.func_110261_ca());
        par1NBTTagCompound.setBoolean("HasReproduced", this.func_110243_cf());
        par1NBTTagCompound.setBoolean("Bred", this.func_110205_ce());
        par1NBTTagCompound.setInteger("Type", this.func_110265_bP());
        par1NBTTagCompound.setInteger("Variant", this.func_110202_bQ());
        par1NBTTagCompound.setInteger("Temper", this.func_110252_cg());
        par1NBTTagCompound.setBoolean("Tame", this.func_110248_bS());
        par1NBTTagCompound.setString("OwnerName", this.func_142019_cb());

        if (this.func_110261_ca())
        {
            NBTTagList var2 = new NBTTagList();

            for (int var3 = 2; var3 < this.field_110296_bG.getSizeInventory(); ++var3)
            {
                ItemStack var4 = this.field_110296_bG.getStackInSlot(var3);

                if (var4 != null)
                {
                    NBTTagCompound var5 = new NBTTagCompound();
                    var5.setByte("Slot", (byte)var3);
                    var4.writeToNBT(var5);
                    var2.appendTag(var5);
                }
            }

            par1NBTTagCompound.setTag("Items", var2);
        }

        if (this.field_110296_bG.getStackInSlot(1) != null)
        {
            par1NBTTagCompound.setTag("ArmorItem", this.field_110296_bG.getStackInSlot(1).writeToNBT(new NBTTagCompound("ArmorItem")));
        }

        if (this.field_110296_bG.getStackInSlot(0) != null)
        {
            par1NBTTagCompound.setTag("SaddleItem", this.field_110296_bG.getStackInSlot(0).writeToNBT(new NBTTagCompound("SaddleItem")));
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.func_110227_p(par1NBTTagCompound.getBoolean("EatingHaystack"));
        this.func_110242_l(par1NBTTagCompound.getBoolean("Bred"));
        this.func_110207_m(par1NBTTagCompound.getBoolean("ChestedHorse"));
        this.func_110221_n(par1NBTTagCompound.getBoolean("HasReproduced"));
        this.func_110214_p(par1NBTTagCompound.getInteger("Type"));
        this.func_110235_q(par1NBTTagCompound.getInteger("Variant"));
        this.func_110238_s(par1NBTTagCompound.getInteger("Temper"));
        this.func_110234_j(par1NBTTagCompound.getBoolean("Tame"));

        if (par1NBTTagCompound.hasKey("OwnerName"))
        {
            this.func_110213_b(par1NBTTagCompound.getString("OwnerName"));
        }

        AttributeInstance var2 = this.func_110140_aT().func_111152_a("Speed");

        if (var2 != null)
        {
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(var2.func_111125_b() * 0.25D);
        }

        if (this.func_110261_ca())
        {
            NBTTagList var3 = par1NBTTagCompound.getTagList("Items");
            this.func_110226_cD();

            for (int var4 = 0; var4 < var3.tagCount(); ++var4)
            {
                NBTTagCompound var5 = (NBTTagCompound)var3.tagAt(var4);
                int var6 = var5.getByte("Slot") & 255;

                if (var6 >= 2 && var6 < this.field_110296_bG.getSizeInventory())
                {
                    this.field_110296_bG.setInventorySlotContents(var6, ItemStack.loadItemStackFromNBT(var5));
                }
            }
        }

        ItemStack var7;

        if (par1NBTTagCompound.hasKey("ArmorItem"))
        {
            var7 = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("ArmorItem"));

            if (var7 != null && func_110211_v(var7.itemID))
            {
                this.field_110296_bG.setInventorySlotContents(1, var7);
            }
        }

        if (par1NBTTagCompound.hasKey("SaddleItem"))
        {
            var7 = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("SaddleItem"));

            if (var7 != null && var7.itemID == Item.saddle.itemID)
            {
                this.field_110296_bG.setInventorySlotContents(0, var7);
            }
        }
        else if (par1NBTTagCompound.getBoolean("Saddle"))
        {
            this.field_110296_bG.setInventorySlotContents(0, new ItemStack(Item.saddle));
        }

        this.func_110232_cE();
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal par1EntityAnimal)
    {
        if (par1EntityAnimal == this)
        {
            return false;
        }
        else if (par1EntityAnimal.getClass() != this.getClass())
        {
            return false;
        }
        else
        {
            EntityHorse var2 = (EntityHorse)par1EntityAnimal;

            if (this.func_110200_cJ() && var2.func_110200_cJ())
            {
                int var3 = this.func_110265_bP();
                int var4 = var2.func_110265_bP();
                return var3 == var4 || var3 == 0 && var4 == 1 || var3 == 1 && var4 == 0;
            }
            else
            {
                return false;
            }
        }
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        EntityHorse var2 = (EntityHorse)par1EntityAgeable;
        EntityHorse var3 = new EntityHorse(this.worldObj);
        int var4 = this.func_110265_bP();
        int var5 = var2.func_110265_bP();
        int var6 = 0;

        if (var4 == var5)
        {
            var6 = var4;
        }
        else if (var4 == 0 && var5 == 1 || var4 == 1 && var5 == 0)
        {
            var6 = 2;
        }

        if (var6 == 0)
        {
            int var8 = this.rand.nextInt(9);
            int var7;

            if (var8 < 4)
            {
                var7 = this.func_110202_bQ() & 255;
            }
            else if (var8 < 8)
            {
                var7 = var2.func_110202_bQ() & 255;
            }
            else
            {
                var7 = this.rand.nextInt(7);
            }

            int var9 = this.rand.nextInt(5);

            if (var9 < 4)
            {
                var7 |= this.func_110202_bQ() & 65280;
            }
            else if (var9 < 8)
            {
                var7 |= var2.func_110202_bQ() & 65280;
            }
            else
            {
                var7 |= this.rand.nextInt(5) << 8 & 65280;
            }

            var3.func_110235_q(var7);
        }

        var3.func_110214_p(var6);
        double var14 = this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() + par1EntityAgeable.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() + (double)this.func_110267_cL();
        var3.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(var14 / 3.0D);
        double var13 = this.func_110148_a(field_110271_bv).func_111125_b() + par1EntityAgeable.func_110148_a(field_110271_bv).func_111125_b() + this.func_110245_cM();
        var3.func_110148_a(field_110271_bv).func_111128_a(var13 / 3.0D);
        double var11 = this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() + par1EntityAgeable.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() + this.func_110203_cN();
        var3.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(var11 / 3.0D);
        return var3;
    }

    public EntityLivingData func_110161_a(EntityLivingData par1EntityLivingData)
    {
        Object par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
        boolean var2 = false;
        int var3 = 0;
        int var7;

        if (par1EntityLivingData1 instanceof EntityHorseGroupData)
        {
            var7 = ((EntityHorseGroupData)par1EntityLivingData1).field_111107_a;
            var3 = ((EntityHorseGroupData)par1EntityLivingData1).field_111106_b & 255 | this.rand.nextInt(5) << 8;
        }
        else
        {
            if (this.rand.nextInt(10) == 0)
            {
                var7 = 1;
            }
            else
            {
                int var4 = this.rand.nextInt(7);
                int var5 = this.rand.nextInt(5);
                var7 = 0;
                var3 = var4 | var5 << 8;
            }

            par1EntityLivingData1 = new EntityHorseGroupData(var7, var3);
        }

        this.func_110214_p(var7);
        this.func_110235_q(var3);

        if (this.rand.nextInt(5) == 0)
        {
            this.setGrowingAge(-24000);
        }

        if (var7 != 4 && var7 != 3)
        {
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)this.func_110267_cL());

            if (var7 == 0)
            {
                this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.func_110203_cN());
            }
            else
            {
                this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.17499999701976776D);
            }
        }
        else
        {
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15.0D);
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000000298023224D);
        }

        if (var7 != 2 && var7 != 1)
        {
            this.func_110148_a(field_110271_bv).func_111128_a(this.func_110245_cM());
        }
        else
        {
            this.func_110148_a(field_110271_bv).func_111128_a(0.5D);
        }

        this.setEntityHealth(this.func_110138_aP());
        return (EntityLivingData)par1EntityLivingData1;
    }

    public float func_110258_o(float par1)
    {
        return this.field_110284_bK + (this.field_110283_bJ - this.field_110284_bK) * par1;
    }

    public float func_110223_p(float par1)
    {
        return this.field_110282_bM + (this.field_110281_bL - this.field_110282_bM) * par1;
    }

    public float func_110201_q(float par1)
    {
        return this.field_110288_bO + (this.field_110287_bN - this.field_110288_bO) * par1;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    protected boolean isAIEnabled()
    {
        return true;
    }

    public void func_110206_u(int par1)
    {
        if (this.func_110257_ck())
        {
            if (par1 < 0)
            {
                par1 = 0;
            }
            else
            {
                this.field_110294_bI = true;
                this.func_110220_cK();
            }

            if (par1 >= 90)
            {
                this.field_110277_bt = 1.0F;
            }
            else
            {
                this.field_110277_bt = 0.4F + 0.4F * (float)par1 / 90.0F;
            }
        }
    }

    protected void func_110216_r(boolean par1)
    {
        String var2 = par1 ? "heart" : "smoke";

        for (int var3 = 0; var3 < 7; ++var3)
        {
            double var4 = this.rand.nextGaussian() * 0.02D;
            double var6 = this.rand.nextGaussian() * 0.02D;
            double var8 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(var2, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var4, var6, var8);
        }
    }

    public void handleHealthUpdate(byte par1)
    {
        if (par1 == 7)
        {
            this.func_110216_r(true);
        }
        else if (par1 == 6)
        {
            this.func_110216_r(false);
        }
        else
        {
            super.handleHealthUpdate(par1);
        }
    }

    public void updateRiderPosition()
    {
        super.updateRiderPosition();

        if (this.field_110282_bM > 0.0F)
        {
            float var1 = MathHelper.sin(this.renderYawOffset * (float)Math.PI / 180.0F);
            float var2 = MathHelper.cos(this.renderYawOffset * (float)Math.PI / 180.0F);
            float var3 = 0.7F * this.field_110282_bM;
            float var4 = 0.15F * this.field_110282_bM;
            this.riddenByEntity.setPosition(this.posX + (double)(var3 * var1), this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset() + (double)var4, this.posZ - (double)(var3 * var2));

            if (this.riddenByEntity instanceof EntityLivingBase)
            {
                ((EntityLivingBase)this.riddenByEntity).renderYawOffset = this.renderYawOffset;
            }
        }
    }

    private float func_110267_cL()
    {
        return 15.0F + (float)this.rand.nextInt(8) + (float)this.rand.nextInt(9);
    }

    private double func_110245_cM()
    {
        return 0.4000000059604645D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D;
    }

    private double func_110203_cN()
    {
        return (0.44999998807907104D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D) * 0.25D;
    }

    public static boolean func_110211_v(int par0)
    {
        return par0 == Item.field_111215_ce.itemID || par0 == Item.field_111216_cf.itemID || par0 == Item.field_111213_cg.itemID;
    }

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    public boolean isOnLadder()
    {
        return false;
    }
}
