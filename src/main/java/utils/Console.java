package utils;

import dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<String> commands = new ArrayList<>(List.of(
            "help",
            "info",
            "show",
            "add",
            "update_by_id",
            "remove_by_id",
            "clear",
            "save",
            "execute_script",
            "exit",
            "remove_first",
            "shuffle",
            "history",
            "remove_any_by_annual_turnover",
            "average_of_annual_turnover",
            "print_unique_official_address"
    ));
    private String command;
    private String argument;

    public void println(String output) {
        System.out.println(output);
    }

    public void print(String output) {
        System.out.print(output);
    }
    
    public void printCommands() {
        for (String command : commands) {
            println("- " + command);
        }
    }

    public void parseCommand(String input) {
        String[] parts = input.split(" ", 2);
        command = parts[0];
        argument = (parts.length > 1) ? parts[1] : null;
    }

    public boolean isValidCommand(String command) {
        return commands.contains(command);
    }

    public String getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }

    public Organization readElement() {
            String name = promptNonEmptyString("Введите название организации:");
            Coordinates coordinates = readCoordinates();
            Double annualTurnover = promptNullablePositiveDouble("Введите ежегодный оборот (положительное число, это поле можно оставить пустым):");
            OrganizationType type = promptEnum("Введите тип организации:");
            Address address = readAddress();

            return new Organization(name, coordinates, annualTurnover, type, address);
        }

        private String promptNonEmptyString(String prompt) {
            String input;
            while (true) {
                println(prompt);
                input = scanner.nextLine().trim();
                if (!input.isEmpty()) return input;
                println("Имя не может быть пустой строкой, повторите ввод.");
            }
        }

        private Coordinates readCoordinates() {
            long x = promptLong("Введите координату X (число > -775):", -775L);
            long y = promptLong("Введите координату Y (число > -734):", -734L);
            return new Coordinates(x, y);
        }

        private long promptLong(String prompt, long minValue) {
            while (true) {
                try {
                    println(prompt);
                    long value = Long.parseLong(scanner.nextLine().trim());
                    if (value > minValue) return value;
                    println("Значение должно быть больше " + minValue + ", повторите ввод.");
                } catch (NumberFormatException e) {
                    println("Значение должно быть целым числом, большим " + minValue + ", повторите ввод");
                }
            }
        }

        private Double promptNullablePositiveDouble(String prompt) {
            while (true) {
                println(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) return null;
                try {
                    double value = Double.parseDouble(input.trim());
                    if (value > 0) return value;
                    println("Значение ежегодного оборота должно быть больше 0.");
                } catch (NumberFormatException e) {
                    println("Значение ежегодного оборота должно быть положительным числом, повторите ввод.");
                }
            }
        }

        private OrganizationType promptEnum(String prompt) {
            println(prompt);
            for (OrganizationType type : OrganizationType.values()) {
                println("- " + type);
            }

            while (true) {
                String input = scanner.nextLine().trim().toUpperCase();
                try {
                    return OrganizationType.valueOf(input);
                } catch (IllegalArgumentException e) {
                    println("Значение должно быть одним из указанных, повторите ввод.");
                }
            }
        }

        private Address readAddress() {
            while (true) {
                println("Хотите ввести адресс? (yes/no):");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (choice.equals("no") || choice.isEmpty()) {
                    return null;
                }
                if (choice.equals("yes")) {
                    break;
                }
            }
            println("Введите улицу:");
            String street;
            while (true) {
                street = scanner.nextLine().trim();
                if (!street.isEmpty()) break;
                println("Название улицы не может быть пустой строкой, повторите ввод.");
            }
            while (true) {
                println("Хотите ввести город? (yes/no):");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (choice.equals("no") || choice.isEmpty()) {
                    return new Address(street, null);
                }
                if (choice.equals("yes")) {
                    break;
                }
            }

            Location town = readLocation();
            return new Address(street, town);
        }

        private Location readLocation() {
            Integer x = promptNonNullInteger("Введите координату города X (целое число):");
            int y = promptInt("Введите координату города Y:");
            int z = promptInt("Введите координату города Z:");
            String name = promptNonEmptyString("Введите название города:");

            return new Location(x, y, z, name);
        }

        private Integer promptNonNullInteger(String prompt) {
            while (true) {
                try {
                    println(prompt);
                    return Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    println("Значение должно быть целым числом, повторите ввод.");
                }
            }
        }

        private int promptInt(String prompt) {
            while (true) {
                try {
                    println(prompt);
                    return Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    println("Значение должно быть целым числом, повторите ввод.");
                }
            }
        }
    }
