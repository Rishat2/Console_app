package commands;

public class RemoveFirst implements Command{

    private final Executor executor;

    public RemoveFirst(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.removeFirst();
    }

    @Override
    public String getName() {
        return "remove_first";
    }

}
