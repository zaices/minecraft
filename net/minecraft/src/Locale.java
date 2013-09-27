package net.minecraft.src;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class Locale
{
    private static final Splitter field_135030_b = Splitter.on('=').limit(2);
    private static final Pattern field_135031_c = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
    Map field_135032_a = Maps.newHashMap();
    private boolean field_135029_d;

    public synchronized void func_135022_a(ResourceManager par1ResourceManager, List par2List)
    {
        this.field_135032_a.clear();
        Iterator var3 = par2List.iterator();

        while (var3.hasNext())
        {
            String var4 = (String)var3.next();
            String var5 = String.format("lang/%s.lang", new Object[] {var4});
            Iterator var6 = par1ResourceManager.func_135055_a().iterator();

            while (var6.hasNext())
            {
                String var7 = (String)var6.next();

                try
                {
                    this.func_135028_a(par1ResourceManager.func_135056_b(new ResourceLocation(var7, var5)));
                }
                catch (IOException var9)
                {
                    ;
                }
            }
        }

        this.func_135024_b();
    }

    public boolean func_135025_a()
    {
        return this.field_135029_d;
    }

    private void func_135024_b()
    {
        this.field_135029_d = false;
        Iterator var1 = this.field_135032_a.values().iterator();

        while (var1.hasNext())
        {
            String var2 = (String)var1.next();

            for (int var3 = 0; var3 < var2.length(); ++var3)
            {
                if (var2.charAt(var3) >= 256)
                {
                    this.field_135029_d = true;
                    break;
                }
            }
        }
    }

    private void func_135028_a(List par1List) throws IOException
    {
        Iterator var2 = par1List.iterator();

        while (var2.hasNext())
        {
            Resource var3 = (Resource)var2.next();
            this.func_135021_a(var3.func_110527_b());
        }
    }

    private void func_135021_a(InputStream par1InputStream) throws IOException
    {
        Iterator var2 = IOUtils.readLines(par1InputStream, Charsets.UTF_8).iterator();

        while (var2.hasNext())
        {
            String var3 = (String)var2.next();

            if (!var3.isEmpty() && var3.charAt(0) != 35)
            {
                String[] var4 = (String[])Iterables.toArray(field_135030_b.split(var3), String.class);

                if (var4 != null && var4.length == 2)
                {
                    String var5 = var4[0];
                    String var6 = field_135031_c.matcher(var4[1]).replaceAll("%$1s");
                    this.field_135032_a.put(var5, var6);
                }
            }
        }
    }

    private String func_135026_c(String par1Str)
    {
        String var2 = (String)this.field_135032_a.get(par1Str);
        return var2 == null ? par1Str : var2;
    }

    public String func_135027_a(String par1Str)
    {
        return this.func_135026_c(par1Str);
    }

    public String func_135023_a(String par1Str, Object[] par2ArrayOfObj)
    {
        String var3 = this.func_135026_c(par1Str);

        try
        {
            return String.format(var3, par2ArrayOfObj);
        }
        catch (IllegalFormatException var5)
        {
            return "Format error: " + var3;
        }
    }
}
