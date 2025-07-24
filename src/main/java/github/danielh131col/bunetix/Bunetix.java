package github.danielh131col.bunetix;

import net.md_5.bungee.api.plugin.Plugin;

public final class Bunetix extends Plugin {

    private static Bunetix instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
    }

    public static Bunetix getInstance() {
        return instance;
    }

}