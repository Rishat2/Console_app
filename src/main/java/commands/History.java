package commands;

public class History implements Command{

    private final Executor executor;

    public History(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.history();
    }

    @Override
    public String getName() {
        return "history";
    }

}
