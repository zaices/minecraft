package net.minecraft.src;

import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;

class ThreadDownloadImageDataINNER1 extends Thread
{
    final ThreadDownloadImageData field_110932_a;

    ThreadDownloadImageDataINNER1(ThreadDownloadImageData par1ThreadDownloadImageData)
    {
        this.field_110932_a = par1ThreadDownloadImageData;
    }

    public void run()
    {
        HttpURLConnection var1 = null;

        try
        {
            var1 = (HttpURLConnection)(new URL(ThreadDownloadImageData.func_110554_a(this.field_110932_a))).openConnection(Minecraft.getMinecraft().func_110437_J());
            var1.setDoInput(true);
            var1.setDoOutput(false);
            var1.connect();

            if (var1.getResponseCode() / 100 == 2)
            {
                BufferedImage var2 = ImageIO.read(var1.getInputStream());

                if (ThreadDownloadImageData.func_110555_b(this.field_110932_a) != null)
                {
                    var2 = ThreadDownloadImageData.func_110555_b(this.field_110932_a).parseUserSkin(var2);
                }

                this.field_110932_a.func_110556_a(var2);
                return;
            }
        }
        catch (Exception var6)
        {
            var6.printStackTrace();
            return;
        }
        finally
        {
            if (var1 != null)
            {
                var1.disconnect();
            }
        }
    }
}
