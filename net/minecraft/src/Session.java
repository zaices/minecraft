package net.minecraft.src;

public class Session
{
    private final String username;
    private final String sessionId;

    public Session(String par1Str, String par2Str)
    {
        this.username = par1Str;
        this.sessionId = par2Str;
    }

    public String func_111285_a()
    {
        return this.username;
    }

    public String func_111286_b()
    {
        return this.sessionId;
    }
}
