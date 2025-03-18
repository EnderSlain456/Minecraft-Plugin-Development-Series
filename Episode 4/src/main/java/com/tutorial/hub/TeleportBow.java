package com.tutorial.hub;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeleportBow implements Listener, CommandExecutor {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if(event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            if(arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();

                player.teleport(arrow.getLocation());

                arrow.remove();
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("givebow")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                ItemStack bow = new ItemStack(Material.BOW);
                ItemMeta meta = bow.getItemMeta();
                if(meta != null) {
                    meta.setDisplayName("Teleport Bow");
                    meta.addEnchant(Enchantment.INFINITY, 1, true);
                    bow.setItemMeta(meta);
                }

                player.getInventory().addItem(bow);
                player.getInventory().addItem(new ItemStack(Material.ARROW));
            }
            return true;
        }
        return false;
    }

}
