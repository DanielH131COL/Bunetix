package github.danielh131col.bunetix.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DanielH131COL
 * @created 23/07/2025
 * @project Bunetix
 * @file CC
 */
public class CC {

    private static final String PREFIX = "&7[&bBunetix&7] ";
    private static final String NO_CONSOLE = "&cEste comando no puede usarse desde la consola.";
    private static final String NO_PERMISSION = "&cNo tienes permiso para hacer esto.";
    private static final String NO_ONLINE = "&cEse jugador no está en línea.";

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> translate(List<String> texts) {
        return texts.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static void console(String text) {
        ProxyServer.getInstance().getConsole().sendMessage(translate(text));
    }

    public static void noConsole(CommandSender sender) {
        sender.sendMessage(translate(PREFIX + NO_CONSOLE));
    }

    public static void noPermission(CommandSender sender) {
        sender.sendMessage(translate(PREFIX + NO_PERMISSION));
    }

    public static void noOnline(CommandSender sender) {
        sender.sendMessage(translate(NO_ONLINE));
    }
}