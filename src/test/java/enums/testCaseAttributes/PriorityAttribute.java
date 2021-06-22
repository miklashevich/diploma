package enums.testCaseAttributes;

public enum PriorityAttribute {
    NOT_SET(1),
    HIGH(2),
    MEDIUM(3),
    LOW(4);

    private final int value;

    PriorityAttribute(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static PriorityAttribute getEnumByValue(int value) {
        for (PriorityAttribute mod : PriorityAttribute.values()) {
            if (mod.getValue() == value) {
                return mod;
            }
        }
        throw new IllegalArgumentException("No such constant with value " + value);
    }
}
