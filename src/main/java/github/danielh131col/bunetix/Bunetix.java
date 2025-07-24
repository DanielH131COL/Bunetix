package github.danielh131col.bunetix;

import github.danielh131col.bunetix.commands.*;
import github.danielh131col.bunetix.commands.messages.MessageCommand;
import github.danielh131col.bunetix.commands.messages.ReplyCommand;
import github.danielh131col.bunetix.listeners.AnnouncementManager;
import github.danielh131col.bunetix.listeners.GeneralListener;
import github.danielh131col.bunetix.utils.CC;
import github.danielh131col.bunetix.utils.FileManager;
import net.md_5.bungee.api.plugin.Plugin;

public final class Bunetix extends Plugin {

    private static Bunetix instance;
    private FileManager fileManager;
    private AnnouncementManager announcementManager;

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
        announcementManager = new AnnouncementManager(this);
        announcementManager.start();

        getProxy().getPluginManager().registerCommand(this, new HubCommand());
        getProxy().getPluginManager().registerCommand(this, new PingCommand());
        getProxy().getPluginManager().registerCommand(this, new MessageCommand());
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand());
        getProxy().getPluginManager().registerCommand(this, new ReloadCommand(this));

        getProxy().getPluginManager().registerListener(this, new GeneralListener(this));
    }

    @Override
    public void onDisable() {
        announcementManager.stop();

        CC.console("&8&m-----------------------------");
        CC.console("");
        CC.console("          &c&lBUNETIX");
        CC.console("     &7Se ha &c&ldesactivado &7el plugin.");
        CC.console("     &7Gracias por usar &bBunetix&7.");
        CC.console("");
        CC.console("&8&m-----------------------------");
    }

    public static Bunetix getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

}