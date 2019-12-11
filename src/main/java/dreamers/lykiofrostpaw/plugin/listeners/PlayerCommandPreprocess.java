package dreamers.lykiofrostpaw.plugin.listeners;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.List;

public class PlayerCommandPreprocess implements Listener {
    private final Telepowort plugin;
    private PlayerConfig pc;
    private List procs = Arrays.asList("back", "back", "warp", "w", "home", "h", "tpa", "tpaccept");

    public PlayerCommandPreprocess(Telepowort plugin) {
        this.plugin = plugin;
    }

    @EventHandler // Update their config on command invoke
    public void onInvoke(PlayerCommandPreprocessEvent e) {

        pc = new PlayerConfig(e.getPlayer());
        if (procs.contains(e.getMessage())) {
            pc.setLastTeleportLocation(e.getPlayer().getLocation());
        }
    }
}
