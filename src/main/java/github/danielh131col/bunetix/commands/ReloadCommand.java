package github.danielh131col.bunetix.commands;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.CommandSender;
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
        super("breload", "bunetix.reload", "breload");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        plugin.getFileManager().loadFiles();
        sender.sendMessage(CC.translate("&a&lBUNETIX ⇨ ¡Reload completado!"));
    }
}
