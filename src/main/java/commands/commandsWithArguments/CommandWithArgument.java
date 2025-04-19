package commands.commandsWithArguments;

import commands.Command;

public interface CommandWithArgument extends Command {

    boolean validateAndSetArgument(String argument);

}
