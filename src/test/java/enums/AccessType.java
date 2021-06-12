package enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AccessType {

    ALL("all"),
    GROUP("group"),
    NONE("none");

    private final String value;

    AccessType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public static AccessType getEnumByValue(String value) {
        for(AccessType mod: AccessType.values()) {
            if(mod.getValue().equals(value)) {
                return mod;
            }
        }
        throw new IllegalArgumentException("No such constant with value " + value);
    }
}
