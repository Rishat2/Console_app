package commands;

public class Help implements Command{

    private final Executor executor;

    public Help(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.help();
    }

    @Override
    public String getName() {
        return "help";
    }

}
