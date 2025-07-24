package github.danielh131col.bunetix.listeners;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GeneralListener implements Listener {

    private final Bunetix plugin;
    private final UUID specialUUID = UUID.fromString("74e34777-da27-4729-94ac-d8622407e4a3");

    public GeneralListener(Bunetix plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerKick(ServerKickEvent event) {
        ProxiedPlayer player = event.getPlayer();

        if (event.getKickedFrom() != null && isHub(event.getKickedFrom().getName())) {
            return;
        }

        Configuration config = plugin.getFileManager().getConfig();
        List<String> hubs = config.getStringList("servers.hubs");

        if (hubs == null || hubs.isEmpty()) {
            String noHubs = plugin.getFileManager().getMessages().getString("messages.hub.not-available");
            player.sendMessage(CC.translate(noHubs));
            return;
        }

        String hubName = hubs.get(new Random().nextInt(hubs.size()));
        ServerInfo hub = ProxyServer.getInstance().getServerInfo(hubName);

        if (hub != null) {
            event.setCancelServer(hub);
            event.setCancelled(true);

            String redirect = plugin.getFileManager().getMessages().getString("messages.hub.redirect-kick");
            player.sendMessage(CC.translate(redirect));
        } else {
            String noHubs = plugin.getFileManager().getMessages().getString("messages.hub.not-available");
            player.sendMessage(CC.translate(noHubs));
        }
    }

    private boolean isHub(String serverName) {
        Configuration config = plugin.getFileManager().getConfig();
        List<String> hubs = config.getStringList("servers.hubs");
        return hubs != null && hubs.stream().anyMatch(hub -> hub.equalsIgnoreCase(serverName));
    }

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (!player.getUniqueId().equals(specialUUID)) return;

        player.sendMessage(CC.translate("&7&m--------------------------------------"));
        player.sendMessage(CC.translate("&f¡Estás usando &b&lBUNETIX&f!"));
        player.sendMessage(CC.translate("&7⇨ &fVersión: &b" + plugin.getDescription().getVersion()));
        player.sendMessage(CC.translate("&7⇨ &fAuthor: &bDanielH131COL"));
        player.sendMessage(CC.translate("&7⇨ &fRepositorio: &bgithub.com/danielh131col"));
        player.sendMessage(CC.translate("&7&m--------------------------------------"));
    }
}