package utils;

import commands.*;
import commands.commandsWithArguments.ExecuteScript;
import commands.commandsWithArguments.RemoveAnyByAnnualTurnover;
import commands.commandsWithArguments.RemoveById;
import commands.commandsWithArguments.UpdateById;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder {

    public Map<String, Command> createMapWithCommands(Executor executor) {
        Map<String, Command> commands = new HashMap<>();
        Command executeScript = new ExecuteScript(executor);
        Command removeAnyByAnnualTurnover = new RemoveAnyByAnnualTurnover(executor);
        Command removeById = new RemoveById(executor);
        Command updateById = new UpdateById(executor);
        Command add = new Add(executor);
        Command averageOfAnnualTurnover = new AverageOfAnnualTurnover(executor);
        Command clear = new Clear(executor);
        Command exit = new Exit(executor);
        Command help = new Help(executor);
        Command history = new History(executor);
        Command info = new Info(executor);
        Command printUniqueOfficialAddress = new PrintUniqueOfficialAddress(executor);
        Command removeFirst = new RemoveFirst(executor);
        Command save = new Save(executor);
        Command show = new Show(executor);
        Command shuffle = new Shuffle(executor);
        commands.put(executeScript.getName(), executeScript);
        commands.put(removeAnyByAnnualTurnover.getName(), removeAnyByAnnualTurnover);
        commands.put(removeById.getName(), removeById);
        commands.put(updateById.getName(), updateById);
        commands.put(add.getName(), add);
        commands.put(averageOfAnnualTurnover.getName(), averageOfAnnualTurnover);
        commands.put(clear.getName(), clear);
        commands.put(exit.getName(), exit);
        commands.put(help.getName(), help);
        commands.put(history.getName(), history);
        commands.put(info.getName(), info);
        commands.put(printUniqueOfficialAddress.getName(), printUniqueOfficialAddress);
        commands.put(removeFirst.getName(), removeFirst);
        commands.put(save.getName(), save);
        commands.put(show.getName(), show);
        commands.put(shuffle.getName(), shuffle);
        return commands;
    }

}
