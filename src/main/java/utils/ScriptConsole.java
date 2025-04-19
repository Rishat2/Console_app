package utils;

import dto.*;

import java.util.Scanner;

public class ScriptConsole extends Console {

    private Scanner scriptScanner;

    public void setScanner(Scanner scanner) {
        this.scriptScanner = scanner;
    }

    public Organization readOrganizationFromScript() {
        try {
            String name = readNextLine("Название организации");
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Название не может быть пустым.");
            }

            long coordX = Long.parseLong(readNextLine("Координата X (больше -775)"));
            if (coordX <= -775) throw new IllegalArgumentException("X должен быть больше -775.");

            long coordY = Long.parseLong(readNextLine("Координата Y (больше -734)"));
            if (coordY <= -734) throw new IllegalArgumentException("Y должен быть больше -734.");

            String annualStr = readNextLine("Годовой оборот (может быть пустым)");
            Double annualTurnover = annualStr.isEmpty() ? null : Double.parseDouble(annualStr);
            if (annualTurnover != null && annualTurnover <= 0) {
                throw new IllegalArgumentException("Оборот должен быть больше 0.");
            }
            String typeStr = readNextLine("Тип организации");
            OrganizationType type = OrganizationType.valueOf(typeStr.toUpperCase());

            String street = readNextLine("Улица (officialAddress) может быть пустая");
            Address address = null;
            if (!street.isEmpty()) {

                String townXStr = readNextLine("Город X (может быть пустым)");
                Location town = null;
                if (!townXStr.isEmpty()) {
                    Integer townX = Integer.parseInt(townXStr);
                    int townY = Integer.parseInt(readNextLine("Город Y"));
                    int townZ = Integer.parseInt(readNextLine("Город Z"));
                    String townName = readNextLine("Название города");
                    town = new Location(townX, townY, townZ, townName);
                }
                address = new Address(street, town);
            }

            Coordinates coordinates = new Coordinates(coordX, coordY);

            return new Organization(
                    name,
                    coordinates,
                    annualTurnover,
                    type,
                    address
            );

        } catch (Exception e) {
            System.out.println("Ошибка чтения организации из скрипта: " + e.getMessage());
            return null;
        }
    }

    private String readNextLine(String prompt) {
        if (scriptScanner.hasNextLine()) {
            return scriptScanner.nextLine().trim();
        } else {
            throw new IllegalStateException("Неожиданный конец файла при чтении поля: " + prompt);
        }
    }
}
