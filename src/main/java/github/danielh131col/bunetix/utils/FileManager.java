package github.danielh131col.bunetix.utils;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

/**
 * @author DanielH131COL
 * @created 23/07/2025
 * @project Bunetix
 * @file FileManager
 */
public class FileManager {

    private final Plugin plugin;
    private Configuration config;
    private Configuration messages;

    public FileManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        createFile("config.yml");
        createFile("messages.yml");

        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "config.yml"));
            messages = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "messages.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFile(String name) {
        File file = new File(plugin.getDataFolder(), name);

        if (!file.exists()) {
            plugin.getDataFolder().mkdirs();
            try (InputStream in = plugin.getResourceAsStream(name)) {
                if (in != null) {
                    try (OutputStream out = new FileOutputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = in.read(buffer)) > 0) {
                            out.write(buffer, 0, length);
                        }
                    }
                } else {
                    plugin.getLogger().warning(name + " no fue encontrado en el JAR.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Configuration getConfig() {
        return config;
    }

    public Configuration getMessages() {
        return messages;
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveMessages() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(messages, new File(plugin.getDataFolder(), "messages.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}