package net.minecraft.src;

public class ItemNameTag extends Item
{
    public ItemNameTag(int par1)
    {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    public boolean func_111207_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase)
    {
        if (!par1ItemStack.hasDisplayName())
        {
            return false;
        }
        else if (par3EntityLivingBase instanceof EntityLiving)
        {
            EntityLiving var4 = (EntityLiving)par3EntityLivingBase;
            var4.func_94058_c(par1ItemStack.getDisplayName());
            var4.func_110163_bv();
            --par1ItemStack.stackSize;
            return true;
        }
        else
        {
            return super.func_111207_a(par1ItemStack, par2EntityPlayer, par3EntityLivingBase);
        }
    }
}
