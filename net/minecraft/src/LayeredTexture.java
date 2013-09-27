package net.minecraft.src;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;

public class LayeredTexture extends AbstractTexture
{
    public final List field_110567_b;

    public LayeredTexture(String ... par1ArrayOfStr)
    {
        this.field_110567_b = Lists.newArrayList(par1ArrayOfStr);
    }

    public void func_110551_a(ResourceManager par1ResourceManager) throws IOException
    {
        BufferedImage var2 = null;

        try
        {
            Iterator var3 = this.field_110567_b.iterator();

            while (var3.hasNext())
            {
                String var4 = (String)var3.next();

                if (var4 != null)
                {
                    InputStream var5 = par1ResourceManager.func_110536_a(new ResourceLocation(var4)).func_110527_b();
                    BufferedImage var6 = ImageIO.read(var5);

                    if (var2 == null)
                    {
                        var2 = new BufferedImage(var6.getWidth(), var6.getHeight(), 2);
                    }

                    var2.getGraphics().drawImage(var6, 0, 0, (ImageObserver)null);
                }
            }
        }
        catch (IOException var7)
        {
            var7.printStackTrace();
            return;
        }

        TextureUtil.func_110987_a(this.func_110552_b(), var2);
    }
}
