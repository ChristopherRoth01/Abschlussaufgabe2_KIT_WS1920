package tasktwo.userinterface.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.kit.informatik.Terminal;
import tasktwo.logic.Cube;
import tasktwo.logic.LogicException;
import tasktwo.userinterface.ErrorMessages;

/**
 * Class for the RollDice Command. Allows the user to input different forms of
 * dices.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class RollDice extends Command {

    /**
     * 
     * Pattern.
     */
    private final Pattern rollDicePattern = Pattern.compile("^rollD" + "([/+]?\\d+)" + " " + "([/+]?\\d+)$");

    /**
     * 
     * Arguments.
     */
    private final Map<Integer, Integer> number = new HashMap<Integer, Integer>();

    @Override
    public void execute() {

        try {
            Terminal.printLine(game.convertDice(new Cube(number.get(0), number.get(1))));
        } catch (LogicException e) {
            Terminal.printLine(e.getMessage());
        }

    }

    @Override
    void setArguments(String argument) {
        Matcher matcher = rollDicePattern.matcher(argument);
        if (matcher.matches()) {
            try {
                number.put(0, Integer.parseInt(matcher.group(1)));
                number.put(1, Integer.parseInt(matcher.group(2)));
            } catch (NumberFormatException e) {
                Terminal.printLine(ErrorMessages.NUMBER_TOO_BIG.getMessage());
            }
        }
    }

    @Override
    Pattern getPattern() {
        return this.rollDicePattern;
    }

}
