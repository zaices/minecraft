package net.minecraft.src;

import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseAttributeMap
{
    protected final Map field_111154_a = new HashMap();
    protected final Map field_111153_b = new LowerStringMap();

    public AttributeInstance func_111151_a(Attribute par1Attribute)
    {
        return (AttributeInstance)this.field_111154_a.get(par1Attribute);
    }

    public AttributeInstance func_111152_a(String par1Str)
    {
        return (AttributeInstance)this.field_111153_b.get(par1Str);
    }

    public abstract AttributeInstance func_111150_b(Attribute var1);

    public Collection func_111146_a()
    {
        return this.field_111153_b.values();
    }

    public void func_111149_a(ModifiableAttributeInstance par1ModifiableAttributeInstance) {}

    public void func_111148_a(Multimap par1Multimap)
    {
        Iterator var2 = par1Multimap.entries().iterator();

        while (var2.hasNext())
        {
            Entry var3 = (Entry)var2.next();
            AttributeInstance var4 = this.func_111152_a((String)var3.getKey());

            if (var4 != null)
            {
                var4.func_111124_b((AttributeModifier)var3.getValue());
            }
        }
    }

    public void func_111147_b(Multimap par1Multimap)
    {
        Iterator var2 = par1Multimap.entries().iterator();

        while (var2.hasNext())
        {
            Entry var3 = (Entry)var2.next();
            AttributeInstance var4 = this.func_111152_a((String)var3.getKey());

            if (var4 != null)
            {
                var4.func_111124_b((AttributeModifier)var3.getValue());
                var4.func_111121_a((AttributeModifier)var3.getValue());
            }
        }
    }
}
