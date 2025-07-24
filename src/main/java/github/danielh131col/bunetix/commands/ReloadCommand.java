package github.danielh131col.bunetix.commands;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author DanielH131COL
 * @created 24/07/2025
 * @project Bunetix
 * @file ReloadCommand
 */
public class ReloadCommand extends Command {

    private final Bunetix plugin;

    public ReloadCommand(Bunetix plugin) {
        super("breload");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (!player.hasPermission("bunetix.reload")) {
                CC.noPermission(sender);
                return;
            }
        }

        plugin.getFileManager().loadFiles();

        String msg = plugin.getFileManager().getMessages().getString("reload");
        if (msg == null || msg.isEmpty()) msg = "&a&lBUNETIX ⇨ &a¡All files were reloaded!";

        sender.sendMessage(new TextComponent(CC.translate(msg)));
    }
}