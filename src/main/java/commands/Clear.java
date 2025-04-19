package commands;

public class Clear implements Command{

    private final Executor executor;

    public Clear(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.clear();
    }

    @Override
    public String getName() {
        return "clear";
    }

}
