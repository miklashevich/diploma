package enums.testCaseAttributes;

public enum TypeAttribute {
    OTHER(1),
    FUNCTIONAL(2),
    SMOKE(3),
    REGRESSION(4),
    SECURITY(5),
    USABILITY(6),
    PERFORMANCE(7),
    ACCEPTANCE(8),
    COMPATIBILITY(9),
    INTEGRATION(10),
    EXPLORATORY(11);

    private final int value;

    TypeAttribute(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static TypeAttribute getEnumByValue(int value) {
        for (TypeAttribute mod : TypeAttribute.values()) {
            if (mod.getValue() == value) {
                return mod;
            }
        }
        throw new IllegalArgumentException("No such constant with value " + value);
    }
}
