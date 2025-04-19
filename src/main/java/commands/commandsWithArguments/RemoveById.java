package commands.commandsWithArguments;

import commands.Executor;

public class RemoveById implements CommandWithArgument {

    private final Executor executor;
    private int id;

    public RemoveById(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.removeById(id);
    }

    @Override
    public String getName() {
        return "remove_by_id";
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
