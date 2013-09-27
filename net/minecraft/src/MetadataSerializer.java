package net.minecraft.src;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class MetadataSerializer
{
    private final IRegistry field_110508_a = new RegistrySimple();
    private final GsonBuilder field_110506_b = new GsonBuilder();
    private Gson field_110507_c;

    public void func_110504_a(MetadataSectionSerializer par1MetadataSectionSerializer, Class par2Class)
    {
        this.field_110508_a.putObject(par1MetadataSectionSerializer.func_110483_a(), new MetadataSerializerRegistration(this, par1MetadataSectionSerializer, par2Class, (MetadataSerializerEmptyAnon)null));
        this.field_110506_b.registerTypeAdapter(par2Class, par1MetadataSectionSerializer);
        this.field_110507_c = null;
    }

    public MetadataSection func_110503_a(String par1Str, JsonObject par2JsonObject)
    {
        if (par1Str == null)
        {
            throw new IllegalArgumentException("Metadata section name cannot be null");
        }
        else if (!par2JsonObject.has(par1Str))
        {
            return null;
        }
        else if (!par2JsonObject.get(par1Str).isJsonObject())
        {
            throw new IllegalArgumentException("Invalid metadata for \'" + par1Str + "\' - expected object, found " + par2JsonObject.get(par1Str));
        }
        else
        {
            MetadataSerializerRegistration var3 = (MetadataSerializerRegistration)this.field_110508_a.func_82594_a(par1Str);

            if (var3 == null)
            {
                throw new IllegalArgumentException("Don\'t know how to handle metadata section \'" + par1Str + "\'");
            }
            else
            {
                return (MetadataSection)this.func_110505_a().fromJson(par2JsonObject.getAsJsonObject(par1Str), var3.field_110500_b);
            }
        }
    }

    private Gson func_110505_a()
    {
        if (this.field_110507_c == null)
        {
            this.field_110507_c = this.field_110506_b.create();
        }

        return this.field_110507_c;
    }
}
