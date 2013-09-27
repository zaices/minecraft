package net.minecraft.src;

import java.io.IOException;

public class GrassColorReloadListener implements ResourceManagerReloadListener
{
    private static final ResourceLocation field_130078_a = new ResourceLocation("textures/colormap/grass.png");

    public void func_110549_a(ResourceManager par1ResourceManager)
    {
        try
        {
            ColorizerGrass.setGrassBiomeColorizer(TextureUtil.func_110986_a(par1ResourceManager, field_130078_a));
        }
        catch (IOException var3)
        {
            ;
        }
    }
}
