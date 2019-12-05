package dreamers.lykiofrostpaw.plugin;

import dreamers.lykiofrostpaw.plugin.commands.names;
import dreamers.lykiofrostpaw.plugin.commands.home.*;
import dreamers.lykiofrostpaw.plugin.commands.tpa.*;
import dreamers.lykiofrostpaw.plugin.commands.warp.*;
import dreamers.lykiofrostpaw.plugin.commands.miscellaneous.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Telepowort extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("[Telepowort] Enabled!");

        // Home
        this.getCommand(names.command_home).setExecutor(new home(this));
        this.getCommand(names.command_sethome).setExecutor(new sethome(this));
        this.getCommand(names.command_delhome).setExecutor(new delhome(this));

        // TPA
        this.getCommand(names.command_tpa).setExecutor(new tpa(this));
        this.getCommand(names.command_tpaccept).setExecutor(new tpaccept(this));
        this.getCommand(names.command_tpadecline).setExecutor(new tpadecline(this));
        this.getCommand(names.command_tpahere).setExecutor(new tpahere(this));

        // Warp
        this.getCommand(names.command_warp).setExecutor(new warp(this));
        this.getCommand(names.command_setwarp).setExecutor(new setwarp(this));
        this.getCommand(names.command_delwarp).setExecutor(new delwarp(this));

        // Misc
        this.getCommand(names.command_ping).setExecutor(new ping(this));
        this.getCommand(names.command_nickname).setExecutor(new nickname(this));

    }

    @Override
    public void onLoad() {
        System.out.println("[Telepowort] Loaded!");
    }

    @Override
    public void onDisable() {
        System.out.println("[Telepowort] Disabled!");
    }

}
