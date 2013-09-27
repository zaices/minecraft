package net.minecraft.src;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class SimpleTexture extends AbstractTexture
{
    private final ResourceLocation field_110568_b;

    public SimpleTexture(ResourceLocation par1ResourceLocation)
    {
        this.field_110568_b = par1ResourceLocation;
    }

    public void func_110551_a(ResourceManager par1ResourceManager) throws IOException
    {
        InputStream var2 = null;

        try
        {
            Resource var3 = par1ResourceManager.func_110536_a(this.field_110568_b);
            var2 = var3.func_110527_b();
            BufferedImage var4 = ImageIO.read(var2);
            boolean var5 = false;
            boolean var6 = false;

            if (var3.func_110528_c())
            {
                try
                {
                    TextureMetadataSection var7 = (TextureMetadataSection)var3.func_110526_a("texture");

                    if (var7 != null)
                    {
                        var5 = var7.func_110479_a();
                        var6 = var7.func_110480_b();
                    }
                }
                catch (RuntimeException var11)
                {
                    Minecraft.getMinecraft().getLogAgent().logWarningException("Failed reading metadata of: " + this.field_110568_b, var11);
                }
            }

            TextureUtil.func_110989_a(this.func_110552_b(), var4, var5, var6);
        }
        finally
        {
            if (var2 != null)
            {
                var2.close();
            }
        }
    }
}
