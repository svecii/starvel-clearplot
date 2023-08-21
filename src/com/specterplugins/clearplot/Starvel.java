package com.specterplugins.clearplot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.intellectualsites.plotsquared.api.PlotAPI;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.specterplugins.clearplot.command.LimpadoraCommand;
import com.specterplugins.clearplot.events.StarvelEvents;

public class Starvel extends JavaPlugin { 
	
	public PlotAPI plot = null;
	private static Starvel instance;
	
	public void onEnable() {
		if (this.getServer().getPluginManager().getPlugin("PlotSquared") == null) {
			getLogger().info("Plugin desligado, não encontramos o plotsquared.");
			this.getServer().getPluginManager().disablePlugin(this);
		} else {
			instance = this;
			this.saveDefaultConfig();
			getLogger().info("plugin enabled.");
			this.plot = new PlotAPI();
			this.registerCommands();
		}
	}
	
	public static Starvel getInstance() { 
		return instance;
	}
	   public WorldEditPlugin getWorldEdit() {
		      Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		      return plugin instanceof WorldEditPlugin ? (WorldEditPlugin)plugin : null;
		   }
	   
	   private void registerCommands() {
		      Bukkit.getPluginManager().registerEvents(new StarvelEvents(), this);
		      this.getCommand("darlimpadora").setExecutor(new LimpadoraCommand());
		   }


}
