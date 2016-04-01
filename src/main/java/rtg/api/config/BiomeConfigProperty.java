package rtg.api.config;


import static rtg.api.config.ConfigProperty.IPropertyID.Type.BLOCK;
import static rtg.api.config.ConfigProperty.IPropertyID.Type.BOOLEAN;

public enum BiomeConfigProperty implements ConfigProperty.IPropertyID {

    ALLOW_VILLAGES(BOOLEAN),
    USE_RTG_DECORATIONS(BOOLEAN),
    USE_RTG_SURFACES(BOOLEAN),
    SURFACE_TOP_BLOCK(BLOCK),
    SURFACE_FILLER_BLOCK(BLOCK),
    // End defaults
    SURFACE_TOP_MIX_BLOCK(BLOCK),
    SURFACE_FILLER_MIX_BLOCK(BLOCK),
    DECORATION_LOG(BOOLEAN),
    DECORATION_CACTI(BOOLEAN),
    DECORATION_BOULDER(BOOLEAN),
    DECORATION_DEAD_BUSH(BOOLEAN),
    DECORATION_FLOWERS(BOOLEAN),
    DECORATION_COBWEB(BOOLEAN),
    DECORATION_TREE_PALM(BOOLEAN);

    public final Type type;

    BiomeConfigProperty(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }
}
