package com.tutorial.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("test")){
            if (sender instanceof Player) {
                Player player = (Player) sender;

                player.sendMessage("Test Commands Works!");

            } else {
                sender.sendMessage("You must be a player to run this command");
            }
        }
        return false;
    }
}
