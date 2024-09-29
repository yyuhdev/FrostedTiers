package dev.yyuh.tiers.mctiers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor @Getter
public class TierlistPlayer {

    private final UUID uuid;
    private final PlayerTier tier;
}
