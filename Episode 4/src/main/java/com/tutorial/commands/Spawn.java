package com.tutorial.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.tutorial.core.Utils.*;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equalsIgnoreCase("spawn")) {

           if(sender instanceof Player ) {
               Player player = (Player) sender;

               Location spawn = new Location(Bukkit.getWorld("world"), spawnX, spawnY, spawnZ);
               player.teleport(spawn);
           }
        }

        return false;
    }
}
