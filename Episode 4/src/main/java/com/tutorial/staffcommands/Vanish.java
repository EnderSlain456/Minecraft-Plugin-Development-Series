package com.tutorial.staffcommands;

import com.tutorial.core.ColorUtils;
import com.tutorial.core.Tutorial;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class Vanish implements CommandExecutor {

    Tutorial plugin;

    public Vanish(Tutorial plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("vanish")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("tutorial.vanish")) {
                    if(plugin.vanish_list.contains(player)) {
                        for (Player people : Bukkit.getOnlinePlayers()) {
                            people.showPlayer(plugin, player);
                        }
                        plugin.vanish_list.remove(player);
                        player.sendMessage(ColorUtils.translateColorCodes("&cYou are no longer in vanish."));
                    } else if (!plugin.vanish_list.contains(player)) {
                        for (Player people: Bukkit.getOnlinePlayers()) {
                            if (people.hasPermission("tutorial.vanish")) {
                                people.hidePlayer(plugin, player);
                            } else {
                                people.showPlayer(plugin, player);
                            }
                        }

                        plugin.vanish_list.add(player);
                        startActionBar(player);
                        player.sendMessage(ColorUtils.translateColorCodes("&aYou are no in vanish"));
                    }
                } else {
                    player.sendMessage(ColorUtils.translateColorCodes("&c You do not have permission to use this command!"));
                }

            }
        }
        return false;
    }

    public void startActionBar(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (plugin.vanish_list.contains(player)) {
                    player.sendActionBar(ColorUtils.translateColorCodes("&eVanish: &aEnabled!"));
                } else {
                    player.sendActionBar(ColorUtils.translateColorCodes("&eVanish: &cDisabled!"));
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}
