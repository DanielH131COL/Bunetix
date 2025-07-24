package github.danielh131col.bunetix.commands.messages;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DanielH131COL
 * @created 23/07/2025
 * @project Bunetix
 * @file MessageCommand
 */
public class MessageCommand extends Command {

    public static final Map<ProxiedPlayer, ProxiedPlayer> lastMessaged = new HashMap<>();

    public MessageCommand() {
        super("msg", null, "tell", "w", "mensaje");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            CC.noConsole(sender);
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length < 2) {
            CC.msg(player, Bunetix.getInstance().getFileManager().getMessages().getString("messages.msg.usage"));
            return;
        }

        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

        if (target == null || target.equals(player)) {
            CC.msg(player, Bunetix.getInstance().getFileManager().getMessages().getString("messages.msg.not-found"));
            return;
        }

        String msg = String.join(" ", args).substring(args[0].length()).trim();

        String toFormat = Bunetix.getInstance().getFileManager().getMessages().getString("messages.msg.to-format")
                .replace("<target>", target.getName())
                .replace("<message>", msg);

        String fromFormat = Bunetix.getInstance().getFileManager().getMessages().getString("messages.msg.from-format")
                .replace("<sender>", player.getName())
                .replace("<message>", msg);

        CC.msg(player, toFormat);
        CC.msg(target, fromFormat);

        lastMessaged.put(player, target);
        lastMessaged.put(target, player);
    }
}