package dreamers.lykiofrostpaw.plugin.listeners;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {
    private final Telepowort plugin;

    public PlayerLoginListener(Telepowort plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        PlayerConfig pc = new PlayerConfig(event.getPlayer());
        if (!pc.exists()) {
            plugin.getServer().broadcastMessage(
                    ChatColor.YELLOW + "Welcome " + ChatColor.GOLD +
                            event.getPlayer().getName() + ChatColor.YELLOW +
                            " to the server!" + ChatColor.DARK_RED + " <3");
            pc.savePlayerConfig();
        }
    }
}
