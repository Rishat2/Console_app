package commands;

public class AverageOfAnnualTurnover implements Command{

    private final Executor executor;

    public AverageOfAnnualTurnover(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.averageOfAnnualTurnover();
    }

    @Override
    public String getName() {
        return "average_of_annual_turnover";
    }

}
