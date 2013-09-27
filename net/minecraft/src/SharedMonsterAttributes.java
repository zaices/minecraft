package net.minecraft.src;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

public class SharedMonsterAttributes
{
    public static final Attribute field_111267_a = (new RangedAttribute("generic.maxHealth", 20.0D, 0.0D, Double.MAX_VALUE)).func_111117_a("Max Health").func_111112_a(true);
    public static final Attribute field_111265_b = (new RangedAttribute("generic.followRange", 32.0D, 0.0D, 2048.0D)).func_111117_a("Follow Range");
    public static final Attribute field_111266_c = (new RangedAttribute("generic.knockbackResistance", 0.0D, 0.0D, 1.0D)).func_111117_a("Knockback Resistance");
    public static final Attribute field_111263_d = (new RangedAttribute("generic.movementSpeed", 0.699999988079071D, 0.0D, Double.MAX_VALUE)).func_111117_a("Movement Speed").func_111112_a(true);
    public static final Attribute field_111264_e = new RangedAttribute("generic.attackDamage", 2.0D, 0.0D, Double.MAX_VALUE);

    public static NBTTagList func_111257_a(BaseAttributeMap par0BaseAttributeMap)
    {
        NBTTagList var1 = new NBTTagList();
        Iterator var2 = par0BaseAttributeMap.func_111146_a().iterator();

        while (var2.hasNext())
        {
            AttributeInstance var3 = (AttributeInstance)var2.next();
            var1.appendTag(func_111261_a(var3));
        }

        return var1;
    }

    private static NBTTagCompound func_111261_a(AttributeInstance par0AttributeInstance)
    {
        NBTTagCompound var1 = new NBTTagCompound();
        Attribute var2 = par0AttributeInstance.func_111123_a();
        var1.setString("Name", var2.func_111108_a());
        var1.setDouble("Base", par0AttributeInstance.func_111125_b());
        Collection var3 = par0AttributeInstance.func_111122_c();

        if (var3 != null && !var3.isEmpty())
        {
            NBTTagList var4 = new NBTTagList();
            Iterator var5 = var3.iterator();

            while (var5.hasNext())
            {
                AttributeModifier var6 = (AttributeModifier)var5.next();

                if (var6.func_111165_e())
                {
                    var4.appendTag(func_111262_a(var6));
                }
            }

            var1.setTag("Modifiers", var4);
        }

        return var1;
    }

    private static NBTTagCompound func_111262_a(AttributeModifier par0AttributeModifier)
    {
        NBTTagCompound var1 = new NBTTagCompound();
        var1.setString("Name", par0AttributeModifier.func_111166_b());
        var1.setDouble("Amount", par0AttributeModifier.func_111164_d());
        var1.setInteger("Operation", par0AttributeModifier.func_111169_c());
        var1.setLong("UUIDMost", par0AttributeModifier.func_111167_a().getMostSignificantBits());
        var1.setLong("UUIDLeast", par0AttributeModifier.func_111167_a().getLeastSignificantBits());
        return var1;
    }

    public static void func_111260_a(BaseAttributeMap par0BaseAttributeMap, NBTTagList par1NBTTagList, ILogAgent par2ILogAgent)
    {
        for (int var3 = 0; var3 < par1NBTTagList.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)par1NBTTagList.tagAt(var3);
            AttributeInstance var5 = par0BaseAttributeMap.func_111152_a(var4.getString("Name"));

            if (var5 != null)
            {
                func_111258_a(var5, var4);
            }
            else if (par2ILogAgent != null)
            {
                par2ILogAgent.logWarning("Ignoring unknown attribute \'" + var4.getString("Name") + "\'");
            }
        }
    }

    private static void func_111258_a(AttributeInstance par0AttributeInstance, NBTTagCompound par1NBTTagCompound)
    {
        par0AttributeInstance.func_111128_a(par1NBTTagCompound.getDouble("Base"));

        if (par1NBTTagCompound.hasKey("Modifiers"))
        {
            NBTTagList var2 = par1NBTTagCompound.getTagList("Modifiers");

            for (int var3 = 0; var3 < var2.tagCount(); ++var3)
            {
                AttributeModifier var4 = func_111259_a((NBTTagCompound)var2.tagAt(var3));
                AttributeModifier var5 = par0AttributeInstance.func_111127_a(var4.func_111167_a());

                if (var5 != null)
                {
                    par0AttributeInstance.func_111124_b(var5);
                }

                par0AttributeInstance.func_111121_a(var4);
            }
        }
    }

    public static AttributeModifier func_111259_a(NBTTagCompound par0NBTTagCompound)
    {
        UUID var1 = new UUID(par0NBTTagCompound.getLong("UUIDMost"), par0NBTTagCompound.getLong("UUIDLeast"));
        return new AttributeModifier(var1, par0NBTTagCompound.getString("Name"), par0NBTTagCompound.getDouble("Amount"), par0NBTTagCompound.getInteger("Operation"));
    }
}
