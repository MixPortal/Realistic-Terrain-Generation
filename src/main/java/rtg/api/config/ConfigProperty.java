package rtg.api.config;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.config.Property;
import rtg.api.util.BlockStringUtil;
import rtg.api.util.debug.RTGException;

/**
 * Wrapper for all different kinds of config properties
 * @author topisani
 */
public class ConfigProperty {

    public enum Type {
        INTEGER,
        BOOLEAN,
        STRING,
        BLOCK
    }

    public String id;
    public String section;
    public String comment;
    public Type type;

    protected int minValue;
    protected int maxValue;

    protected int defaultInt;
    protected boolean defaultBoolean = true;
    protected String defaultString = "";
    protected IBlockState defaultBlock;

    protected int valueInt;
    protected boolean valueBoolean = true;
    protected String valueString = "";
    protected IBlockState valueBlock;

    public ConfigProperty(String id, String section, Type type) {
        this.id = id;
        this.section = section;
        this.type = type;
    }

    public ConfigProperty setIntRange(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        return this;
    }

    public ConfigProperty set(int valueInt) {
        this.valueInt = valueInt;
        return this;
    }

    public ConfigProperty set(boolean valueBoolean) {
        this.valueBoolean = valueBoolean;
        return this;
    }

    public ConfigProperty set(String valueString) {
        this.valueString = valueString;
        return this;
    }

    public ConfigProperty set(IBlockState valueBlock) {
        this.valueBlock = valueBlock;
        return this;
    }

    public ConfigProperty setDefault(int defaultInt) {
        this.defaultInt = defaultInt;
        return this;
    }

    public ConfigProperty setDefault(boolean defaultBoolean) {
        this.defaultBoolean = defaultBoolean;
        return this;
    }

    public ConfigProperty setDefault(String defaultString) {
        this.defaultString = defaultString;
        return this;
    }

    public ConfigProperty setDefault(IBlockState defaultBlock) {
        this.defaultBlock = defaultBlock;
        return this;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int _defaultInt() {
        return defaultInt;
    }

    public boolean _defaultBoolean() {
        return defaultBoolean;
    }

    public String _defaultString() {
        return defaultString;
    }

    public IBlockState _defaultBlock() {
        return defaultBlock;
    }

    public int _int() {
        return valueInt;
    }

    public boolean _boolean() {
        return valueBoolean;
    }

    public String _string() {
        return valueString;
    }

    public IBlockState _block() {
        return valueBlock;
    }

    public String getID() {
        return this.id;
    }

    public String getComment() {
        return comment;
    }

    public ConfigProperty setComment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Needed for writing to config files
     * @throws RTGException
     */
    public Property toForgeProp() throws RTGException{
        Property prop;
        switch (type) {
            case INTEGER:
                prop = new Property(id, String.valueOf(this.valueInt), Property.Type.INTEGER).setDefaultValue(this.defaultInt);
                break;
            case BOOLEAN:
                prop = new Property(id, String.valueOf(valueBoolean), Property.Type.BOOLEAN).setDefaultValue(defaultBoolean);
                break;
            case STRING:
                prop = new Property(id, valueString, Property.Type.STRING).setDefaultValue(defaultString);
                break;
            case BLOCK:
                prop = new Property(id, BlockStringUtil.stateToString(valueBlock), Property.Type.STRING).setDefaultValue(BlockStringUtil.stateToString(defaultBlock));
                break;
            default:
                throw new RTGException(RTGException.Type.CONFIG_SYNTAX, "Somehow RTG doesnt support this config type: " + type.name(), "ConfigProperty.toForgeProp()");
        }

        return prop;
    }

    /**
     * Needed for writing to config files
     * @param prop The property to read
     * @throws RTGException of type CONFIG_SYNTAX if failed.
     * @return this
     */
    public ConfigProperty readForgeProperty(Property prop) throws RTGException {
        try {
            switch (type) {
                case INTEGER:
                    this.set(prop.getInt());
                    break;
                case BOOLEAN:
                    this.set(prop.getBoolean());
                    break;
                case STRING:
                    this.set(prop.getString());
                    break;
                case BLOCK:
                    this.set(BlockStringUtil.stringToState(prop.getString()));
            }
            return this;
        } catch (Exception e) {
            throw new RTGException(RTGException.Type.CONFIG_SYNTAX,
                    "Tried to read property " + prop.getName() + " with value " + prop.getString() + " of type " + prop.getType().name() +
                            " into property " + id + " of type " + type.name(),
                    "ConfigProperty.fromForgeProp()");
        }
    }
}
