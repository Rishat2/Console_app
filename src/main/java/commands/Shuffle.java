package commands;

public class Shuffle implements Command{

    private final Executor executor;

    public Shuffle(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.shuffle();
    }

    @Override
    public String getName() {
        return "shuffle";
    }

}
