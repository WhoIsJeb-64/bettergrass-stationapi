package io.github.whoisjeb.bettergrass.config;

import com.google.common.collect.ImmutableMap;
import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigFactoryProvider;
import net.glasslauncher.mods.gcapi3.impl.SeptFunction;
import net.glasslauncher.mods.gcapi3.impl.factory.DefaultFactoryProvider;
import net.glasslauncher.mods.gcapi3.impl.object.ConfigEntryHandler;
import net.glasslauncher.mods.gcapi3.impl.object.entry.EnumConfigEntryHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.function.Function;

public class ConfigFactories implements ConfigFactoryProvider {
    @Override
    public void provideLoadFactories(ImmutableMap.Builder<Type, SeptFunction<String, ConfigEntry, Field, Object, Boolean, Object, Object, ConfigEntryHandler<?>>> builder) {
        builder.put(Quality.class, ((id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrOrdinal, defaultEnum) -> new EnumConfigEntryHandler<Quality>(id, configEntry, parentField, parentObject, isMultiplayerSynced, DefaultFactoryProvider.enumOrOrdinalToOrdinal(enumOrOrdinal), ((Quality) defaultEnum).ordinal(), Quality.class)));
    }

    @Override
    public void provideSaveFactories(ImmutableMap.Builder<Type, Function<Object, Object>> builder) {
        builder.put(Quality.class, enumEntry -> enumEntry);
    }
}
