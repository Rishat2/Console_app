package commands.commandsWithArguments;

import commands.Executor;

public class ExecuteScript implements CommandWithArgument{

    private final Executor executor;
    private String filePath;

    public ExecuteScript(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        try {
            executor.executeScript(filePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public boolean validateAndSetArgument(String argument) {
        if (argument == null || argument.trim().isEmpty()) {
            System.out.println("Файл не указан.");
            return false;
        }
        this.filePath = argument.trim();
        return true;
    }
}
