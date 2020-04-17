package tasktwo.userinterface.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.kit.informatik.Terminal;
import tasktwo.logic.Card;
import tasktwo.logic.Drawables;
import tasktwo.logic.LogicException;

/**
 * 
 * Class for the start-Command. The start Command starts a Game. The argument is
 * a List of Cards which should be used as starting stack.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class Start extends Command {

    /**
     * Pattern.
     */
    private Pattern startPattern = Pattern.compile("start (((wood|metal|plastic|spider|snake|tiger|thunderstorm),){63}"
            + "(wood|metal|plastic|spider|snake|tiger|thunderstorm))");
    /**
     * Argument.
     */
    private List<Card> cards;

    @Override
    public void execute() {
        try {
            Collections.reverse(cards);
            Terminal.printLine(this.game.start(cards));

        } catch (LogicException e) {
            Terminal.printLine(e.getMessage());
        }
    }

    @Override
    Pattern getPattern() {
        return startPattern;
    }

    @Override
    void setArguments(String argument) {
        Matcher matcher = startPattern.matcher(argument);
        cards = new ArrayList<Card>();
        if (matcher.matches()) {

            String[] cardsInput = matcher.group(1).split(",");

            for (int i = 0; i < cardsInput.length; i++) {
                cards.add(new Card(Drawables.convertToDrawable(cardsInput[i]), i));

            }
        }
    }

}
