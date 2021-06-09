package enums;

public enum AccessType {

    ALL(1),
    GROUP(2),
    NONE(3);

    private final int value;


    AccessType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static AccessType getEnumByValue(int value) {
        for(AccessType mod: AccessType.values()) {
            if(mod.getValue() == value) {
                return mod;
            }
        }
        throw new IllegalArgumentException("No such constant with value" + value);
    }
}
