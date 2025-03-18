package com.tutorial.hub;

import com.tutorial.core.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Set;

public class PlayerVisibility implements Listener {


    private Set<Player> playerWithHidden = new HashSet<>();

    private boolean isGlobal = false;


    private ItemStack getToggleItem() {
        ItemStack item = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            meta.setDisplayName(ColorUtils.translateColorCodes("&ePlayer Visibility Toggle"));
            item.setItemMeta(meta);
        }
        return item;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().addItem(getToggleItem());

        if(isGlobal){
            hidePlayer(player);
        } else {
            showPlayer(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        playerWithHidden.remove(player);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(player.getInventory().getItemInMainHand().isSimilar(getToggleItem())) {
            toggleVisibility(player);
        }
    }

    private void toggleVisibility(Player togglingPlayer) {
        if(playerWithHidden.contains(togglingPlayer)) {
            showAllPlayers(togglingPlayer);
            togglingPlayer.sendMessage(ColorUtils.translateColorCodes("&aYou can now see other players"));
        } else {
            hideAllPlayers(togglingPlayer);
            togglingPlayer.sendMessage(ColorUtils.translateColorCodes("&4All players are now hidden"));
        }
    }

    private void hideAllPlayers(Player player) {
        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            if(!otherPlayer.equals(player)) {
                player.hidePlayer( otherPlayer);
            }
        }
        playerWithHidden.add(player);
    }

    private void showAllPlayers(Player player) {
        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            if(!otherPlayer.equals(player)) {
                player.showPlayer(otherPlayer);
            }
        }
        playerWithHidden.remove(player);
    }

    private void hidePlayer(Player hiddenPlayer) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if (!player.equals(hiddenPlayer)) {
                player.hidePlayer(hiddenPlayer);
            }
        }
    }

    private void showPlayer(Player shownPlayer) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!player.equals(shownPlayer)) {
                player.showPlayer(shownPlayer);
            }
        }
    }

}
