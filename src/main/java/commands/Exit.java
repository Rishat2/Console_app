package commands;

public class Exit implements Command{

    private final Executor executor;

    public Exit(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.exit();
    }

    @Override
    public String getName() {
        return "exit";
    }

}
