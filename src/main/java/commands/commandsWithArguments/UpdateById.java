package commands.commandsWithArguments;

import commands.Executor;
import dto.Organization;
import utils.Console;

public class UpdateById implements CommandWithArgument {

    private final Executor executor;
    private final Console console = new Console();
    private int id;

    public UpdateById(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        Organization organization = console.readElement();
        executor.updateById(id, organization);
    }

    @Override
    public String getName() {
        return "update_by_id";
    }

    @Override
    public boolean validateAndSetArgument(String argument) {
        try {
            id = Integer.parseInt(argument);
            return true;
        } catch (Exception e) {
            System.err.println("Incorrect argument, id must be a integer!");
            return false;
        }
    }

}
