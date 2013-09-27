package net.minecraft.src;

import java.util.Iterator;
import net.minecraft.server.MinecraftServer;

public class ServerCommandManager extends CommandHandler implements IAdminCommand
{
    public ServerCommandManager()
    {
        this.registerCommand(new CommandTime());
        this.registerCommand(new CommandGameMode());
        this.registerCommand(new CommandDifficulty());
        this.registerCommand(new CommandDefaultGameMode());
        this.registerCommand(new CommandKill());
        this.registerCommand(new CommandToggleDownfall());
        this.registerCommand(new CommandWeather());
        this.registerCommand(new CommandXP());
        this.registerCommand(new CommandServerTp());
        this.registerCommand(new CommandGive());
        this.registerCommand(new CommandEffect());
        this.registerCommand(new CommandEnchant());
        this.registerCommand(new CommandServerEmote());
        this.registerCommand(new CommandShowSeed());
        this.registerCommand(new CommandHelp());
        this.registerCommand(new CommandDebug());
        this.registerCommand(new CommandServerMessage());
        this.registerCommand(new CommandServerSay());
        this.registerCommand(new CommandSetSpawnpoint());
        this.registerCommand(new CommandGameRule());
        this.registerCommand(new CommandClearInventory());
        this.registerCommand(new ServerCommandTestFor());
        this.registerCommand(new CommandSpreadPlayers());
        this.registerCommand(new CommandPlaySound());
        this.registerCommand(new ServerCommandScoreboard());

        if (MinecraftServer.getServer().isDedicatedServer())
        {
            this.registerCommand(new CommandServerOp());
            this.registerCommand(new CommandServerDeop());
            this.registerCommand(new CommandServerStop());
            this.registerCommand(new CommandServerSaveAll());
            this.registerCommand(new CommandServerSaveOff());
            this.registerCommand(new CommandServerSaveOn());
            this.registerCommand(new CommandServerBanIp());
            this.registerCommand(new CommandServerPardonIp());
            this.registerCommand(new CommandServerBan());
            this.registerCommand(new CommandServerBanlist());
            this.registerCommand(new CommandServerPardon());
            this.registerCommand(new CommandServerKick());
            this.registerCommand(new CommandServerList());
            this.registerCommand(new CommandServerWhitelist());
        }
        else
        {
            this.registerCommand(new CommandServerPublishLocal());
        }

        CommandBase.setAdminCommander(this);
    }

    /**
     * Sends a message to the admins of the server from a given CommandSender with the given resource string and given
     * extra srings. If the int par2 is even or zero, the original sender is also notified.
     */
    public void notifyAdmins(ICommandSender par1ICommandSender, int par2, String par3Str, Object ... par4ArrayOfObj)
    {
        boolean var5 = true;

        if (par1ICommandSender instanceof TileEntityCommandBlock && !MinecraftServer.getServer().worldServers[0].getGameRules().getGameRuleBooleanValue("commandBlockOutput"))
        {
            var5 = false;
        }

        ChatMessageComponent var6 = ChatMessageComponent.func_111082_b("chat.type.admin", new Object[] {par1ICommandSender.getCommandSenderName(), ChatMessageComponent.func_111082_b(par3Str, par4ArrayOfObj)});
        var6.func_111059_a(EnumChatFormatting.GRAY);
        var6.func_111063_b(Boolean.valueOf(true));

        if (var5)
        {
            Iterator var7 = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();

            while (var7.hasNext())
            {
                EntityPlayerMP var8 = (EntityPlayerMP)var7.next();

                if (var8 != par1ICommandSender && MinecraftServer.getServer().getConfigurationManager().areCommandsAllowed(var8.getCommandSenderName()))
                {
                    var8.sendChatToPlayer(var6);
                }
            }
        }

        if (par1ICommandSender != MinecraftServer.getServer())
        {
            MinecraftServer.getServer().sendChatToPlayer(var6);
        }

        if ((par2 & 1) != 1)
        {
            par1ICommandSender.sendChatToPlayer(ChatMessageComponent.func_111082_b(par3Str, par4ArrayOfObj));
        }
    }
}
