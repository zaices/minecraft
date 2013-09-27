package net.minecraft.src;

import java.io.IOException;

public class FoliageColorReloadListener implements ResourceManagerReloadListener
{
    private static final ResourceLocation field_130079_a = new ResourceLocation("textures/colormap/foliage.png");

    public void func_110549_a(ResourceManager par1ResourceManager)
    {
        try
        {
            ColorizerFoliage.setFoliageBiomeColorizer(TextureUtil.func_110986_a(par1ResourceManager, field_130079_a));
        }
        catch (IOException var3)
        {
            ;
        }
    }
}
