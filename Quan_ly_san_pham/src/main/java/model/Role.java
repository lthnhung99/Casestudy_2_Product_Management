package model;

public enum Role {

    ADMIN("ADMIN"),
    USER("USER");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Role parseRole(String value) {
        Role[] item = values();
        for (Role role : item) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("invalid");
    }
}
