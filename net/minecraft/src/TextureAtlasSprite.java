package net.minecraft.src;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TextureAtlasSprite implements Icon
{
    private final String field_110984_i;
    protected List field_110976_a = Lists.newArrayList();
    private AnimationMetadataSection field_110982_k;
    protected boolean field_130222_e;
    protected int field_110975_c;
    protected int field_110974_d;
    protected int field_130223_c;
    protected int field_130224_d;
    private float field_110979_l;
    private float field_110980_m;
    private float field_110977_n;
    private float field_110978_o;
    protected int field_110973_g;
    protected int field_110983_h;
    private int indexInMap = -1;
    public float baseU;
    public float baseV;
    public int sheetWidth;
    public int sheetHeight;
    private boolean mipmapActive = false;
    public int glOwnTextureId = -1;
    private int uploadedFrameIndex = -1;
    private int uploadedOwnFrameIndex = -1;
    public IntBuffer[] frameBuffers;
    public Mipmaps[] frameMipmaps;

    protected TextureAtlasSprite(String par1Str)
    {
        this.field_110984_i = par1Str;
    }

    public void func_110971_a(int par1, int par2, int par3, int par4, boolean par5)
    {
        this.field_110975_c = par3;
        this.field_110974_d = par4;
        this.field_130222_e = par5;
        float var6 = (float)(0.009999999776482582D / (double)par1);
        float var7 = (float)(0.009999999776482582D / (double)par2);
        this.field_110979_l = (float)par3 / (float)((double)par1) + var6;
        this.field_110980_m = (float)(par3 + this.field_130223_c) / (float)((double)par1) - var6;
        this.field_110977_n = (float)par4 / (float)par2 + var7;
        this.field_110978_o = (float)(par4 + this.field_130224_d) / (float)par2 - var7;
        this.baseU = Math.min(this.field_110979_l, this.field_110980_m);
        this.baseV = Math.min(this.field_110977_n, this.field_110978_o);
    }

    public void copyFrom(TextureAtlasSprite par1TextureAtlasSprite)
    {
        this.field_110975_c = par1TextureAtlasSprite.field_110975_c;
        this.field_110974_d = par1TextureAtlasSprite.field_110974_d;
        this.field_130223_c = par1TextureAtlasSprite.field_130223_c;
        this.field_130224_d = par1TextureAtlasSprite.field_130224_d;
        this.field_130222_e = par1TextureAtlasSprite.field_130222_e;
        this.field_110979_l = par1TextureAtlasSprite.field_110979_l;
        this.field_110980_m = par1TextureAtlasSprite.field_110980_m;
        this.field_110977_n = par1TextureAtlasSprite.field_110977_n;
        this.field_110978_o = par1TextureAtlasSprite.field_110978_o;
        this.baseU = Math.min(this.field_110979_l, this.field_110980_m);
        this.baseV = Math.min(this.field_110977_n, this.field_110978_o);
    }

    public int func_130010_a()
    {
        return this.field_110975_c;
    }

    public int func_110967_i()
    {
        return this.field_110974_d;
    }

    /**
     * Returns the X position of this icon on its texture sheet, in pixels.
     */
    public int getOriginX()
    {
        return this.field_130223_c;
    }

    /**
     * Returns the Y position of this icon on its texture sheet, in pixels.
     */
    public int getOriginY()
    {
        return this.field_130224_d;
    }

    /**
     * Returns the minimum U coordinate to use when rendering with this icon.
     */
    public float getMinU()
    {
        return this.field_110979_l;
    }

    /**
     * Returns the maximum U coordinate to use when rendering with this icon.
     */
    public float getMaxU()
    {
        return this.field_110980_m;
    }

    /**
     * Gets a U coordinate on the icon. 0 returns uMin and 16 returns uMax. Other arguments return in-between values.
     */
    public float getInterpolatedU(double par1)
    {
        float var3 = this.field_110980_m - this.field_110979_l;
        return this.field_110979_l + var3 * (float)par1 / 16.0F;
    }

    /**
     * Returns the minimum V coordinate to use when rendering with this icon.
     */
    public float getMinV()
    {
        return this.field_110977_n;
    }

    /**
     * Returns the maximum V coordinate to use when rendering with this icon.
     */
    public float getMaxV()
    {
        return this.field_110978_o;
    }

    /**
     * Gets a V coordinate on the icon. 0 returns vMin and 16 returns vMax. Other arguments return in-between values.
     */
    public float getInterpolatedV(double par1)
    {
        float var3 = this.field_110978_o - this.field_110977_n;
        return this.field_110977_n + var3 * ((float)par1 / 16.0F);
    }

    public String getIconName()
    {
        return this.field_110984_i;
    }

    public void updateAnimation()
    {
        ++this.field_110983_h;

        if (this.field_110983_h >= this.field_110982_k.func_110472_a(this.field_110973_g))
        {
            int var1 = this.field_110982_k.func_110468_c(this.field_110973_g);
            int var2 = this.field_110982_k.func_110473_c() == 0 ? this.field_110976_a.size() : this.field_110982_k.func_110473_c();
            this.field_110973_g = (this.field_110973_g + 1) % var2;
            this.field_110983_h = 0;
            int var3 = this.field_110982_k.func_110468_c(this.field_110973_g);

            if (var1 != var3 && var3 >= 0 && var3 < this.field_110976_a.size())
            {
                this.uploadFrameTexture(var3, this.field_110975_c, this.field_110974_d);
                this.uploadedFrameIndex = var3;
            }
        }
    }

    public int[] func_110965_a(int par1)
    {
        return (int[])((int[])this.field_110976_a.get(par1));
    }

    public int func_110970_k()
    {
        return this.field_110976_a.size();
    }

    public void func_110966_b(int par1)
    {
        this.field_130223_c = par1;
    }

    public void func_110969_c(int par1)
    {
        this.field_130224_d = par1;
    }

    public void func_130100_a(Resource par1Resource) throws IOException
    {
        this.func_130102_n();
        InputStream var2 = par1Resource.func_110527_b();
        AnimationMetadataSection var3 = (AnimationMetadataSection)par1Resource.func_110526_a("animation");
        BufferedImage var4 = ImageIO.read(var2);
        this.field_130224_d = var4.getHeight();
        this.field_130223_c = var4.getWidth();
        int[] var5 = new int[this.field_130224_d * this.field_130223_c];
        var4.getRGB(0, 0, this.field_130223_c, this.field_130224_d, var5, 0, this.field_130223_c);
        int var6;

        if (var3 == null)
        {
            if (this.field_130224_d != this.field_130223_c)
            {
                throw new RuntimeException("broken aspect ratio and not an animation");
            }

            this.field_110976_a.add(var5);
        }
        else
        {
            var6 = this.field_130224_d / this.field_130223_c;
            int var7 = this.field_130223_c;
            int var8 = this.field_130223_c;
            this.field_130224_d = this.field_130223_c;
            int var9;

            if (var3.func_110473_c() > 0)
            {
                Iterator var10 = var3.func_130073_e().iterator();

                while (var10.hasNext())
                {
                    var9 = ((Integer)var10.next()).intValue();

                    if (var9 >= var6)
                    {
                        throw new RuntimeException("invalid frameindex " + var9);
                    }

                    this.func_130099_d(var9);
                    this.field_110976_a.set(var9, func_130101_a(var5, var7, var8, var9));
                }

                this.field_110982_k = var3;
            }
            else
            {
                ArrayList var11 = Lists.newArrayList();

                for (var9 = 0; var9 < var6; ++var9)
                {
                    this.field_110976_a.add(func_130101_a(var5, var7, var8, var9));
                    var11.add(new AnimationFrame(var9, -1));
                }

                this.field_110982_k = new AnimationMetadataSection(var11, this.field_130223_c, this.field_130224_d, var3.func_110469_d());
            }
        }

        for (var6 = 0; var6 < this.field_110976_a.size(); ++var6)
        {
            if (this.field_110976_a.get(var6) == null)
            {
                this.field_110976_a.set(var6, new int[this.field_130223_c * this.field_130224_d]);
            }

            int[] var12 = (int[])((int[])this.field_110976_a.get(var6));
            this.fixTransparentColor(var12);
        }
    }

    private void func_130099_d(int par1)
    {
        if (this.field_110976_a.size() <= par1)
        {
            for (int var2 = this.field_110976_a.size(); var2 <= par1; ++var2)
            {
                this.field_110976_a.add((Object)null);
            }
        }
    }

    private static int[] func_130101_a(int[] par0ArrayOfInteger, int par1, int par2, int par3)
    {
        int[] var4 = new int[par1 * par2];
        System.arraycopy(par0ArrayOfInteger, par3 * var4.length, var4, 0, var4.length);
        return var4;
    }

    public void func_130103_l()
    {
        this.field_110976_a.clear();
    }

    public boolean func_130098_m()
    {
        return this.field_110982_k != null;
    }

    public void func_110968_a(List par1List)
    {
        this.field_110976_a = par1List;

        for (int var2 = 0; var2 < this.field_110976_a.size(); ++var2)
        {
            if (this.field_110976_a.get(var2) == null)
            {
                this.field_110976_a.set(var2, new int[this.field_130223_c * this.field_130224_d]);
            }
        }
    }

    private void func_130102_n()
    {
        this.field_110982_k = null;
        this.func_110968_a(Lists.newArrayList());
        this.field_110973_g = 0;
        this.field_110983_h = 0;
        this.deleteOwnTexture();
        this.uploadedFrameIndex = -1;
        this.uploadedOwnFrameIndex = -1;
        this.frameBuffers = null;
        this.frameMipmaps = null;
    }

    public String toString()
    {
        return "TextureAtlasSprite{name=\'" + this.field_110984_i + '\'' + ", frameCount=" + this.field_110976_a.size() + ", rotated=" + this.field_130222_e + ", x=" + this.field_110975_c + ", y=" + this.field_110974_d + ", height=" + this.field_130224_d + ", width=" + this.field_130223_c + ", u0=" + this.field_110979_l + ", u1=" + this.field_110980_m + ", v0=" + this.field_110977_n + ", v1=" + this.field_110978_o + '}';
    }

    public int getWidth()
    {
        return this.field_130223_c;
    }

    public int getHeight()
    {
        return this.field_130224_d;
    }

    public int getIndexInMap()
    {
        return this.indexInMap;
    }

    public void setIndexInMap(int indexInMap)
    {
        this.indexInMap = indexInMap;
    }

    public void setMipmapActive(boolean mipmapActive)
    {
        this.mipmapActive = mipmapActive;
        this.frameMipmaps = null;
    }

    public boolean load(ResourceManager manager, ResourceLocation location) throws IOException
    {
        this.func_130100_a(manager.func_110536_a(location));
        return true;
    }

    public void uploadFrameTexture()
    {
        this.uploadFrameTexture(this.field_110973_g, this.field_110975_c, this.field_110974_d);
    }

    public void uploadFrameTexture(int frameIndex, int xPos, int yPos)
    {
        int frameCount = this.func_110970_k();

        if (frameIndex >= 0 && frameIndex < frameCount)
        {
            if (frameCount <= 1)
            {
                int[] buf = this.func_110965_a(frameIndex);
                IntBuffer data = TextureUtils.getStaticBuffer(this.field_130223_c, this.field_130224_d);
                data.clear();
                data.put(buf);
                data.clear();
                GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 0, xPos, yPos, this.field_130223_c, this.field_130224_d, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, data);
            }
            else
            {
                if (this.frameBuffers == null)
                {
                    this.frameBuffers = new IntBuffer[frameCount];

                    for (int var8 = 0; var8 < this.frameBuffers.length; ++var8)
                    {
                        int[] var10 = this.func_110965_a(var8);
                        IntBuffer buf1 = GLAllocation.createDirectIntBuffer(var10.length);
                        buf1.put(var10);
                        buf1.clear();
                        this.frameBuffers[var8] = buf1;
                    }
                }

                IntBuffer var9 = this.frameBuffers[frameIndex];
                var9.clear();
                GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 0, xPos, yPos, this.field_130223_c, this.field_130224_d, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, var9);
            }

            if (this.mipmapActive)
            {
                this.uploadFrameMipmaps(frameIndex, xPos, yPos);
            }
        }
    }

    private void uploadFrameMipmaps(int frameIndex, int xPos, int yPos)
    {
        if (this.mipmapActive)
        {
            int frameCount = this.func_110970_k();

            if (frameIndex >= 0 && frameIndex < frameCount)
            {
                if (frameCount <= 1)
                {
                    int[] var8 = this.func_110965_a(frameIndex);
                    boolean var10 = false;
                    Mipmaps var11 = new Mipmaps(this.field_110984_i, this.field_130223_c, this.field_130224_d, var8, var10);
                    var11.uploadMipmaps(xPos, yPos);
                }
                else
                {
                    if (this.frameMipmaps == null)
                    {
                        this.frameMipmaps = new Mipmaps[frameCount];

                        for (int m = 0; m < this.frameMipmaps.length; ++m)
                        {
                            int[] data = this.func_110965_a(m);
                            boolean direct = frameCount > 0;
                            this.frameMipmaps[m] = new Mipmaps(this.field_110984_i, this.field_130223_c, this.field_130224_d, data, direct);
                        }
                    }

                    Mipmaps var9 = this.frameMipmaps[frameIndex];
                    var9.uploadMipmaps(xPos, yPos);
                }
            }
        }
    }

    public void bindOwnTexture()
    {
        if (this.glOwnTextureId < 0)
        {
            this.glOwnTextureId = TextureUtil.func_110996_a();
            TextureUtil.func_110991_a(this.glOwnTextureId, this.field_130223_c, this.field_130224_d);
            TextureUtils.setupTexture(this.field_130223_c, this.field_130224_d, 1, this.mipmapActive);
        }

        TextureUtil.bindTexture(this.glOwnTextureId);
    }

    public void bindUploadOwnTexture()
    {
        this.bindOwnTexture();
        this.uploadFrameTexture(this.field_110973_g, 0, 0);
    }

    public void uploadOwnAnimation()
    {
        if (this.uploadedFrameIndex != this.uploadedOwnFrameIndex)
        {
            TextureUtil.bindTexture(this.glOwnTextureId);
            this.uploadFrameTexture(this.uploadedFrameIndex, 0, 0);
            this.uploadedOwnFrameIndex = this.uploadedFrameIndex;
        }
    }

    public void deleteOwnTexture()
    {
        if (this.glOwnTextureId >= 0)
        {
            GL11.glDeleteTextures(this.glOwnTextureId);
            this.glOwnTextureId = -1;
        }
    }

    private void fixTransparentColor(int[] data)
    {
        long redSum = 0L;
        long greenSum = 0L;
        long blueSum = 0L;
        long count = 0L;
        int redAvg;
        int greenAvg;
        int blueAvg;
        int i;
        int col;
        int alpha;

        for (redAvg = 0; redAvg < data.length; ++redAvg)
        {
            greenAvg = data[redAvg];
            blueAvg = greenAvg >> 24 & 255;

            if (blueAvg != 0)
            {
                i = greenAvg >> 16 & 255;
                col = greenAvg >> 8 & 255;
                alpha = greenAvg & 255;
                redSum += (long)i;
                greenSum += (long)col;
                blueSum += (long)alpha;
                ++count;
            }
        }

        if (count > 0L)
        {
            redAvg = (int)(redSum / count);
            greenAvg = (int)(greenSum / count);
            blueAvg = (int)(blueSum / count);

            for (i = 0; i < data.length; ++i)
            {
                col = data[i];
                alpha = col >> 24 & 255;

                if (alpha == 0)
                {
                    data[i] = redAvg << 16 | greenAvg << 8 | blueAvg;
                }
            }
        }
    }
}
