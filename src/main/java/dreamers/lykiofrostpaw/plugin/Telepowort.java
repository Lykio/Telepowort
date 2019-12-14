package dreamers.lykiofrostpaw.plugin;

import dreamers.lykiofrostpaw.plugin.commands.home.delhome;
import dreamers.lykiofrostpaw.plugin.commands.home.home;
import dreamers.lykiofrostpaw.plugin.commands.home.sethome;
import dreamers.lykiofrostpaw.plugin.commands.miscellaneous.back;
import dreamers.lykiofrostpaw.plugin.commands.miscellaneous.nickname;
import dreamers.lykiofrostpaw.plugin.commands.miscellaneous.ping;
import dreamers.lykiofrostpaw.plugin.commands.miscellaneous.treload;
import dreamers.lykiofrostpaw.plugin.commands.tpa.tpa;
import dreamers.lykiofrostpaw.plugin.commands.warp.WarpConfig;
import dreamers.lykiofrostpaw.plugin.commands.warp.delwarp;
import dreamers.lykiofrostpaw.plugin.commands.warp.setwarp;
import dreamers.lykiofrostpaw.plugin.commands.warp.warp;
import dreamers.lykiofrostpaw.plugin.listeners.PlayerLogin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public final class Telepowort extends JavaPlugin {

    @Override
    public void onEnable() {

        // Prevent crashing because code is trash
        File userspath = new File(getDataFolder() + "\\users");
        if (!userspath.exists()) userspath.mkdirs();
        WarpConfig warpConfig = new WarpConfig(this);
        if (!warpConfig.exists()) warpConfig.createWarpConfig();

        // Home
        this.getCommand("home").setExecutor(new home(this));
        this.getCommand("sethome").setExecutor(new sethome(this));
        this.getCommand("delhome").setExecutor(new delhome(this));

        // TPA
        this.getCommand("tpa").setExecutor(new tpa(this));

        // Warp
        this.getCommand("warp").setExecutor(new warp(this));
        this.getCommand("setwarp").setExecutor(new setwarp(this));
        this.getCommand("delwarp").setExecutor(new delwarp(this));

        // Misc
        this.getCommand("ping").setExecutor(new ping(this));
        this.getCommand("nickname").setExecutor(new nickname(this));
        this.getCommand("treload").setExecutor(new treload(this));
        this.getCommand("back").setExecutor(new back(this));

        // Listeners
        new PlayerLogin(this);
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDisable() {

    }

}
