package com.tutorial.staffcommands;

import com.tutorial.core.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equalsIgnoreCase("kick")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;

                if(!player.hasPermission("tutorial.kick")) {
                    player.sendMessage(ColorUtils.translateColorCodes("&c You do not have permission to run command!"));
                    return true;
                }
                if(args.length < 1) {
                    player.sendMessage(ColorUtils.translateColorCodes("&c Usage: /kick <player> [reason]"));
                    return true;
                }

                Player target = Bukkit.getPlayer(args[0]);
                if(target == null) {
                    player.sendMessage(ColorUtils.translateColorCodes("&c Player not found!"));
                    return true;
                }

                String reason = "You have been kicked!";
                if(args.length > 1) {
                    reason = String.join(" ", args).substring(args[0].length()).trim();
                }

                target.kickPlayer(ColorUtils.translateColorCodes("&c You were kicked!\n Reason: " + reason));
                player.sendMessage(ColorUtils.translateColorCodes("&a Successfully Kicked " + target.getName() + "for: " + reason));
                return true;
            }
        }
        return false;
    }
}
