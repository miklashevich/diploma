package enums.testCaseAttributes;

import enums.AccessType;

public enum SeverityAttribute {
    NOT_SET(1),
    BLOCKER(2),
    CRITICAL(3),
    MAJOR(4),
    NORMAL(5),
    MINOR(6),
    TRIVIAL(7);

    private final int value;

    SeverityAttribute(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SeverityAttribute getEnumByValue(int value) {
        for (SeverityAttribute mod : SeverityAttribute.values()) {
            if (mod.getValue() == value) {
                return mod;
            }
        }
        throw new IllegalArgumentException("No such constant with value " + value);
    }
}
