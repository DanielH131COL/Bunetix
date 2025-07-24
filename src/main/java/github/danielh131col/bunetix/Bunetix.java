package github.danielh131col.bunetix;

import github.danielh131col.bunetix.utils.FileManager;
import net.md_5.bungee.api.plugin.Plugin;

public final class Bunetix extends Plugin {

    private static Bunetix instance;
    private FileManager fileManager;

    @Override
    public void onEnable() {
        instance = this;

        fileManager = new FileManager(this);
        fileManager.setup();
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