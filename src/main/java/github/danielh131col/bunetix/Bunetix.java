package github.danielh131col.bunetix;

import github.danielh131col.bunetix.commands.*;
import github.danielh131col.bunetix.commands.messages.MessageCommand;
import github.danielh131col.bunetix.commands.messages.ReplyCommand;
import github.danielh131col.bunetix.utils.CC;
import github.danielh131col.bunetix.utils.FileManager;
import net.md_5.bungee.api.plugin.Plugin;

public final class Bunetix extends Plugin {

    private static Bunetix instance;
    private FileManager fileManager;

    @Override
    public void onEnable() {
        instance = this;

        CC.console("&8&m-----------------------------");
        CC.console("");
        CC.console("          &b&lBUNETIX");
        CC.console("    &e&lENGLISH &7| &e&lINGLES");
        CC.console("&fProxy Core is completely free.");
        CC.console("&fYou can report bugs and more on Discord:");
        CC.console("&e&nhttps://discord.gg/k2KwG37dbG");
        CC.console("");
        CC.console("    &6&lSPANISH &7| &6&lESPAÑOL");
        CC.console("&fCore de Proxy Totalmente gratis.");
        CC.console("&fPuedes reportar tus bugs y mas en Discord:");
        CC.console("&6&nhttps://discord.gg/k2KwG37dbG");
        CC.console("");
        CC.console("&8&m-----------------------------");

        fileManager = new FileManager(this);
        fileManager.setup();

        getProxy().getPluginManager().registerCommand(this, new HubCommand());
        getProxy().getPluginManager().registerCommand(this, new PingCommand());
        getProxy().getPluginManager().registerCommand(this, new MessageCommand());
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand());
    }

    @Override
    public void onDisable() {
    }

    public static Bunetix getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

}