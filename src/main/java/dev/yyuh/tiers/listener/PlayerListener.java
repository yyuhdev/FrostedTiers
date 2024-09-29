package dev.yyuh.tiers.listener;

import dev.yyuh.tiers.mctiers.TierCache;
import dev.yyuh.tiers.mctiers.TierWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.io.IOException;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        try {
            TierWrapper.requestFromAPI(event.getUniqueId(), tierlistPlayer -> {
                TierCache.addPlayer(event.getUniqueId(), tierlistPlayer.getTier());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
