package dev.yyuh.tiers.mctiers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@UtilityClass
public class TierWrapper {

    private final static String TIER_URL = "https://mctiers.com/api/rankings/";

    public void requestFromAPI(UUID playerUUID, Consumer<TierlistPlayer> consumer) throws IOException {
        CompletableFuture.supplyAsync(() -> {
            try {
                String uuid = playerUUID.toString().replace("-", "");
                URL url = new URL(TIER_URL + uuid);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    System.err.println("Error Code: '<code>'"
                            .replace("<code>", String.valueOf(responseCode))
                    );
                    return new TierlistPlayer(playerUUID, PlayerTier.UNRANKED);
                }
                String responseString = new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                JsonElement responseElement = JsonParser.parseString(responseString);
                JsonObject root = responseElement.getAsJsonObject();
                String name = "vanilla";
                if (root.has(name)) {
                    JsonObject tierObject = root.get(name).getAsJsonObject();
                    int tier = tierObject.get("tier").getAsInt();
                    int pos = tierObject.get("pos").getAsInt();
                    int tierValue = PlayerTier.wrap(tier, pos);
                    PlayerTier tierObj = PlayerTier.from(tierValue);
                    return new TierlistPlayer(playerUUID, tierObj);
                }
                return new TierlistPlayer(playerUUID, PlayerTier.UNRANKED);
            } catch (IOException e){
                e.printStackTrace();
            }
            return new TierlistPlayer(playerUUID, PlayerTier.UNRANKED);
        }).thenAccept(consumer);
    }
}
