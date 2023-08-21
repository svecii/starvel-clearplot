package com.specterplugins.clearplot.command;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.specterplugins.clearplot.Starvel;

public class LimpadoraCommand implements CommandExecutor {
   private static FileConfiguration config = Starvel.getInstance().getConfig();
   public static ItemStack item;

   static {
      int id = config.getInt("Clear.Item.ID");
      int data = config.getInt("Clear.Item.Data");
      String name = config.getString("Clear.Item.Name").replace("&", "§");
      List<String> lore = config.getStringList("Clear.Item.Lore");
      lore = (List)lore.stream().map((l) -> {
         return l.replace("&", "§");
      }).collect(Collectors.toList());
      ItemStack item = new ItemStack(id, 1, (short)data);
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(name);
      meta.setLore(lore);
      item.setItemMeta(meta);
      LimpadoraCommand.item = item;
   }

   public boolean onCommand(CommandSender s, Command cmd, String lb, String[] a) {
      if (!s.hasPermission("starvel.give.limpadora")) {
         s.sendMessage("§cVocê não tem permissão para executar este comando.");
         return true;
      } else if (a.length != 0 && a.length != 1) {
         Player t = Bukkit.getPlayerExact(a[0]);
         if (t == null) {
            s.sendMessage("§cJogador offline.");
            return true;
         } else {
            boolean var6 = false;

            int amount;
            try {
               amount = Integer.parseInt(a[1]);
            } catch (Exception var8) {
               s.sendMessage("§cDigite um número válido por favor.");
               return true;
            }

            ItemStack item = LimpadoraCommand.item.clone();
            item.setAmount(amount);
            t.getInventory().addItem(new ItemStack[]{item});
            return false;
         }
      } else {
         s.sendMessage("§cUtilize: /givelimpadora <jogador> <quantia>");
         return true;
      }
   }
}
