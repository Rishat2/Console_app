package dto;

public class Coordinates {

    private long x; //Значение поля должно быть больше -775
    private long y; //Значение поля должно быть больше -734

    public Coordinates(long x, long y) {
        setX(x);
        setY(y);
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        if(x <= -775) {
            throw new IllegalArgumentException("Координата x должна быть больше чем -775!");
        }
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        if(y <= -734) {
            throw new IllegalArgumentException("Координата y должна быть больше чем -734!");
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates {" +
                "\nx: " + x +
                "\ny: " + y +
                "\n}";
    }
}
