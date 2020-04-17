package tasktwo.userinterface.commands;

import java.util.regex.Pattern;

import edu.kit.informatik.Terminal;
import tasktwo.logic.LogicException;

/**
 * The Class for the list-buildings Command. This command prints all build
 * Elements of the Player.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class ListBuildings extends Command {
    /**
     * 
     * Pattern.
     */
    private final Pattern listBuildingsPattern = Pattern.compile("^list-buildings$");

    @Override
    public void execute() {

        try {
            Terminal.printLine(game.returnBuildingsToString());
        } catch (LogicException e) {
            Terminal.printLine(e.getMessage());
        }

    }

    @Override
    Pattern getPattern() {
        return this.listBuildingsPattern;

    }

}
