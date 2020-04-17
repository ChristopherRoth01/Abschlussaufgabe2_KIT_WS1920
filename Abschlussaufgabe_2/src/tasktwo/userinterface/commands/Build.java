package tasktwo.userinterface.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.kit.informatik.Terminal;
import tasktwo.logic.LogicException;

/**
 * Class for the Build Command. The build command allows the User to build
 * different Buildables.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class Build extends Command {

    /**
     * Pattern
     */
    private final Pattern buildPattern = Pattern
            .compile("build (axe|club|shack|fireplace|sailingraft|hangglider|steamboat|ballon)");

    /**
     * The argument of the command.
     */
    private String argument;

    @Override
    public void execute() {

        try {
            Terminal.printLine(game.build(argument));
        } catch (LogicException e) {
            Terminal.printLine(e.getMessage());
        }

    }

    @Override
    void setArguments(String argument) {
        Matcher matcher = buildPattern.matcher(argument);

        if (matcher.matches()) {
            this.argument = matcher.group(1);
        }
    }

    @Override
    Pattern getPattern() {
        return this.buildPattern;
    }

}
