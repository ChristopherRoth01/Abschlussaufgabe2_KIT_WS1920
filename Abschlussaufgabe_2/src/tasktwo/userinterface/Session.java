package tasktwo.userinterface;

import edu.kit.informatik.Terminal;
import tasktwo.logic.Game;
import tasktwo.userinterface.commands.Command;
import tasktwo.userinterface.commands.CommandCenter;

/**
 * Class for a Session. A Session combines the UserInterface with the Game.
 * Handles the Terminal input and executes the fitting Command, which is
 * returned by the CommandCenter.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class Session {
    /**
     * Boolean for the while(true) loop in the run() method. Is set to false if exit
     * is typed in the Terminal.
     */
    private boolean finished = false;
    /**
     * A ModelRailWay.
     */
    private Game game;

    /**
     * Constructor, which initiates a ModelRailWay.
     */
    public Session() {
        game = new Game();
    }

    /**
     * The method which is executed in the main-Class. Handles the Terminal input
     * and executes the fitting Command, which is returned by the CommandCenter.
     *
     */
    public void run() {
        CommandCenter center = new CommandCenter(this);

        while (!finished) {
            try {
                Command command = center.getCommand(Terminal.readLine());
                command.execute();
            } catch (InputException i) {
                Terminal.printLine(i.getMessage());
            }
        }
    }

    /**
     * The method which sets finished to true, which leads to a termination of the
     * program.
     */
    public void quit() {
        finished = true;
    }

    /**
     * Getter for the Game
     * 
     * @return the Game of the Session.
     */
    public Game getGame() {
        return this.game;
    }
}
