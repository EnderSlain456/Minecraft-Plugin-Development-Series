package com.tutorial.commands;

import com.tutorial.core.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Discord implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equalsIgnoreCase("discord")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                player.sendMessage(ColorUtils.translateColorCodes("&#85C1E9&l DISCORD"));
                player.sendMessage(ColorUtils.translateColorCodes("&#85C1E9 Join my Discord Server!"));
                player.sendMessage(ColorUtils.translateColorCodes("&#85C1E9 https://discord.gg/FJN7qPazrQ"));
            } else {
                sender.sendMessage(ColorUtils.translateColorCodes("&4 You must be a player to run this command"));
            }
        }

        return false;
    }
}
