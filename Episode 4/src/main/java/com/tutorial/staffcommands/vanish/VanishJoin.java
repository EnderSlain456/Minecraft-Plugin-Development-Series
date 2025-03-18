package com.tutorial.staffcommands.vanish;


import com.tutorial.core.Tutorial;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VanishJoin implements Listener {

    Tutorial plugin;

    public VanishJoin(Tutorial plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (int i =0; i < plugin.vanish_list.size(); i++) {
            for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                if(!all.hasPermission("tutorial.vanish")) {
                    player.hidePlayer(plugin, plugin.vanish_list.get(i));
                } else {
                    player.showPlayer(plugin, plugin.vanish_list.get(i));
                }
            }
        }
    }
}
