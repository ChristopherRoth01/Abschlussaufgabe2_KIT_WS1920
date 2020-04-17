package tasktwo.userinterface.commands;

import java.util.regex.Pattern;

import edu.kit.informatik.Terminal;
import tasktwo.logic.LogicException;

/**
 * Class for the draw Command. The draw Command allows the user to draw Cards
 * from the cardStack of the Game.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class Draw extends Command {

    /**
     * Pattern.
     */
    private final Pattern drawPattern = Pattern.compile("^draw$");

    @Override
    public void execute() {

        try {

            Terminal.printLine(game.drawCard());
        } catch (LogicException e) {
            Terminal.printLine(e.getMessage());
        }

    }

    @Override
    Pattern getPattern() {
        return this.drawPattern;
    }

}
