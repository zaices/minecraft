package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderMagmaCube extends RenderLiving
{
    private static final ResourceLocation field_110873_a = new ResourceLocation("textures/entity/slime/magmacube.png");

    public RenderMagmaCube()
    {
        super(new ModelMagmaCube(), 0.25F);
    }

    protected ResourceLocation func_110872_a(EntityMagmaCube par1EntityMagmaCube)
    {
        return field_110873_a;
    }

    protected void scaleMagmaCube(EntityMagmaCube par1EntityMagmaCube, float par2)
    {
        int var3 = par1EntityMagmaCube.getSlimeSize();
        float var4 = (par1EntityMagmaCube.field_70812_c + (par1EntityMagmaCube.field_70811_b - par1EntityMagmaCube.field_70812_c) * par2) / ((float)var3 * 0.5F + 1.0F);
        float var5 = 1.0F / (var4 + 1.0F);
        float var6 = (float)var3;
        GL11.glScalef(var5 * var6, 1.0F / var5 * var6, var5 * var6);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.scaleMagmaCube((EntityMagmaCube)par1EntityLivingBase, par2);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.func_110872_a((EntityMagmaCube)par1Entity);
    }
}
