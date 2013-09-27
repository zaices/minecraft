package net.minecraft.src;

import org.apache.commons.lang3.Validate;

public class ResourceLocation
{
    private final String field_110626_a;
    private final String field_110625_b;

    public ResourceLocation(String par1Str, String par2Str)
    {
        Validate.notNull(par2Str);

        if (par1Str != null && par1Str.length() != 0)
        {
            this.field_110626_a = par1Str;
        }
        else
        {
            this.field_110626_a = "minecraft";
        }

        this.field_110625_b = par2Str;
    }

    public ResourceLocation(String par1Str)
    {
        String var2 = "minecraft";
        String var3 = par1Str;
        int var4 = par1Str.indexOf(58);

        if (var4 >= 0)
        {
            var3 = par1Str.substring(var4 + 1, par1Str.length());

            if (var4 > 1)
            {
                var2 = par1Str.substring(0, var4);
            }
        }

        this.field_110626_a = var2.toLowerCase();
        this.field_110625_b = var3;
    }

    public String func_110623_a()
    {
        return this.field_110625_b;
    }

    public String func_110624_b()
    {
        return this.field_110626_a;
    }

    public String toString()
    {
        return this.field_110626_a + ":" + this.field_110625_b;
    }

    public boolean equals(Object par1Obj)
    {
        if (this == par1Obj)
        {
            return true;
        }
        else if (!(par1Obj instanceof ResourceLocation))
        {
            return false;
        }
        else
        {
            ResourceLocation var2 = (ResourceLocation)par1Obj;
            return this.field_110626_a.equals(var2.field_110626_a) && this.field_110625_b.equals(var2.field_110625_b);
        }
    }

    public int hashCode()
    {
        return 31 * this.field_110626_a.hashCode() + this.field_110625_b.hashCode();
    }
}
