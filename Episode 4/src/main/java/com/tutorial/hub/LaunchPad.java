package com.tutorial.hub;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import static com.tutorial.core.Utils.*;

public class LaunchPad implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

        if(block != null && block.getType() == Material.SLIME_BLOCK) {
            Player player = event.getPlayer();

            Vector launchVelocity = new Vector(launchX, launchY, launchZ);
            player.setVelocity(launchVelocity);
        }
    }
}
