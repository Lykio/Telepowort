package dreamers.lykiofrostpaw.plugin.listeners;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class LastLocationListener implements Listener {
    private final Telepowort plugin;
    private PlayerConfig pc;
    private String[] proc = {
            "back",
            "home",
            "warp",
            "tpa",
            "tpy"
    };

    public LastLocationListener(Telepowort plugin) {
        this.plugin = plugin;
    }

    @EventHandler // Creating their config, if it doesn't exist, on LoginEvent
    public void onLogin(PlayerLoginEvent e) {
        if (!pc.exists()) {
            pc.createPlayerConfig();
        }
    }

    @EventHandler // Updating their config on command invoke
    public void onInvoke(PlayerCommandPreprocessEvent e) {
        pc = new PlayerConfig(e.getPlayer());
        if (e.getMessage().equals(proc[0]) || e.getMessage().equals(proc[1]) ||
                e.getMessage().equals(proc[2]) || e.getMessage().equals(proc[3]) ||
                e.getMessage().equals(proc[4])) {
            Location loc = e.getPlayer().getLocation();

            pc.getPlayerConfig().set("Last-Teleport-Location.world", loc.getWorld().toString());
            pc.getPlayerConfig().set("Last-Teleport-Location.x", loc.getBlockX());
            pc.getPlayerConfig().set("Last-Teleport-Location.y", loc.getBlockY());
            pc.getPlayerConfig().set("Last-Teleport-Location.z", loc.getBlockZ());
            pc.getPlayerConfig().set("Last-Teleport-Location.yaw", loc.getYaw());
            pc.getPlayerConfig().set("Last-Teleport-Location.pitch", loc.getPitch());

            pc.savePlayerConfig();
        }
    }
}
