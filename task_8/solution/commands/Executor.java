package commands;

import commands.commandsWithArguments.CommandWithArgument;

public class Executor {

    private final Vector<Organization> organizations;


    public Executor(Vector<Organization> organizations) {
        this.organizations = organizations;
    }

    public void clear() {
        organizations.clear();
        System.out.println("Collection cleared successfully.");
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

    public void exit() {
        System.out.println("Program shutdown...");
        System.exit(0);
    }



}
