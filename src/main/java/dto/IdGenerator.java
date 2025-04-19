package dto;

public class IdGenerator {
    private static int currentId = 1;

    public static int nextId() {
        return currentId++;
    }

    public static void setId(int id) {
        currentId = id;
    }
}
