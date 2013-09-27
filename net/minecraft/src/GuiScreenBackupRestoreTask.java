package net.minecraft.src;

class GuiScreenBackupRestoreTask extends TaskLongRunning
{
    private final Backup field_111254_c;

    final GuiScreenBackup field_111255_a;

    private GuiScreenBackupRestoreTask(GuiScreenBackup par1GuiScreenBackup, Backup par2Backup)
    {
        this.field_111255_a = par1GuiScreenBackup;
        this.field_111254_c = par2Backup;
    }

    public void run()
    {
        this.func_96576_b(I18n.func_135053_a("mco.backup.restoring"));

        try
        {
            McoClient var1 = new McoClient(this.func_96578_b().func_110432_I());
            var1.func_111235_c(GuiScreenBackup.func_110367_b(this.field_111255_a), this.field_111254_c.field_110727_a);

            try
            {
                Thread.sleep(1000L);
            }
            catch (InterruptedException var3)
            {
                Thread.currentThread().interrupt();
            }

            this.func_96578_b().displayGuiScreen(GuiScreenBackup.func_130031_d(this.field_111255_a));
        }
        catch (ExceptionMcoService var4)
        {
            GuiScreenBackup.func_130035_e(this.field_111255_a).getLogAgent().logSevere(var4.toString());
            this.func_96575_a(var4.toString());
        }
        catch (Exception var5)
        {
            this.func_96575_a(var5.getLocalizedMessage());
        }
    }

    GuiScreenBackupRestoreTask(GuiScreenBackup par1GuiScreenBackup, Backup par2Backup, GuiScreenBackupDownloadThread par3GuiScreenBackupDownloadThread)
    {
        this(par1GuiScreenBackup, par2Backup);
    }
}
