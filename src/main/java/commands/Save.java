package commands;

public class Save implements Command{

    private final Executor executor;

    public Save(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.save("..\\..\\data.csv");
    }

    @Override
    public String getName() {
        return "save";
    }

}
