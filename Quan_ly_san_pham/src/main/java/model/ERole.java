package model;

public enum ERole {

    ADMIN("ADMIN"),
    USER("USER");

    private String value;

    ERole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ERole parseRole(String value) {
        ERole[] item = values();
        for (ERole role : item) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("invalid");
    }
}
