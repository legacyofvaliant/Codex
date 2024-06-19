package cx.ajneb97.tasks;

import cx.ajneb97.Codex;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class SavePlayersTask {

    private Codex plugin;
    private boolean end;

    public SavePlayersTask(Codex plugin) {
        this.plugin = plugin;
        this.end = false;
    }

    public void start() {
        FileConfiguration config = plugin.getConfig();
        int time = config.getInt("data_auto_save_time");
        new BukkitRunnable() {
            @Override
            public void run() {
                if (end) {
                    this.cancel();
                } else {
                    execute();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 20L * time, 20L * time);
    }

    public void end() {
        end = true;
    }

    public void execute() {
        plugin.getConfigsManager().getPlayerConfigsManager().guardarJugadores();
    }
}
