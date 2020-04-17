package tasktwo.userinterface.commands;

import java.util.regex.Pattern;

import edu.kit.informatik.Terminal;
import tasktwo.logic.LogicException;

/**
 * Class for the Reset-Command. Command sets a game in its starting State, which
 * means all build Elements are removed and the cardStack is refilled.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class Reset extends Command {

    /**
     * Pattern.
     */
    private final Pattern resetPattern = Pattern.compile("^reset$");

    @Override
    public void execute() {

        try {
            Terminal.printLine(game.reset());
        } catch (LogicException e) {
            Terminal.printLine(e.getMessage());
        }

    }

    @Override
    Pattern getPattern() {
        return this.resetPattern;
    }

}
