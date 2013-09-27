package net.minecraft.src;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class MessageComponentSerializer implements JsonDeserializer, JsonSerializer
{
    public ChatMessageComponent func_111056_a(JsonElement par1JsonElement, Type par2Type, JsonDeserializationContext par3JsonDeserializationContext)
    {
        ChatMessageComponent var4 = new ChatMessageComponent();
        JsonObject var5 = (JsonObject)par1JsonElement;
        JsonElement var6 = var5.get("text");
        JsonElement var7 = var5.get("translate");
        JsonElement var8 = var5.get("color");
        JsonElement var9 = var5.get("bold");
        JsonElement var10 = var5.get("italic");
        JsonElement var11 = var5.get("underlined");
        JsonElement var12 = var5.get("obfuscated");

        if (var8 != null && var8.isJsonPrimitive())
        {
            EnumChatFormatting var13 = EnumChatFormatting.func_96300_b(var8.getAsString());

            if (var13 == null || !var13.func_96302_c())
            {
                throw new JsonParseException("Given color (" + var8.getAsString() + ") is not a valid selection");
            }

            var4.func_111059_a(var13);
        }

        if (var9 != null && var9.isJsonPrimitive())
        {
            var4.func_111071_a(Boolean.valueOf(var9.getAsBoolean()));
        }

        if (var10 != null && var10.isJsonPrimitive())
        {
            var4.func_111063_b(Boolean.valueOf(var10.getAsBoolean()));
        }

        if (var11 != null && var11.isJsonPrimitive())
        {
            var4.func_111081_c(Boolean.valueOf(var11.getAsBoolean()));
        }

        if (var12 != null && var12.isJsonPrimitive())
        {
            var4.func_111061_d(Boolean.valueOf(var12.getAsBoolean()));
        }

        if (var6 != null)
        {
            if (var6.isJsonArray())
            {
                JsonArray var18 = var6.getAsJsonArray();
                Iterator var14 = var18.iterator();

                while (var14.hasNext())
                {
                    JsonElement var15 = (JsonElement)var14.next();

                    if (var15.isJsonPrimitive())
                    {
                        var4.func_111079_a(var15.getAsString());
                    }
                    else if (var15.isJsonObject())
                    {
                        var4.func_111073_a(this.func_111056_a(var15, par2Type, par3JsonDeserializationContext));
                    }
                }
            }
            else if (var6.isJsonPrimitive())
            {
                var4.func_111079_a(var6.getAsString());
            }
        }
        else if (var7 != null && var7.isJsonPrimitive())
        {
            JsonElement var17 = var5.get("using");

            if (var17 != null)
            {
                if (var17.isJsonArray())
                {
                    ArrayList var20 = Lists.newArrayList();
                    Iterator var19 = var17.getAsJsonArray().iterator();

                    while (var19.hasNext())
                    {
                        JsonElement var16 = (JsonElement)var19.next();

                        if (var16.isJsonPrimitive())
                        {
                            var20.add(var16.getAsString());
                        }
                        else if (var16.isJsonObject())
                        {
                            var20.add(this.func_111056_a(var16, par2Type, par3JsonDeserializationContext));
                        }
                    }

                    var4.func_111080_a(var7.getAsString(), var20.toArray());
                }
                else if (var17.isJsonPrimitive())
                {
                    var4.func_111080_a(var7.getAsString(), new Object[] {var17.getAsString()});
                }
            }
            else
            {
                var4.func_111072_b(var7.getAsString());
            }
        }

        return var4;
    }

    public JsonElement func_111055_a(ChatMessageComponent par1ChatMessageComponent, Type par2Type, JsonSerializationContext par3JsonSerializationContext)
    {
        JsonObject var4 = new JsonObject();

        if (par1ChatMessageComponent.func_111065_a() != null)
        {
            var4.addProperty("color", par1ChatMessageComponent.func_111065_a().func_96297_d());
        }

        if (par1ChatMessageComponent.func_111058_b() != null)
        {
            var4.addProperty("bold", par1ChatMessageComponent.func_111058_b());
        }

        if (par1ChatMessageComponent.func_111064_c() != null)
        {
            var4.addProperty("italic", par1ChatMessageComponent.func_111064_c());
        }

        if (par1ChatMessageComponent.func_111067_d() != null)
        {
            var4.addProperty("underlined", par1ChatMessageComponent.func_111067_d());
        }

        if (par1ChatMessageComponent.func_111076_e() != null)
        {
            var4.addProperty("obfuscated", par1ChatMessageComponent.func_111076_e());
        }

        if (par1ChatMessageComponent.func_111075_f() != null)
        {
            var4.addProperty("text", par1ChatMessageComponent.func_111075_f());
        }
        else if (par1ChatMessageComponent.func_111074_g() != null)
        {
            var4.addProperty("translate", par1ChatMessageComponent.func_111074_g());

            if (par1ChatMessageComponent.func_111069_h() != null && !par1ChatMessageComponent.func_111069_h().isEmpty())
            {
                var4.add("using", this.func_111057_b(par1ChatMessageComponent, par2Type, par3JsonSerializationContext));
            }
        }
        else if (par1ChatMessageComponent.func_111069_h() != null && !par1ChatMessageComponent.func_111069_h().isEmpty())
        {
            var4.add("text", this.func_111057_b(par1ChatMessageComponent, par2Type, par3JsonSerializationContext));
        }

        return var4;
    }

    private JsonArray func_111057_b(ChatMessageComponent par1ChatMessageComponent, Type par2Type, JsonSerializationContext par3JsonSerializationContext)
    {
        JsonArray var4 = new JsonArray();
        Iterator var5 = par1ChatMessageComponent.func_111069_h().iterator();

        while (var5.hasNext())
        {
            ChatMessageComponent var6 = (ChatMessageComponent)var5.next();

            if (var6.func_111075_f() != null)
            {
                var4.add(new JsonPrimitive(var6.func_111075_f()));
            }
            else
            {
                var4.add(this.func_111055_a(var6, par2Type, par3JsonSerializationContext));
            }
        }

        return var4;
    }

    public Object deserialize(JsonElement par1JsonElement, Type par2Type, JsonDeserializationContext par3JsonDeserializationContext)
    {
        return this.func_111056_a(par1JsonElement, par2Type, par3JsonDeserializationContext);
    }

    public JsonElement serialize(Object par1Obj, Type par2Type, JsonSerializationContext par3JsonSerializationContext)
    {
        return this.func_111055_a((ChatMessageComponent)par1Obj, par2Type, par3JsonSerializationContext);
    }
}
