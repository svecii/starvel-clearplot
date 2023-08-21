package com.specterplugins.clearplot.manager;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.specterplugins.clearplot.Starvel;

import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class StarvelManager {
   public static void plotReset(Integer id, Location locationMin, Location locationMax) {
      locationMin.setY(1.0D);
      CuboidSelection cuboid = new CuboidSelection(Bukkit.getWorld(locationMin.getWorld().getName()), locationMin, locationMax);
      CuboidRegion region = new CuboidRegion(BukkitUtil.getLocalWorld(cuboid.getWorld()), cuboid.getNativeMinimumPoint(), cuboid.getNativeMaximumPoint());
      EditSession session = new EditSession(BukkitUtil.getLocalWorld(cuboid.getWorld()), region.getArea());

      try {
         session.setBlocks(region, new BaseBlock(id));
         session.flushQueue();
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   public static String limparTerreno(Player p) {
      String clear = "";
      if (Starvel.getInstance().plot.getPlot(p.getLocation()) != null) {
         Set<UUID> uuid = Starvel.getInstance().plot.getPlot(p.getLocation()).getOwners();
         if (uuid.contains(p.getUniqueId())) {
            String name = Starvel.getInstance().plot.getPlot(p.getLocation()).getWorldName();
            Double minX = (double)Starvel.getInstance().plot.getPlot(p.getLocation()).getCorners()[0].getX();
            Double minZ = (double)Starvel.getInstance().plot.getPlot(p.getLocation()).getCorners()[0].getZ();
            Location locationMin = new Location(Bukkit.getWorld(name), minX, 1.0D, minZ);
            Double maxX = (double)Starvel.getInstance().plot.getPlot(p.getLocation()).getCorners()[1].getX();
            Double maxY = (double)Starvel.getInstance().plot.getPlot(p.getLocation()).getCorners()[1].getY();
            Double maxZ = (double)Starvel.getInstance().plot.getPlot(p.getLocation()).getCorners()[1].getZ();
            Location locationMax = new Location(Bukkit.getWorld(name), maxX, maxY, maxZ);
            plotReset(0, locationMin, locationMax);
            clear = "limpadora";
            p.sendMessage(Starvel.getInstance().getConfig().getString("Message.Clear").replace("&", "§"));
         } else {
            p.sendMessage(Starvel.getInstance().getConfig().getString("Message.PlotOwner").replace("&", "§"));
         }
      } else {
         p.sendMessage(Starvel.getInstance().getConfig().getString("Message.noPlot").replace("&", "§"));
      }

      return clear;
   }
}
