package commands;

public class Show implements Command{

    private final Executor executor;

    public Show(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.show();
    }

    @Override
    public String getName() {
        return "show";
    }

}
