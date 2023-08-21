package com.specterplugins.clearplot.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.specterplugins.clearplot.command.LimpadoraCommand;
import com.specterplugins.clearplot.manager.StarvelManager;

public class StarvelEvents implements Listener {
   @EventHandler
   void interactLimpadora(PlayerInteractEvent e) {
      Player p = e.getPlayer();
      Action action = e.getAction();
      if (e.getItem() != null && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)) {
         ItemStack item = e.getItem().clone();
         if (item.isSimilar(LimpadoraCommand.item)) {
            String clear = StarvelManager.limparTerreno(p);
            e.setCancelled(true);
            if (clear.equalsIgnoreCase("limpadora")) {
               if (p.getItemInHand().getAmount() == 1) {
                  p.getInventory().setItem(p.getInventory().getHeldItemSlot(), (ItemStack)null);
               } else {
                  p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
               }
            }
         }
      }

   }
}
