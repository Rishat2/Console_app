package utils;

import dto.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class ParserCSV {

    public Vector<Organization> loadFromFile(String filePath) {
        Vector<Organization> organizations = new Vector<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",", -1);

                if (parts.length != 10) {
                    System.err.println("There should be 10 data points: " + line);
                    continue;
                }

                try {
                    String name = parts[0].trim();
                    long x = Long.parseLong(parts[1].trim());
                    long y = Long.parseLong(parts[2].trim());
                    Double annualTurnover = parts[3].isEmpty() ? null : Double.parseDouble(parts[3].trim());
                    OrganizationType type = OrganizationType.valueOf(parts[4].trim());
                    Address address;
                    if (Objects.equals(parts[5], "")) {
                        address = null;
                    }
                    else {
                        String street = parts[5].trim();
                        if (Objects.equals(parts[6], "")) {
                            address = new Address(street, null);
                        }
                        else {
                            Integer townX = parts[6].isEmpty() ? null : Integer.parseInt(parts[6].trim());
                            int townY = Integer.parseInt(parts[7].trim());
                            int townZ = Integer.parseInt(parts[8].trim());
                            String townName = parts[9].trim();
                            Location town = new Location(townX, townY, townZ, townName);
                            address = new Address(street, town);
                        }
                    }


                    Coordinates coordinates = new Coordinates(x, y);
                        Organization organization = new Organization(name, coordinates, annualTurnover, type, address);
                        organizations.add(organization);
                } catch (IllegalArgumentException e) {
                    System.err.println("Error in the string: " + line);
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    System.err.println("Error processing the string: " + line);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }

        return organizations;
    }

    public void saveToFile(Vector<Organization> collection, String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {

            writer.println("name,x,y,annualTurnover,type,street,townX,townY,townZ,townName");

            for (Organization organization : collection) {
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(escape(organization.getName())).append(",");
                stringBuilder.append(organization.getCoordinates().getX()).append(",");
                stringBuilder.append(organization.getCoordinates().getY()).append(",");
                stringBuilder.append(organization.getAnnualTurnover() != null ? organization.getAnnualTurnover() : "").append(",");
                stringBuilder.append(organization.getType().name()).append(",");

                Address address = organization.getOfficialAddress();
                if (address != null) {
                    stringBuilder.append(escape(address.getStreet())).append(",");
                    Location town = address.getTown();
                    if (town != null) {
                        stringBuilder.append(town.getX() != null ? town.getX() : "").append(",");
                        stringBuilder.append(town.getY()).append(",");
                        stringBuilder.append(town.getZ()).append(",");
                        stringBuilder.append(escape(town.getName()));
                    } else {
                        stringBuilder.append(",,,");
                    }
                } else {
                    stringBuilder.append(",,,,");
                }

                writer.println(stringBuilder);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
    }

    private String escape(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }

}
