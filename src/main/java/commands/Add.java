package commands;

import dto.Organization;
import utils.Console;

public class Add implements Command{

    private final Executor executor;
    private final Console console = new Console();

    public Add(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        Organization organization = console.readElement();
        executor.add(organization);
    }

    @Override
    public String getName() {
        return "add";
    }

}
