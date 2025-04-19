package commands.commandsWithArguments;

import commands.Executor;

import java.util.Objects;

public class RemoveAnyByAnnualTurnover implements CommandWithArgument {
    private final Executor executor;
    private Double annualTurnover;

    public RemoveAnyByAnnualTurnover(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.removeAnyByAnnualTurnover(annualTurnover);
    }

    @Override
    public String getName() {
        return "remove_any_by_annual_turnover";
    }

    @Override
    public boolean validateAndSetArgument(String argument) {
        if (Objects.equals(argument, "null")) {
            annualTurnover = null;
            return true;
        }
        else {
            try {
                annualTurnover = Double.parseDouble(argument);
                return true;
            } catch (Exception e) {
                System.err.println("Incorrect argument, annual turnover must be a number!");
                return false;
            }
        }
    }
}
