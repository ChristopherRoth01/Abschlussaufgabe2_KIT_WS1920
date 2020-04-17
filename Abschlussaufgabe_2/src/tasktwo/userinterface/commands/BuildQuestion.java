package tasktwo.userinterface.commands;

import java.util.regex.Pattern;

import edu.kit.informatik.Terminal;
import tasktwo.logic.LogicException;

/**
 * Class for the build? command. The build? Command returns a List of all
 * Buildables that can be build in the situation the method is called.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class BuildQuestion extends Command {

    /**
     * Pattern.
     */
    private final Pattern buildQuestionPattern = Pattern.compile("^build[?]$");

    @Override
    public void execute() {

        try {
            Terminal.printLine(game.possibleBuildingsToString());
        } catch (LogicException e) {
            Terminal.printLine(e.getMessage());
        }

    }

    @Override
    Pattern getPattern() {
        return buildQuestionPattern;
    }

}
