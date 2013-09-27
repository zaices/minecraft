package net.minecraft.src;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Iterator;
import java.util.List;

public class ChatMessageComponent
{
    private static final Gson field_111089_a = (new GsonBuilder()).registerTypeAdapter(ChatMessageComponent.class, new MessageComponentSerializer()).create();
    private EnumChatFormatting field_111087_b;
    private Boolean field_111088_c;
    private Boolean field_111085_d;
    private Boolean field_111086_e;
    private Boolean field_111083_f;
    private String field_111084_g;
    private String field_111090_h;
    private List field_111091_i;

    public ChatMessageComponent() {}

    public ChatMessageComponent(ChatMessageComponent par1ChatMessageComponent)
    {
        this.field_111087_b = par1ChatMessageComponent.field_111087_b;
        this.field_111088_c = par1ChatMessageComponent.field_111088_c;
        this.field_111085_d = par1ChatMessageComponent.field_111085_d;
        this.field_111086_e = par1ChatMessageComponent.field_111086_e;
        this.field_111083_f = par1ChatMessageComponent.field_111083_f;
        this.field_111084_g = par1ChatMessageComponent.field_111084_g;
        this.field_111090_h = par1ChatMessageComponent.field_111090_h;
        this.field_111091_i = par1ChatMessageComponent.field_111091_i == null ? null : Lists.newArrayList(par1ChatMessageComponent.field_111091_i);
    }

    public ChatMessageComponent func_111059_a(EnumChatFormatting par1EnumChatFormatting)
    {
        if (par1EnumChatFormatting != null && !par1EnumChatFormatting.func_96302_c())
        {
            throw new IllegalArgumentException("Argument is not a valid color!");
        }
        else
        {
            this.field_111087_b = par1EnumChatFormatting;
            return this;
        }
    }

    public EnumChatFormatting func_111065_a()
    {
        return this.field_111087_b;
    }

    public ChatMessageComponent func_111071_a(Boolean par1)
    {
        this.field_111088_c = par1;
        return this;
    }

    public Boolean func_111058_b()
    {
        return this.field_111088_c;
    }

    public ChatMessageComponent func_111063_b(Boolean par1)
    {
        this.field_111085_d = par1;
        return this;
    }

    public Boolean func_111064_c()
    {
        return this.field_111085_d;
    }

    public ChatMessageComponent func_111081_c(Boolean par1)
    {
        this.field_111086_e = par1;
        return this;
    }

    public Boolean func_111067_d()
    {
        return this.field_111086_e;
    }

    public ChatMessageComponent func_111061_d(Boolean par1)
    {
        this.field_111083_f = par1;
        return this;
    }

    public Boolean func_111076_e()
    {
        return this.field_111083_f;
    }

    protected String func_111075_f()
    {
        return this.field_111084_g;
    }

    protected String func_111074_g()
    {
        return this.field_111090_h;
    }

    protected List func_111069_h()
    {
        return this.field_111091_i;
    }

    public ChatMessageComponent func_111073_a(ChatMessageComponent par1ChatMessageComponent)
    {
        if (this.field_111084_g == null && this.field_111090_h == null)
        {
            if (this.field_111091_i != null)
            {
                this.field_111091_i.add(par1ChatMessageComponent);
            }
            else
            {
                this.field_111091_i = Lists.newArrayList(new ChatMessageComponent[] {par1ChatMessageComponent});
            }
        }
        else
        {
            this.field_111091_i = Lists.newArrayList(new ChatMessageComponent[] {new ChatMessageComponent(this), par1ChatMessageComponent});
            this.field_111084_g = null;
            this.field_111090_h = null;
        }

        return this;
    }

    public ChatMessageComponent func_111079_a(String par1Str)
    {
        if (this.field_111084_g == null && this.field_111090_h == null)
        {
            if (this.field_111091_i != null)
            {
                this.field_111091_i.add(func_111066_d(par1Str));
            }
            else
            {
                this.field_111084_g = par1Str;
            }
        }
        else
        {
            this.field_111091_i = Lists.newArrayList(new ChatMessageComponent[] {new ChatMessageComponent(this), func_111066_d(par1Str)});
            this.field_111084_g = null;
            this.field_111090_h = null;
        }

        return this;
    }

    public ChatMessageComponent func_111072_b(String par1Str)
    {
        if (this.field_111084_g == null && this.field_111090_h == null)
        {
            if (this.field_111091_i != null)
            {
                this.field_111091_i.add(func_111077_e(par1Str));
            }
            else
            {
                this.field_111090_h = par1Str;
            }
        }
        else
        {
            this.field_111091_i = Lists.newArrayList(new ChatMessageComponent[] {new ChatMessageComponent(this), func_111077_e(par1Str)});
            this.field_111084_g = null;
            this.field_111090_h = null;
        }

        return this;
    }

    public ChatMessageComponent func_111080_a(String par1Str, Object ... par2ArrayOfObj)
    {
        if (this.field_111084_g == null && this.field_111090_h == null)
        {
            if (this.field_111091_i != null)
            {
                this.field_111091_i.add(func_111082_b(par1Str, par2ArrayOfObj));
            }
            else
            {
                this.field_111090_h = par1Str;
                this.field_111091_i = Lists.newArrayList();
                Object[] var3 = par2ArrayOfObj;
                int var4 = par2ArrayOfObj.length;

                for (int var5 = 0; var5 < var4; ++var5)
                {
                    Object var6 = var3[var5];

                    if (var6 instanceof ChatMessageComponent)
                    {
                        this.field_111091_i.add((ChatMessageComponent)var6);
                    }
                    else
                    {
                        this.field_111091_i.add(func_111066_d(var6.toString()));
                    }
                }
            }
        }
        else
        {
            this.field_111091_i = Lists.newArrayList(new ChatMessageComponent[] {new ChatMessageComponent(this), func_111082_b(par1Str, par2ArrayOfObj)});
            this.field_111084_g = null;
            this.field_111090_h = null;
        }

        return this;
    }

    public String toString()
    {
        return this.func_111068_a(false);
    }

    public String func_111068_a(boolean par1)
    {
        return this.func_111070_a(par1, (EnumChatFormatting)null, false, false, false, false);
    }

    public String func_111070_a(boolean par1, EnumChatFormatting par2EnumChatFormatting, boolean par3, boolean par4, boolean par5, boolean par6)
    {
        StringBuilder var7 = new StringBuilder();
        EnumChatFormatting var8 = this.field_111087_b == null ? par2EnumChatFormatting : this.field_111087_b;
        boolean var9 = this.field_111088_c == null ? par3 : this.field_111088_c.booleanValue();
        boolean var10 = this.field_111085_d == null ? par4 : this.field_111085_d.booleanValue();
        boolean var11 = this.field_111086_e == null ? par5 : this.field_111086_e.booleanValue();
        boolean var12 = this.field_111083_f == null ? par6 : this.field_111083_f.booleanValue();

        if (this.field_111090_h != null)
        {
            if (par1)
            {
                func_111060_a(var7, var8, var9, var10, var11, var12);
            }

            if (this.field_111091_i != null)
            {
                String[] var13 = new String[this.field_111091_i.size()];

                for (int var14 = 0; var14 < this.field_111091_i.size(); ++var14)
                {
                    var13[var14] = ((ChatMessageComponent)this.field_111091_i.get(var14)).func_111070_a(par1, var8, var9, var10, var11, var12);
                }

                var7.append(StatCollector.translateToLocalFormatted(this.field_111090_h, var13));
            }
            else
            {
                var7.append(StatCollector.translateToLocal(this.field_111090_h));
            }
        }
        else if (this.field_111084_g != null)
        {
            if (par1)
            {
                func_111060_a(var7, var8, var9, var10, var11, var12);
            }

            var7.append(this.field_111084_g);
        }
        else
        {
            ChatMessageComponent var16;

            if (this.field_111091_i != null)
            {
                for (Iterator var15 = this.field_111091_i.iterator(); var15.hasNext(); var7.append(var16.func_111070_a(par1, var8, var9, var10, var11, var12)))
                {
                    var16 = (ChatMessageComponent)var15.next();

                    if (par1)
                    {
                        func_111060_a(var7, var8, var9, var10, var11, var12);
                    }
                }
            }
        }

        return var7.toString();
    }

    private static void func_111060_a(StringBuilder par0StringBuilder, EnumChatFormatting par1EnumChatFormatting, boolean par2, boolean par3, boolean par4, boolean par5)
    {
        if (par1EnumChatFormatting != null)
        {
            par0StringBuilder.append(par1EnumChatFormatting);
        }
        else if (par2 || par3 || par4 || par5)
        {
            par0StringBuilder.append(EnumChatFormatting.RESET);
        }

        if (par2)
        {
            par0StringBuilder.append(EnumChatFormatting.BOLD);
        }

        if (par3)
        {
            par0StringBuilder.append(EnumChatFormatting.ITALIC);
        }

        if (par4)
        {
            par0StringBuilder.append(EnumChatFormatting.UNDERLINE);
        }

        if (par5)
        {
            par0StringBuilder.append(EnumChatFormatting.OBFUSCATED);
        }
    }

    public static ChatMessageComponent func_111078_c(String par0Str)
    {
        try
        {
            return (ChatMessageComponent)field_111089_a.fromJson(par0Str, ChatMessageComponent.class);
        }
        catch (Throwable var4)
        {
            CrashReport var2 = CrashReport.makeCrashReport(var4, "Deserializing Message");
            CrashReportCategory var3 = var2.makeCategory("Serialized Message");
            var3.addCrashSection("JSON string", par0Str);
            throw new ReportedException(var2);
        }
    }

    public static ChatMessageComponent func_111066_d(String par0Str)
    {
        ChatMessageComponent var1 = new ChatMessageComponent();
        var1.func_111079_a(par0Str);
        return var1;
    }

    public static ChatMessageComponent func_111077_e(String par0Str)
    {
        ChatMessageComponent var1 = new ChatMessageComponent();
        var1.func_111072_b(par0Str);
        return var1;
    }

    public static ChatMessageComponent func_111082_b(String par0Str, Object ... par1ArrayOfObj)
    {
        ChatMessageComponent var2 = new ChatMessageComponent();
        var2.func_111080_a(par0Str, par1ArrayOfObj);
        return var2;
    }

    public String func_111062_i()
    {
        return field_111089_a.toJson(this);
    }
}
