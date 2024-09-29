package dev.yyuh.tiers;

import dev.yyuh.tiers.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FrostedTiers extends JavaPlugin {

    @Override
    public void onEnable() {
        new PAPIHook().register();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
