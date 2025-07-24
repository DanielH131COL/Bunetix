package github.danielh131col.bunetix.commands;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DanielH131COL
 * @created 23/07/2025
 * @project Bunetix
 * @file HubCommand
 */
public class HubCommand extends Command {

    public HubCommand() {
        super("hub", null, "lobby", "main");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            CC.noConsole(sender);
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        List<String> hubList = Bunetix.getInstance().getFileManager().getConfig().getStringList("servers.hubs");

        if (hubList.isEmpty()) {
            CC.console("&4&lBUNETIX &c⇨ No hay hubs configurados.");
            return;
        }

        List<ServerInfo> availableHubs = hubList.stream()
                .map(name -> ProxyServer.getInstance().getServerInfo(name))
                .filter(info -> info != null)
                .collect(Collectors.toList());

        if (availableHubs.isEmpty()) {
            player.sendMessage(new TextComponent(CC.translate(
                    Bunetix.getInstance().getFileManager().getMessages().getString("hub.not-available")
            )));
            return;
        }

        String currentServer = player.getServer().getInfo().getName();
        if (hubList.stream().anyMatch(s -> s.equalsIgnoreCase(currentServer))) {
            player.sendMessage(new TextComponent(CC.translate(
                    Bunetix.getInstance().getFileManager().getMessages().getString("hub.already-there")
            )));
            return;
        }

        Collections.shuffle(availableHubs);
        ServerInfo chosenHub = availableHubs.get(0);

        player.connect(chosenHub);
        player.sendMessage(new TextComponent(CC.translate(Bunetix.getInstance().getFileManager().getMessages().getString("hub.connecting"))));
    }
}