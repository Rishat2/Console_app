package commands;

import commands.commandsWithArguments.CommandWithArgument;
import dto.Address;
import dto.Organization;
import utils.MapBuilder;
import utils.ParserCSV;
import utils.ScriptConsole;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

public class Executor {

    private final Vector<Organization> organizations;
    private final ArrayList<String> history = new ArrayList<>();
    private final LocalDateTime dateOfCreation;
    private final Set<String> usedScripts = new HashSet<>();


    public Executor(Vector<Organization> organizations) {
        this.organizations = organizations;
        dateOfCreation = LocalDateTime.now();
    }

    public void executeScript(String filePath) {
        history.add("execute_script");
        if (usedScripts.contains(filePath)) {
            throw new RuntimeException("Скрипт уже выполняется: " + filePath);
        }

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            usedScripts.add(filePath);

            ScriptConsole scriptConsole = new ScriptConsole();
            MapBuilder builder = new MapBuilder();
            Map<String, Command> commands = builder.createMapWithCommands(this);
            int lineNumber = 0;
            while (fileScanner.hasNextLine()) {
                lineNumber++;
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    throw new RuntimeException("Обнаружена пустая строка в файле: " + filePath + " номер строки: " + lineNumber);
                }

                scriptConsole.parseCommand(line);
                String commandName = scriptConsole.getCommand();
                String argument = scriptConsole.getArgument();

                Command command = commands.get(commandName);

                if (!scriptConsole.isValidCommand(commandName)) {
                    throw new RuntimeException("Команда \"" + commandName + "\" не найдена. Файл: " + filePath + " номер строки: " + lineNumber);
                }

                if (commandName.equals("add")) {
                    scriptConsole.setScanner(fileScanner);
                    Organization org = scriptConsole.readOrganizationFromScript();
                    if (org == null) {
                        throw new RuntimeException("Ошибка чтения организации. Файл: " + filePath + " номер строки: " + lineNumber);
                    }
                    this.add(org);
                    continue;
                }

                if (command instanceof CommandWithArgument cmdWithArg) {
                    if (!cmdWithArg.validateAndSetArgument(argument)) {
                        throw new RuntimeException("Неверный аргумент для команды \"" + commandName + "\". Файл: " + filePath + " номер строки: " + lineNumber);
                    }

                    if (commandName.equals("update_by_id")) {
                        scriptConsole.setScanner(fileScanner);
                        Organization org = scriptConsole.readOrganizationFromScript();
                        if (org == null) {
                            throw new RuntimeException("Ошибка чтения организации. Файл: " + filePath + " номер строки: " + lineNumber);
                        }
                            int id = Integer.parseInt(argument);
                            this.updateById(id, org);
                        continue;
                    }

                    command.execute();
                } else {
                    command.execute();
                }
            }
            System.out.println("Выполнение скрипта завершено: " + filePath);
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении скрипта: " + e.getMessage());
            throw new RuntimeException("Прерывание скрипта из-за ошибки: " + filePath);
        }
        finally {
            usedScripts.remove(filePath);
        }
    }

    public void removeAnyByAnnualTurnover(Double annualTurnover) {
        history.add("remove_any_by_annual_turnover");
        for (Organization organization : organizations) {
            if (annualTurnover == null && organization.getAnnualTurnover() == null) {
                organizations.remove(organization);
                System.out.println("Element with annualTurnover = " + null + " successfully deleted from the collection.");
                return;
            }
            if (Objects.equals(organization.getAnnualTurnover(), annualTurnover)) {
                organizations.remove(organization);
                System.out.println("Element with annualTurnover = " + annualTurnover + " successfully deleted from the collection.");
                return;
            }
        }
        System.out.println("Element with annualTurnover = " + annualTurnover + " wasn't found in the collection.");
    }

    public void removeById(int id) {
        history.add("remove_by_id");
        for (Organization organization : organizations) {
            if (organization.getId() == id) {
                organizations.remove(organization);
                System.out.println("Element with id = " + id + " successfully deleted from the collection.");
                return;
            }
        }
        System.out.println("Element with id = " + id + " wasn't found in the collection.");
    }

    public void updateById(int id, Organization organization) {
        history.add("update_by_id");
        for (Organization org : organizations) {
            if (org.getId() == id) {
                organization.setId(id);
                organizations.set(organizations.indexOf(org), organization);
                System.out.println("Element with id " + id + " successfully replaced.");
                return;
            }
        }
        System.out.println("Element with id " + id + " not found in the collection.");
    }

    public void add(Organization organization) {
        history.add("add");
        organizations.add(organization);
        System.out.println("Element successfully added in the collection.");
    }

    public void averageOfAnnualTurnover() {
        history.add("average_of_annual_turnover");
        Double sumOfAnnualTurnover = 0.0;
        for (Organization organization : organizations) {
            if (organization.getAnnualTurnover() != null) {
                sumOfAnnualTurnover += organization.getAnnualTurnover();
            }
        }
        if (sumOfAnnualTurnover == 0.0) {
            System.out.println("It is impossible to calculate the averageOfAnnualTurnover value because all values of annual Turnover are null.");
            return;
        }
        System.out.println("AverageOfAnnualTurnover = " + sumOfAnnualTurnover / organizations.size() + ".");
    }

    public void clear() {
        history.add("clear");
        organizations.clear();
        System.out.println("Collection cleared successfully.");
    }

    public void exit() {
        System.out.println("Program shutdown...");
        System.exit(0);
    }

    public void help() {
        history.add("help");
        System.out.println("help : output help for available commands\n" +
                "info : output information about the collection (type, initialization date, number of elements, etc.) to the standard output stream\n" +
                "show : output all elements of the collection in a string representation\n" +
                "to the standard output stream add {element} : add a new element to the collection\n" +
                "update id {element} : update the value of the element a collection whose id is equal to the specified\n" +
                "remove_by_id id : delete an item from the collection by its id\n" +
                "clear : clear the collection\n" +
                "save : save the collection to a file\n" +
                "execute_script file_name : read and execute the script from the specified file. The script contains commands in the same form as they are entered by the user interactively.\n" +
                "exit : terminate the program (without saving to a file)\n" +
                "remove_first : delete the first item from the collection\n" +
                "shuffle : shuffle the collection items in random order\n" +
                "history : output the last 13 commands (without their arguments)\n" +
                "remove_any_by_annual_turnover annualTurnover : remove one item from the collection, the value of the annualTurnover field of which is equivalent to the specified\n" +
                "average_of_annual_turnover : output the average value of the annualTurnover field for all items in the collection\n" +
                "print_unique_official_address : output the unique values of the officialAddress field of all items in the collection");
    }

    public void history() {
        for (int i = history.size() - 13; i < history.size(); i++) {
            if(i < 0) {
                continue;
            }
            System.out.println((i + 1) + ": " + history.get(i));
        }
        history.add("history");
    }

    public void info() {
        history.add("info");
        System.out.println("type: Vector<Organization>" +
                "\nDate of creation: " + dateOfCreation +
                "\nCount of elements in collection: " + organizations.size());
    }

    public void printUniqueOfficialAddress() {
        history.add("print_unique_official_address");
        Map<Address, Integer> countOfAddresses = new HashMap<>();
        for (Organization organization : organizations) {
            Address address = organization.getOfficialAddress();
            if (address != null) {
                countOfAddresses.put(address, countOfAddresses.getOrDefault(address, 0) + 1);
            }
        }
        List<Address> uniqueAddresses = new ArrayList<>();
        for (Map.Entry<Address, Integer> entry : countOfAddresses.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueAddresses.add(entry.getKey());
            }
        }
        if (uniqueAddresses.isEmpty()) {
            System.out.println("There are no unique addresses.");
        } else {
            System.out.println("Unique addresses:");
            for (Address address : uniqueAddresses) {
                System.out.println(address);
            }
        }
    }

    public void removeFirst() {
        history.add("remove_first");
        organizations.removeFirst();
        System.out.println("First element of collection successfully removed");
    }

    public void save(String filePath) {
        history.add("save");
        ParserCSV parser = new ParserCSV();
        parser.saveToFile(organizations, filePath);
        System.out.println("Collection successfully saved in the file: " + filePath);
    }

    public void show() {
        history.add("show");
        for (Organization organization : organizations) {
            System.out.println(organization);
        }
    }

    public void shuffle() {
        history.add("shuffle");
        Collections.shuffle(organizations);
        System.out.println("Collection successfully shuffled.");
    }

}
