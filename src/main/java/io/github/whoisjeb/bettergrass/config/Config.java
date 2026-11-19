package io.github.whoisjeb.bettergrass.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;

import static io.github.whoisjeb.bettergrass.config.Quality.FANCY;

public class Config {
    @ConfigRoot(value = "bettergrass", visibleName = "Better Grass Settings")
    public static final BetterGrassConfig betterGrassConfig = new BetterGrassConfig();

    public static class BetterGrassConfig {
        @ConfigEntry(name = "Quality", description = "Lower qualities have a lesser performance impact.")
        public Quality quality = FANCY;
    }
}
