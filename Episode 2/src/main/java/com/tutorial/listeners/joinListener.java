package com.tutorial.listeners;

import com.tutorial.core.ColorUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class joinListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        String joinPrefix = "&7[&#16FC19&l+&7] ";

        if (user != null) {
            String playerPrefix = user.getCachedData().getMetaData().getPrefix();

            if (playerPrefix == null) {
                playerPrefix = "";
            }

            String joinText = ColorUtils.translateColorCodes(joinPrefix + playerPrefix + player.getName());
            joinText = PlaceholderAPI.setPlaceholders(player, joinText);
            event.setJoinMessage(joinText);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        String quitPrefix = "&7[&#FF0000&l-&7] ";

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
