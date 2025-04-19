import commands.Command;
import commands.Executor;
import commands.commandsWithArguments.CommandWithArgument;
import dto.IdGenerator;
import dto.Organization;
import utils.Console;
import utils.MapBuilder;
import utils.ParserCSV;

import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        if (args.length == 0) {
            console.println("Specify the path to the CSV file as a command line argument.");
            return;
        }
        String filePath = args[0];
        ParserCSV parser = new ParserCSV();
        Vector<Organization> organizations = parser.loadFromFile(filePath);
        IdGenerator.setId(organizations.size() + 1);
        MapBuilder mapBuilder = new MapBuilder();
        Executor executor =new Executor(organizations);
        Map<String, Command> commands = mapBuilder.createMapWithCommands(executor);
        console.println("Введите команду ниже, список возможных команд (чтобы его вывести введите help): ");
        console.printCommands();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            console.println("");
            console.print(">>> ");
            String input = scanner.nextLine().trim();
            console.println("");
            console.parseCommand(input);
            String commandName = console.getCommand();
            if (!console.isValidCommand(commandName)) {
                console.println("Такой команды нет!");
                continue;
            }
            Command command = commands.get(commandName);
            if (command instanceof CommandWithArgument) {
                String argument = console.getArgument();
                if (!((CommandWithArgument) command).validateAndSetArgument(argument)) {
                    continue;
                }
                command.execute();
                continue;
            }
            command.execute();
        }
    }
}