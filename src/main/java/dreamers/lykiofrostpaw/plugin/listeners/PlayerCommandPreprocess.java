package dreamers.lykiofrostpaw.plugin.listeners;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocess implements Listener {
    private final Telepowort plugin;
    private PlayerConfig pc;
    private String ="back"+"warp"+"home"+y

    public PlayerCommandPreprocess(Telepowort plugin) {
        this.plugin = plugin;
    }

    @EventHandler // Update their config on command invoke
    public void onInvoke(PlayerCommandPreprocessEvent e) {

        pc = new PlayerConfig(e.getPlayer());
        if (proc.toString().contains(e.getMessage())) {
            pc.setLastTeleportLocation(e.getPlayer().getLocation());
        }
    }
}
