package github.danielh131col.bunetix.listeners;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StaffListener implements Listener {

    private final Set<UUID> firstJoin = new HashSet<>();

    @EventHandler
    public void onChat(ChatEvent e) {
        if (!(e.getSender() instanceof ProxiedPlayer)) return;
        if (e.getMessage().startsWith("/")) return;

        ProxiedPlayer player = (ProxiedPlayer) e.getSender();

        if (Bunetix.inSc.contains(player)) {
            e.setCancelled(true);
            String rawFormat = Bunetix.getInstance().getFileManager().getMessages().getString("messages.staffchat.chat-format");
            String formatted = rawFormat
                    .replace("%server%", player.getServer().getInfo().getName())
                    .replace("%player%", player.getName())
                    .replace("%message%", e.getMessage());

            BaseComponent[] msg = TextComponent.fromLegacyText(CC.translate(formatted));
            for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                if (staff.hasPermission("Bunetix.staff")) {
                    staff.sendMessage(msg);
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        firstJoin.add(player.getUniqueId());

        ProxyServer.getInstance().getScheduler().schedule(
                Bunetix.getInstance(),
                () -> {
                    if (player.isConnected() && player.getServer() != null) {
                        String serverName = player.getServer().getInfo().getName();
                        if (serverName.equalsIgnoreCase("Hub")) {
                            String raw = Bunetix.getInstance().getFileManager().getMessages().getString("messages.staffchat.join-hub");
                            String formatted = raw.replace("%player%", player.getName());

                            BaseComponent[] msg = TextComponent.fromLegacyText(CC.translate(formatted));
                            for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                                if (staff.hasPermission("Bunetix.staff")) {
                                    staff.sendMessage(msg);
                                }
                            }
                        }
                    }
                },
                1, TimeUnit.SECONDS
        );
    }

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent event) {
        ProxiedPlayer player = event.getPlayer();

        if (firstJoin.remove(player.getUniqueId())) return;
        if (player.getServer() == null) return;

        String raw = Bunetix.getInstance().getFileManager().getMessages().getString("messages.staffchat.switch-server");
        String formatted = raw
                .replace("%player%", player.getName())
                .replace("%server%", player.getServer().getInfo().getName());

        BaseComponent[] msg = TextComponent.fromLegacyText(CC.translate(formatted));
        for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
            if (staff.hasPermission("Bunetix.staff")) {
                staff.sendMessage(msg);
            }
        }
    }
}