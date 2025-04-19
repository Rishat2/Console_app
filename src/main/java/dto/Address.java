package dto;

import java.util.Objects;

public class Address {

    private String street; //Поле не может быть null
    private Location town; //Поле может быть null

    public Address(String street, Location town) {
        setStreet(street);
        setTown(town);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if(street == null) {
            throw new IllegalArgumentException("Название улицы должно быть определенно!");
        }
        this.street = street;
    }

    public Location getTown() {
        return town;
    }

    public void setTown(Location town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(street, address.street) &&
                Objects.equals(town, address.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, town);
    }

    @Override
    public String toString() {
        return  "Address {" +
                "\nTown: " + this.town +
                "\nStreet: " + this.street +
                "\n}";
    }
}
