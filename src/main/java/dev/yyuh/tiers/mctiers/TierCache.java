package dev.yyuh.tiers.mctiers;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class TierCache {

    private final Map<UUID, PlayerTier> tiers = new ConcurrentHashMap<>();

    public void addPlayer(UUID uuid, PlayerTier tier){
        tiers.put(uuid, tier);
    }

    public TierlistPlayer getPlayer(UUID uuid){
        return new TierlistPlayer(uuid, tiers.getOrDefault(uuid, PlayerTier.UNRANKED));
    }

    public void invalidate(UUID uuid){
        tiers.remove(uuid);
    }
}
