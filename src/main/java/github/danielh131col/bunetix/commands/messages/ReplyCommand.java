package github.danielh131col.bunetix.commands.messages;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author DanielH131COL
 * @created 23/07/2025
 * @project Bunetix
 * @file ReplyCommand
 */
public class ReplyCommand extends Command {

    public ReplyCommand() {
        super("reply", null, "r");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            CC.noConsole(sender);
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length < 1) {
            CC.msg(player, Bunetix.getInstance().getFileManager().getMessages().getString("messages.reply.usage"));
            return;
        }

        ProxiedPlayer target = MessageCommand.lastMessaged.get(player);

        if (target == null || !target.isConnected()) {
            CC.msg(player, Bunetix.getInstance().getFileManager().getMessages().getString("messages.reply.no-target"));
            return;
        }

        String msg = String.join(" ", args);

        String toFormat = Bunetix.getInstance().getFileManager().getMessages().getString("messages.reply.to-format")
                .replace("{target}", target.getName())
                .replace("{message}", msg);

        String fromFormat = Bunetix.getInstance().getFileManager().getMessages().getString("messages.reply.from-format")
                .replace("{sender}", player.getName())
                .replace("{message}", msg);

        CC.msg(player, toFormat);
        CC.msg(target, fromFormat);

        MessageCommand.lastMessaged.put(player, target);
        MessageCommand.lastMessaged.put(target, player);
    }
}