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
}
