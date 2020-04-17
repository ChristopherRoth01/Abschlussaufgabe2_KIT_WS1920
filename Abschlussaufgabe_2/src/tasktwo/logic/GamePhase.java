package tasktwo.logic;

/**
 * Enum that contains all possible GamePhases.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public enum GamePhase {

    /**
     * The first Phase. In this Phase the Player can draw Cards.
     */
    SCAVENGE,
    /**
     * The phase which is entered when a animal Card is drawn.
     */
    ENCOUNTER,
    /**
     * The phase that is entered when a sailingraft or hangglider is build.
     */
    ENDEAVOR,
    /**
     * The phase which is entered when the Player wins or loses.
     */
    END,
    /**
     * The phase before the Game is started.
     */
    START;
}
