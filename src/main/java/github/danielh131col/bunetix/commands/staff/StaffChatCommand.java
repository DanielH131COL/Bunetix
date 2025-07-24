package github.danielh131col.bunetix.commands.staff;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author DanielH131COL
 * @created 23/07/2025
 * @project Bunetix
 * @file StaffChatCommand
 */
public class StaffChatCommand extends Command {

    private final Bunetix plugin;

    public StaffChatCommand(Bunetix plugin) {
        super("sc", "Bunetix.staff", new String[]{"staffchat"});
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) return;
        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length == 0) {
            if (!player.hasPermission("Bunetix.staffchat")) {
                CC.noPermission(player);
                return;
            }

            if (Bunetix.inSc.contains(player)) {
                Bunetix.inSc.remove(player);
                player.sendMessage(new TextComponent(CC.translate(
                        plugin.getFileManager().getMessages().getString("messages.staffchat.disabled")
                )));
            } else {
                Bunetix.inSc.add(player);
                player.sendMessage(new TextComponent(CC.translate(
                        plugin.getFileManager().getMessages().getString("messages.staffchat.enabled")
                )));
            }
            return;
        }

        if (!player.hasPermission("Bunetix.staff")) {
            CC.noPermission(player);
            return;
        }

        String message = String.join(" ", args);
        String format = plugin.getFileManager().getMessages().getString("messages.staffchat.format")
                .replace("%server%", player.getServer().getInfo().getName())
                .replace("%player%", player.getName())
                .replace("%message%", message);

        BaseComponent[] text = TextComponent.fromLegacyText(CC.translate(format));
        for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
            if (staff.hasPermission("Bunetix.staff")) {
                staff.sendMessage(text);
            }
        }
    }
}