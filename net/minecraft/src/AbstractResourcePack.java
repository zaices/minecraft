package net.minecraft.src;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;

public abstract class AbstractResourcePack implements ResourcePack
{
    protected static final ILogAgent field_110598_a = Minecraft.getMinecraft().getLogAgent();
    protected final File field_110597_b;

    public AbstractResourcePack(File par1File)
    {
        this.field_110597_b = par1File;
    }

    private static String func_110592_c(ResourceLocation par0ResourceLocation)
    {
        return String.format("%s/%s/%s", new Object[] {"assets", par0ResourceLocation.func_110624_b(), par0ResourceLocation.func_110623_a()});
    }

    protected static String func_110595_a(File par0File, File par1File)
    {
        return par0File.toURI().relativize(par1File.toURI()).getPath();
    }

    public InputStream func_110590_a(ResourceLocation par1ResourceLocation) throws IOException
    {
        return this.func_110591_a(func_110592_c(par1ResourceLocation));
    }

    public boolean func_110589_b(ResourceLocation par1ResourceLocation)
    {
        return this.func_110593_b(func_110592_c(par1ResourceLocation));
    }

    protected abstract InputStream func_110591_a(String var1) throws IOException;

    protected abstract boolean func_110593_b(String var1);

    protected void func_110594_c(String par1Str)
    {
        field_110598_a.logWarningFormatted("ResourcePack: ignored non-lowercase namespace: %s in %s", new Object[] {par1Str, this.field_110597_b});
    }

    public MetadataSection func_135058_a(MetadataSerializer par1MetadataSerializer, String par2Str) throws IOException
    {
        return func_110596_a(par1MetadataSerializer, this.func_110591_a("pack.mcmeta"), par2Str);
    }

    static MetadataSection func_110596_a(MetadataSerializer par0MetadataSerializer, InputStream par1InputStream, String par2Str)
    {
        JsonObject var3 = null;
        BufferedReader var4 = null;

        try
        {
            var4 = new BufferedReader(new InputStreamReader(par1InputStream));
            var3 = (new JsonParser()).parse(var4).getAsJsonObject();
        }
        finally
        {
            IOUtils.closeQuietly(var4);
        }

        return par0MetadataSerializer.func_110503_a(par2Str, var3);
    }

    public BufferedImage func_110586_a() throws IOException
    {
        return ImageIO.read(this.func_110591_a("pack.png"));
    }

    public String func_130077_b()
    {
        return this.field_110597_b.getName();
    }
}
