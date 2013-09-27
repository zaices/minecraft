package net.minecraft.src;

import java.net.URL;

public class SoundPoolEntry
{
    private final String soundName;
    private final URL soundUrl;

    public SoundPoolEntry(String par1Str, URL par2URL)
    {
        this.soundName = par1Str;
        this.soundUrl = par2URL;
    }

    public String func_110458_a()
    {
        return this.soundName;
    }

    public URL func_110457_b()
    {
        return this.soundUrl;
    }
}
