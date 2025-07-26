package github.danielh131col.bunetix.commands;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.util.HashMap;
import java.util.Set;

/**
 * @author DanielH131COL
 * @created 26/07/2025
 * @project Bunetix
 * @file StreamCommand
 */
public class StreamCommand extends Command {

    private final Bunetix plugin;
    private final HashMap<String, Long> cooldowns;

    public StreamCommand(Bunetix plugin) {
        super("stream", "stream.use");
        this.plugin = plugin;
        this.cooldowns = new HashMap<>();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            CC.noOnline(sender);
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        long currentTime = System.currentTimeMillis();
        long cooldownTime = plugin.getFileManager().getConfig().getInt("stream.cooldown-seconds") * 1000L;
        Long lastUsed = cooldowns.get(player.getUniqueId().toString());

        if (lastUsed != null && (currentTime - lastUsed) < cooldownTime) {
            long timeLeftMillis = cooldownTime - (currentTime - lastUsed);
            long minutes = timeLeftMillis / 60000;
            long seconds = (timeLeftMillis % 60000) / 1000;
            String timeLeft = (minutes > 0 ? minutes + "m " : "") + (seconds > 0 ? seconds + "s" : "");
            String msg = plugin.getFileManager().getMessages().getString("messages.stream.cooldown").replace("%time%", timeLeft);
            player.sendMessage(new TextComponent(CC.translate(msg)));
            return;
        }

        if (args.length != 1) {
            sender.sendMessage(new TextComponent(CC.translate(plugin.getFileManager().getMessages().getString("messages.stream.usage"))));
            return;
        }

        String link = args[0];
        String platform = detectPlatformFromLink(link);

        if (platform == null) {
            player.sendMessage(new TextComponent(CC.translate(plugin.getFileManager().getMessages().getString("messages.stream.unsupported"))));
            return;
        }

        String color = plugin.getFileManager().getConfig().getString("stream.supported-platforms." + platform + ".color", "&f");
        String icon = plugin.getFileManager().getConfig().getString("stream.supported-platforms." + platform + ".icon", "&f•");

        TextComponent message = new TextComponent();
        message.addExtra(new TextComponent(CC.translate(plugin.getFileManager().getMessages().getString("messages.stream.header") + "\n")));

        message.addExtra(new TextComponent(CC.translate(
                plugin.getFileManager().getMessages().getString("messages.stream.line")
                        .replace("%color%", color)
                        .replace("%icon%", icon)
                        .replace("%platform%", platform.toUpperCase())
        ) + "\n"));

        message.addExtra(new TextComponent(CC.translate(
                plugin.getFileManager().getMessages().getString("messages.stream.status")
                        .replace("%color%", color)
                        .replace("%player%", player.getName())
                        .replace("%platform%", platform)
        ) + "\n"));

        TextComponent linkComponent = new TextComponent(CC.translate(color + link));
        linkComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link));
        linkComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new Text(CC.translate(plugin.getFileManager().getMessages().getString("messages.stream.link-hover")))));

        message.addExtra(linkComponent);
        message.addExtra(new TextComponent("\n"));
        message.addExtra(new TextComponent(CC.translate(plugin.getFileManager().getMessages().getString("messages.stream.footer"))));

        for (ProxiedPlayer p : plugin.getProxy().getPlayers()) {
            p.sendMessage(message);
        }

        cooldowns.put(player.getUniqueId().toString(), currentTime);
    }

    private String detectPlatformFromLink(String link) {
        Configuration section = plugin.getFileManager().getConfig().getSection("stream.supported-platforms");

        if (section == null) {
            plugin.getLogger().warning("La sección 'stream.supported-platforms' no existe en config.yml");
            return null;
        }

        Set<String> platforms = (Set<String>) section.getKeys();
        for (String platform : platforms) {
            String regex = section.getString(platform + ".regex");
            if (regex != null && link.toLowerCase().matches(regex)) {
                return platform;
            }
        }
        return null;
    }
}