package net.minecraft.src;

public class RenderCow extends RenderLiving
{
    private static final ResourceLocation field_110833_a = new ResourceLocation("textures/entity/cow/cow.png");

    public RenderCow(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation func_110832_a(EntityCow par1EntityCow)
    {
        return field_110833_a;
    }

    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.func_110832_a((EntityCow)par1Entity);
    }
}
