package dto;

import java.util.Objects;

public class Location {

    private Integer x; //Поле не может быть null
    private int y;
    private int z;
    private String name; //Поле не может быть null

    public Location(Integer x, int y, int z, String name) {
        setX(x);
        setY(y);
        setZ(z);
        setName(name);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        if(x == null) {
            throw new IllegalArgumentException("Составная x локации должна быть определена!");
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Название локации должно быть определено!");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return y == location.y &&
                z == location.z &&
                Objects.equals(x, location.x) &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }

    @Override
    public String toString() {
        return "Location {" +
                "\nx: " + x +
                "\ny: " + y +
                "\nz: " + z +
                "\nname: " + name +
                "\n}";
    }
}
