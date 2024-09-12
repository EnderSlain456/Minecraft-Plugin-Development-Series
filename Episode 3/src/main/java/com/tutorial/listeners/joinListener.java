package com.tutorial.listeners;

import com.tutorial.core.ColorUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.tutorial.core.Utils.*;

public class joinListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());


        if (user != null) {
            String playerPrefix = user.getCachedData().getMetaData().getPrefix();

            if (playerPrefix == null) {
                playerPrefix = "";
            }

            String joinText = ColorUtils.translateColorCodes(joinPrefix + playerPrefix + player.getName());
            joinText = PlaceholderAPI.setPlaceholders(player, joinText);
            event.setJoinMessage(joinText);
        }

        Location spawn = new Location(Bukkit.getWorld("world"), spawnX, spawnY, spawnZ);
        player.teleport(spawn);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());


        if (user != null) {
            String playerPrefix = user.getCachedData().getMetaData().getPrefix();

            if (playerPrefix == null) {
                playerPrefix = "";
            }

            String quitText = ColorUtils.translateColorCodes(quitPrefix + playerPrefix + player.getName());
            quitText = PlaceholderAPI.setPlaceholders(player, quitText);
            event.setQuitMessage(quitText);
        }
    }

}
