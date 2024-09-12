package com.tutorial.hub;

import com.tutorial.core.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class AFKManager implements Listener {

    private final Plugin plugin;
    private HashMap<UUID, Long> playerActivity;
    private static final long AFK_TIMEOUT = 300000;
    private BukkitRunnable afkTask;

    public AFKManager(Plugin plugin) {
        this.plugin = plugin;
        playerActivity = new HashMap<>();
    }

    public void start() {
        afkTask = new BukkitRunnable() {
            public void run() {
                checkforAFKPlayers();
            }
        };
        afkTask.runTaskTimer(plugin, 0L, 20L * 6);
    }

    public void stop() {
        if(afkTask != null) {
            afkTask.cancel();
        }
        playerActivity.clear();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        playerActivity.put(player.getUniqueId(), System.currentTimeMillis());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        playerActivity.remove(event.getPlayer().getUniqueId());
    }

    private void checkforAFKPlayers() {
        long currentTime = System.currentTimeMillis();
        for (Player player :Bukkit.getOnlinePlayers()) {
            UUID playerId = player.getUniqueId();
            long lastActivity = playerActivity.getOrDefault(playerId, currentTime);

            if (currentTime - lastActivity > AFK_TIMEOUT) {
                player.kickPlayer(ColorUtils.translateColorCodes("&4You have been kicked for being AFK for too long!"));
            }
        }
    }
}
