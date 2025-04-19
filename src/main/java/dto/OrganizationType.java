package dto;

public enum OrganizationType {

    COMMERCIAL("commercial"),
    PUBLIC("public"),
    TRUST("trust"),
    OPEN_JOINT_STOCK_COMPANY("open_joint_stock_company");

    private final String description;

    OrganizationType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
