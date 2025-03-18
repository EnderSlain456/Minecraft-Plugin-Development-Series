package com.tutorial.staffcommands;

import com.tutorial.core.ColorUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Unban implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("unban")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;

                if(!player.hasPermission("tutorial.unban")) {
                    player.sendMessage(ColorUtils.translateColorCodes("&cYou do not have permission to unban"));
                    return true;
                }

                if (args.length != 1) {
                    player.sendMessage(ColorUtils.translateColorCodes("&cUsage /unban <player>"));
                    return true;
                }

                String target = args[0];

                if(Bukkit.getBanList(BanList.Type.NAME).isBanned(target)) {
                    Bukkit.getBanList(BanList.Type.NAME).pardon(target);
                    player.sendMessage(ColorUtils.translateColorCodes("&aSuccessfully unbbaned " + target));
                } else {
                    player.sendMessage(ColorUtils.translateColorCodes("&cThat Player is not banned."));
                }
                return true;
            }
        }
        return false;
    }
}
