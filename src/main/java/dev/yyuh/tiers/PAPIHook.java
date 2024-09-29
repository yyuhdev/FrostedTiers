package dev.yyuh.tiers;

import dev.yyuh.tiers.mctiers.TierCache;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PAPIHook extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "FrostedTiers";
    }

    @Override
    public @NotNull String getAuthor() {
        return "yyuh";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) return "";

        switch (params) {
            case "tier" -> {
                return TierCache.getPlayer(player.getUniqueId()).getTier().getStringValue();
            }
        }
        return "";
    }
}
