package net.minecraft.src;

import java.util.concurrent.Callable;

class TextureManagerINNER1 implements Callable
{
    final TextureObject field_135062_a;

    final TextureManager field_135061_b;

    TextureManagerINNER1(TextureManager par1TextureManager, TextureObject par2TextureObject)
    {
        this.field_135061_b = par1TextureManager;
        this.field_135062_a = par2TextureObject;
    }

    public String func_135060_a()
    {
        return this.field_135062_a.getClass().getName();
    }

    public Object call()
    {
        return this.func_135060_a();
    }
}
