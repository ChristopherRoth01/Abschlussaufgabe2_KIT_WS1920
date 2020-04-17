package tasktwo.userinterface.commands;

import java.util.regex.Pattern;

import edu.kit.informatik.Terminal;
import tasktwo.logic.LogicException;

/**
 * The list-resources Command. Prints the List of all drawn Resources.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class ListResources extends Command {

    /**
     * Pattern.
     */
    private Pattern listResourcesPattern = Pattern.compile("^list-resources$");

    @Override
    public void execute() {

        try {
            Terminal.printLine(game.drawCardsToString());
        } catch (LogicException e) {
            Terminal.printLine(e.getMessage());
        }

    }

    @Override
    Pattern getPattern() {
        return listResourcesPattern;
    }

}
