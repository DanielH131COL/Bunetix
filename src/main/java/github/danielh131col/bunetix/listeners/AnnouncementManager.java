package github.danielh131col.bunetix.listeners;

import github.danielh131col.bunetix.Bunetix;
import github.danielh131col.bunetix.utils.CC;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import net.md_5.bungee.config.Configuration;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author DanielH131COL
 * @created 23/07/2025
 * @project Bunetix
 * @file AnnouncementManager
 */
public class AnnouncementManager {

    private final Bunetix plugin;
    private final Map<String, ScheduledTask> tasks = new HashMap<>();

    public AnnouncementManager(Bunetix plugin) {
        this.plugin = plugin;
    }

    public void start() {
        Configuration announcements = plugin.getFileManager().getConfig().getSection("announcements");
        if (announcements == null) return;

        for (String key : announcements.getKeys()) {
            Configuration section = announcements.getSection(key);

            List<String> lines = section.getStringList("lines");
            int interval = section.getInt("interval", 60);

            ScheduledTask task = ProxyServer.getInstance().getScheduler().schedule(plugin, () -> {
                lines.forEach(line -> ProxyServer.getInstance().broadcast(CC.translate(line)));
            }, interval, interval, TimeUnit.SECONDS);

            tasks.put(key, task);
        }
    }

    public void stop() {
        for (ScheduledTask task : tasks.values()) {
            task.cancel();
        }
        tasks.clear();
    }
}