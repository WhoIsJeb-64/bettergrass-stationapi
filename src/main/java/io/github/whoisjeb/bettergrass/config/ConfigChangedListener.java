package io.github.whoisjeb.bettergrass.config;

import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.gcapi3.api.PreConfigSavedListener;
import net.glasslauncher.mods.gcapi3.impl.GlassYamlFile;
import net.minecraft.client.Minecraft;

@SuppressWarnings("deprecation")
public class ConfigChangedListener implements PreConfigSavedListener {

    @Override public void onPreConfigSaved(int source, GlassYamlFile oldValues, GlassYamlFile newValues) {
        if(oldValues.get("quality", 0) != newValues.get("quality", 0)) {
            Minecraft minecraft = (Minecraft) FabricLoader.getInstance().getGameInstance();
            if(minecraft != null) {
                minecraft.worldRenderer.reload();
            }
        }
    }
}
