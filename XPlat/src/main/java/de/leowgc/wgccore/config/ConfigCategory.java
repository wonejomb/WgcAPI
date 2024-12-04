package de.leowgc.wgccore.config;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.regex.Pattern;

public class ConfigCategory {
    private static final Pattern START_PATTERN = Pattern.compile("\\s*\\[a-zA-Z].start");
    private static final Pattern END_PATTERN = Pattern.compile("\\s*\\[a-z-A-Z].end");

    private final String name;
    private final ImmutableList<ConfigSpec<?>> specs;

    ConfigCategory ( String pName, ImmutableList<ConfigSpec<?>> pSpecs ) {
        this.name = pName;
        this.specs = pSpecs;
    }

    public void load ( List<String> pFileContent ) {
        ]
    }

    @SuppressWarnings("unchecked")
    public <T> T get ( String pKey ) {
        return (T) this.getSpec(pKey).deserialize();
    }

    @SuppressWarnings({"OptionalGetWithoutIsPresent", "unchecked"})
    public <T> ConfigSpec<T> getSpec ( String pKey ) {
        return (ConfigSpec<T>) this.specs.stream().filter((s) -> s.getIdentifier().equals(pKey)).findAny().get();
    }

}