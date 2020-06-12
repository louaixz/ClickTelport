package me.daz.tasks;
import me.daz.clicktp;
import me.daz.logtask;
import org.bukkit.scheduler.BukkitRunnable;

public class ClearOldRequest extends BukkitRunnable {
    private final logtask plugin;

    public ClearOldRequest(clicktp plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.clearOldRequests();
    }
}
