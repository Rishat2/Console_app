package commands;

public class PrintUniqueOfficialAddress implements Command{

    private final Executor executor;

    public PrintUniqueOfficialAddress(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.printUniqueOfficialAddress();
    }

    @Override
    public String getName() {
        return "print_unique_official_address";
    }

}
