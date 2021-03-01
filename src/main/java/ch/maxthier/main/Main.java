package ch.maxthier.main;

import ch.maxthier.manager.ChallengeCommand;
import ch.maxthier.manager.ChallengeTabcompleter;
import ch.maxthier.manager.GameSelecter;
import ch.maxthier.ticatactoe.TTTListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public final static double Version = 1.0;
    private static Main plugin;
    FileConfiguration config;

    @Override
    public void onEnable() {
        plugin = this;
        config = plugin.getConfig();
        GameSelecter.build("Choose the gamemode!");
        register();
        registerconfig();
        Material item1 = Material.getMaterial(config.getString("TicTacToe.Item1"));
        Material item2 = Material.getMaterial(config.getString("TicTacToe.Item2"));
        if(item1 == null){
            config.set("TicTacToe.Item1", Material.BARRIER.toString());
            System.out.println("The set material at TicTacToe.Item1 does not exist, therefore the item was reset to the default value");
        }
        if(item2 == null){
            config.set("TicTacToe.Item2", Material.STRUCTURE_VOID);
            System.out.println("The set material at TicTacToe.Item2 does not exist, therefore the item was reset to the default value");
        }
        plugin.saveConfig();
    }

    private void registerconfig(){
        config.addDefault("NeedsPermission", true);
        config.addDefault("TicTacToe.Item1", Material.BARRIER.toString());
        config.addDefault("TicTacToe.Item2", Material.STRUCTURE_VOID.toString());
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    @Override
    public void onDisable() {
    }

    private void register(){
        getCommand("challenge").setExecutor(new ChallengeCommand());
        getCommand("challenge").setTabCompleter(new ChallengeTabcompleter());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new TTTListener(), this);
        pluginManager.registerEvents(new GameSelecter(), this);
    }

    public static Main getPlugin() {
        return plugin;
    }
}