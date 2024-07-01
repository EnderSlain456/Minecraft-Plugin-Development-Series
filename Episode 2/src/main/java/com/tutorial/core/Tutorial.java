package com.tutorial.core;

import com.tutorial.commands.Discord;
import com.tutorial.commands.TestCommand;
import com.tutorial.listeners.joinListener;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Tutorial extends JavaPlugin {

    private Logger logger = getLogger();

    @Override
    public void onEnable() {
        logger.info("Tutorial Plugin has been Enabled");

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();
        }

        loadCommands();
        loadEvents();
    }

    @Override
    public void onDisable() {
        logger.info("Tutorial Plugin has been Disabled");

    }

    private void loadCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("discord").setExecutor(new Discord());

    }

    private void loadEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new joinListener(), this);
    }

}
