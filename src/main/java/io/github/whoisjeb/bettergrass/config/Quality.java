package io.github.whoisjeb.bettergrass.config;

public enum Quality {
    OFF("Off"),
    FAST("Fast"),
    FANCY("Fancy");

    private final String name;

    Quality(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
