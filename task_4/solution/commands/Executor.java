package commands;

import commands.commandsWithArguments.CommandWithArgument;

public class Executor {

    private final Vector<Organization> organizations;


    public Executor(Vector<Organization> organizations) {
        this.organizations = organizations;
    }

}
