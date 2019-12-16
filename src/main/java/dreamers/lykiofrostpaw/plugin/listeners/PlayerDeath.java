package dreamers.lykiofrostpaw.plugin.listeners;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    private Telepowort plugin;

    public PlayerDeath(Telepowort plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler // Set player's back location to where they died
    public void playerDied(PlayerDeathEvent e) {
        PlayerConfig playerConfig = new PlayerConfig(e.getEntity());
        playerConfig.setLastTeleportLocation(e.getEntity().getLocation());
    }

    @EventHandler // Let Player know they can back to where they died
    public void playerRespawned(PlayerPostRespawnEvent e) {
        e.getPlayer().sendMessage(ChatColor.YELLOW + "Use " + ChatColor.DARK_RED + "/back" + ChatColor.YELLOW + " to return to where you died.");
    }
}
