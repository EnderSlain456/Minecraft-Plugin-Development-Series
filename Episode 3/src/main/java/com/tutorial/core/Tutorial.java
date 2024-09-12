package com.tutorial.core;

import com.tutorial.commands.Discord;
import com.tutorial.commands.Spawn;
import com.tutorial.commands.TestCommand;
import com.tutorial.hub.*;
import com.tutorial.listeners.joinListener;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class Tutorial extends JavaPlugin {

    private Logger logger = getLogger();
    private AFKManager afkManager;

    @Override
    public void onEnable() {
        logger.info("Tutorial Plugin has been Enabled");

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();
        }

        loadCommands();
        loadEvents();
        loadConfig();

        afkManager = new AFKManager(this);
        afkManager.start();
    }

    @Override
    public void onDisable() {
        logger.info("Tutorial Plugin has been Disabled");
        afkManager.stop();

    }

    private void loadCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("discord").setExecutor(new Discord());
        getCommand("givebow").setExecutor(new TeleportBow());
        getCommand("spawn").setExecutor(new Spawn());

    }

    private void loadEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new joinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AFKManager(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new LaunchPad(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerVisibility(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new TeleportBow(), this);
    }

    public void loadConfig() {
        getDataFolder().mkdirs();
        File config = new File(getDataFolder(), "config.yml");
        if(!config.exists()) {
            saveDefaultConfig();
        }
    }

}
