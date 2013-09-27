package net.minecraft.src;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

class SoundPoolProtocolHandler extends URLStreamHandler
{
    final SoundPool field_110658_a;

    SoundPoolProtocolHandler(SoundPool par1SoundPool)
    {
        this.field_110658_a = par1SoundPool;
    }

    protected URLConnection openConnection(URL par1URL)
    {
        return new SoundPoolURLConnection(this.field_110658_a, par1URL, (SoundPoolProtocolHandler)null);
    }
}
