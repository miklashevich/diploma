package enums.testCaseAttributes;

public enum AutomationStatusAttribute {
    NOT_AUTOMATED(1),
    TO_BE_AUTOMATED(2),
    AUTOMATED(3);

    private final int value;

    AutomationStatusAttribute(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static AutomationStatusAttribute getEnumByValue(int value) {
        for (AutomationStatusAttribute mod : AutomationStatusAttribute.values()) {
            if (mod.getValue() == value) {
                return mod;
            }
        }
        throw new IllegalArgumentException("No such constant with value " + value);
    }
}
