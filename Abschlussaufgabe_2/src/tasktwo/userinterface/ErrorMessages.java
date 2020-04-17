package tasktwo.userinterface;

/**
 * Enum containing all Error Messages.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public enum ErrorMessages {
    /**
     * Error Message, if the input of the user does not match any Commands.
     */
    NOT_MATCHING("Error, wrong input format!"),
    /**
     * Error Message, if the number of Cards is not valid.
     */
    CARDS_NOT_CORRECT("Error, the number of Cards is not valid!"),
    /**
     * Error Message, when user trys to execute a Command that can not be executed
     * in the current Phase.
     */
    UNALLOWED_GAMEPHASE("Error, this command is not allowed in this GamePhase!"),
    /**
     * Error Message, if user trys to build something that can not be build in the
     * current Moment.
     */
    NOT_BUILDABLE("Error, you can´t build this!"),
    /**
     * Error Message, if the card Stack is emtpy and user trys to draw a Card.
     */
    NO_CARDS("Error, no cards on the Stack!"),
    /**
     * Error Message, if user inputs a number out of the integer range.
     */
    NUMBER_TOO_BIG("Error, Not a valid number!"),
    /**
     * Error Message, if user trys to use an unallowed cube.
     */
    UNALLOWED_CUBE("Error, this cube is unallowed!"),
    /**
     * Error Message, if user trys to execute Commands that can only be executed
     * with a started Game.
     */
    GAME_NOT_STARTED("Error, you need to start the Game first!");

    /**
     * The errorMessage which a Enum-object contains.
     */
    private String errorMessage;

    /**
     * Constructor
     * 
     * @param errorMessage the errorMessage of this Enum-Object.
     */
    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Getter for the message.
     * 
     * @return the errorMessage.
     */
    public String getMessage() {
        return this.errorMessage;
    }
}
