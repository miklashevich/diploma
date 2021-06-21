package enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import org.testng.annotations.BeforeMethod;

public enum AccessType {

    ALL("all"),
    GROUP("group"),
    NONE("none");

    private String value;

    AccessType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue() {
        this.value = value;
    }

    public static AccessType getEnumByValue(String value) {
        for (AccessType mod : AccessType.values()) {
            if (mod.getValue().equals(value)) {
                return mod;
            }
        }
        throw new IllegalArgumentException("No such constant with value " + value);
    }
}
