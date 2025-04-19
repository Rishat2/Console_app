package commands;

public class Info implements Command{

    private final Executor executor;

    public Info(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.info();
    }

    @Override
    public String getName() {
        return "info";
    }

}
