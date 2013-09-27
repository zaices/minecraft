package net.minecraft.src;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class SoundPoolURLConnection extends URLConnection
{
    private final ResourceLocation field_110659_b;

    final SoundPool field_110660_a;

    private SoundPoolURLConnection(SoundPool par1SoundPool, URL par2URL)
    {
        super(par2URL);
        this.field_110660_a = par1SoundPool;
        this.field_110659_b = new ResourceLocation(par2URL.getPath());
    }

    public void connect() {}

    public InputStream getInputStream()
    {
        try
        {
            return SoundPool.func_110655_a(this.field_110660_a).func_110536_a(this.field_110659_b).func_110527_b();
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    SoundPoolURLConnection(SoundPool par1SoundPool, URL par2URL, SoundPoolProtocolHandler par3SoundPoolProtocolHandler)
    {
        this(par1SoundPool, par2URL);
    }
}
