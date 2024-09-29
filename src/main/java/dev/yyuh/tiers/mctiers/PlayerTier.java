package dev.yyuh.tiers.mctiers;

public enum PlayerTier {

    LT5(1, "&#D3D3D3LT5"),
    HT5(2, "&#808080HT5"),
    LT4(3, "&#90EE90LT4"),
    HT4(4, "&#006400HT4"),
    LT3(5, "&#EEE8AALT3"),
    HT3(6, "&#DAA520HT3"),
    LT2(7, "&#FFE4B5LT2"),
    HT2(8, "&#FFA500HT2"),
    LT1(9, "&#FFB6C1LT1"),
    HT1(10, "&#FF0000HT1"),
    UNRANKED(-1, "&#D3D3D3N/A");

    private final int tier;
    private final String formatted;

    PlayerTier(int val, String formatted){
        this.formatted = formatted;
        this.tier = val;
    }

    public int getTierValue() {
        return this.tier;
    }

    public String getStringValue(){
        return this.formatted;
    }


    public static PlayerTier from(int value) {
        for (PlayerTier tier : values()) {
            if (tier.getTierValue() == value) return tier;
        }
        return PlayerTier.UNRANKED;
    }

    public static int wrap(int tier, int pos) {
        return 12 - (tier * 2) - pos;
    }
}
