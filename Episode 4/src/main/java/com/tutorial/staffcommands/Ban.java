package com.tutorial.staffcommands;

import com.tutorial.core.ColorUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Ban  implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equalsIgnoreCase("ban")) {
            if(sender instanceof Player) {
                Player player =  (Player) sender;

                if(!player.hasPermission("tutorial.ban")) {
                    player.sendMessage(ColorUtils.translateColorCodes("&c You do not have permission to ban!"));
                    return true;
                }

                if (args.length < 1) {
                    player.sendMessage("&c Usage: /ban <player> [reason]");
                    return true;
                }

                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage("&cPlayer not found.");
                    return true;
                }

                String reason = "Banned by an admin";
                if (args.length > 1) {
                    reason = String.join(" ", args).substring(args[0].length()).trim();
                }

                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, player.getName());
                target.kickPlayer(ColorUtils.translateColorCodes("&cYou have been banned! \n Reason: " + reason));

                player.sendMessage((ColorUtils.translateColorCodes("&a Successfully banned " + target.getName() + " for: " + reason)));
                return true;
            }
        }
        return false;
    }
}
