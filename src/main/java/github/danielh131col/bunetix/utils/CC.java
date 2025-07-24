package github.danielh131col.bunetix.utils;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DanielH131COL
 * @created 23/07/2025
 * @project Bunetix
 * @file CC
 */
public class CC {

    private static final String NO_CONSOLE = "&cEste comando no puede usarse desde la consola.";
    private static final String NO_PERMISSION = "&cNo tienes permiso para hacer esto.";
    private static final String NO_ONLINE = "&cEse jugador no está en línea.";

    public static String translate(String text) {
        return IridiumColorAPI.process(ChatColor.translateAlternateColorCodes('&', text));
    }

    public static void msg(CommandSender sender, String message) {
        sender.sendMessage(new TextComponent(translate(message)));
    }

    public static List<String> translate(List<String> texts) {
        return texts.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static void console(String text) {
        ProxyServer.getInstance().getConsole().sendMessage(translate(text));
    }

    public static void noConsole(CommandSender sender) {
        sender.sendMessage(translate("&4&lBUNETIX &c⇨ " + NO_CONSOLE));
    }

    public static void noPermission(CommandSender sender) {
        sender.sendMessage(translate("&4&lBUNETIX &c⇨ " + NO_PERMISSION));
    }

    public static void noOnline(CommandSender sender) {
        sender.sendMessage(translate(NO_ONLINE));
    }
}