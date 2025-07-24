package github.danielh131col.bunetix.commands;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author DanielH131COL
 * @created 23/07/2025
 * @project Bunetix
 * @file PingCommand
 */
public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            CC.noConsole(sender);
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        String message = Bunetix.getInstance().getFileManager().getMessages().getString("messages.ping");

        if (args.length == 0) {
            int ping = player.getPing();
            player.sendMessage(CC.translate(message.replace("<ping>", String.valueOf(ping))));
        } else {
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

            if (target == null || !target.isConnected()) {
                CC.noOnline(sender);
                return;
            }

            int ping = target.getPing();
            player.sendMessage(CC.translate(message.replace("<ping>", String.valueOf(ping))));
        }
    }
}