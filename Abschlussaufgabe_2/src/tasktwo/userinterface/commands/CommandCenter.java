package tasktwo.userinterface.commands;

import java.util.ArrayList;
import java.util.List;

import tasktwo.userinterface.ErrorMessages;
import tasktwo.userinterface.InputException;
import tasktwo.userinterface.Session;

/**
 * Works as the Invoker Class of the Java Command Pattern. The CommandCenter
 * handles all Commands. Matches input Strings to the fitting Patterns. Then
 * creates a new Instance of the fitting Command class and executes it.
 * 
 * @author Christopher Roth
 * @version 1.0
 */
public class CommandCenter {

    private final List<Command> commandPackage = new ArrayList<Command>();

    private final Session session;

    /**
     * Constructor.
     * 
     * @param session the Session to be initiated.
     */
    public CommandCenter(final Session session) {

        commandPackage.add(new Quit());
        commandPackage.add(new ListResources());
        commandPackage.add(new Start());
        commandPackage.add(new Build());
        commandPackage.add(new Draw());
        commandPackage.add(new Reset());
        commandPackage.add(new ListBuildings());
        commandPackage.add(new BuildQuestion());
        commandPackage.add(new RollDice());
        this.session = session;
    }

    /**
     * Method that prepares the command to be executed.
     * 
     * @param input the String input to be matched against a Pattern.
     * @return the fitting Command Class.
     * @throws InputException if the String does not match.
     */
    public Command getCommand(final String input) throws InputException {

        Command matchingCommand = commandPackage.stream().filter(s -> s.getPattern().matcher(input).matches())
                .findFirst().orElse(null);
        if (matchingCommand == null) {
            throw new InputException(ErrorMessages.NOT_MATCHING.getMessage().toString());
        }

        matchingCommand.setArguments(input);
        matchingCommand.setSession(session);

        return matchingCommand;
    }
}