package com.tutorial.core;

import com.tutorial.commands.TestCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Tutorial extends JavaPlugin {

    private Logger logger = getLogger();

    @Override
    public void onEnable() {
        logger.info("Tutorial Plugin has been Enabled");
        loadCommands();
    }

    @Override
    public void onDisable() {
        logger.info("Tutorial Plugin has been Disabled");

    }

    private void loadCommands() {
        getCommand("test").setExecutor(new TestCommand());

    }
}
