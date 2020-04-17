package tasktwo.logic;

import java.util.List;
import java.util.Stack;

import tasktwo.userinterface.ErrorMessages;

/**
 * Represents a CardStack of which the Player can draw Cards. A CardStack holds
 * 64 cards. 16 of each resource, 5 of each animal and 1 catastrophe card. The
 * order of the cards is set when starting the game.
 * 
 * @author Christopher Roth
 * @version 1.
 *
 */
public class CardStack {

    /**
     * The allowed number of resource Cards in a stack.
     */
    private final int resourceCount = 16;
    /**
     * The allowed number of animal Cards in a stack.
     */
    private final int animalCount = 5;
    /**
     * The allowed number of catastrophe Cards in a stack.
     */
    private final int catastropheCount = 1;
    /**
     * the defaul cardStack the Player draws Cards from.
     */
    private final Stack<Card> cardStack;
    /**
     * stack that is used to restore the cardStack.
     * 
     */
    private final Stack<Card> cardStackToStart;

    /**
     * Constructor.
     * 
     * @param drawables the List of drawables with which the cardStack should be
     *                  initiated.
     * @throws LogicException if the number of Cards is incorrect.
     */
    CardStack(List<Card> drawables) throws LogicException {
        this.cardStack = new Stack<Card>();
        this.cardStackToStart = new Stack<Card>();
        if (!this.validCardStack(drawables)) {
            throw new LogicException(ErrorMessages.CARDS_NOT_CORRECT.getMessage());
        }
        this.addCards(drawables);
        this.addDepositCards(drawables);
    }

    /**
     * Draws a Card from the cardStack.
     * 
     * @return the drawn Card.
     * @throws LogicException if stack is empty.
     */
    public Card drawCard() throws LogicException {
        if (!this.cardStack.isEmpty()) {
            return this.cardStack.pop();
        } else {
            throw new LogicException(ErrorMessages.NO_CARDS.getMessage());
        }
    }

    /**
     * Adds a Card to the cardStack.
     * 
     * @param drawable the drawable to be added.
     */
    public void addCard(Card drawable) {
        this.cardStack.push(drawable);
    }

    /**
     * Adds a List of Cards to the cardStack.
     * 
     * @param drawables the list to be added.
     */
    public void addCards(List<Card> drawables) {
        for (Card drawable : drawables) {
            this.cardStack.push(drawable);

        }
    }

    /**
     * Checks if cardStack is empty.
     * 
     * @return true if empty, false if not.
     */
    public boolean isEmpty() {
        if (cardStack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Resets the cardStack to its starting Phase.
     */
    public void reset() {
        this.cardStack.clear();
        for (Card drawable : this.cardStackToStart) {
            this.cardStack.push(drawable);
        }

    }

    /**
     * Adds Cards to the cardStackToStart. This stack is used to restore the default
     * stack when the reset command is executed.
     * 
     * @param drawables a List which contains the drawables that should be added.
     */
    public void addDepositCards(List<Card> drawables) {
        for (Card drawable : drawables) {
            this.cardStackToStart.push(drawable);

        }
    }

    /**
     * Method that clears both stacks.
     */
    public void clear() {
        this.cardStack.clear();
        this.cardStackToStart.clear();

    }

    /**
     * Checks if the Card Stack has a valid number of each Card.
     * 
     * @param drawables the list of drawables that should be checked.
     * @return true if correct, false if not.
     */
    private boolean validCardStack(List<Card> drawables) {
        int woodCounter = 0;
        int metalCounter = 0;
        int plasticCounter = 0;
        int spiderCounter = 0;
        int snakeCounter = 0;
        int tigerCounter = 0;
        int catastropheCounter = 0;
        for (Card drawable : drawables) {
            switch (drawable.getDrawable()) {
                case WOOD:
                    woodCounter++;
                    break;
                case METAL:
                    metalCounter++;
                    break;
                case PLASTIC:
                    plasticCounter++;
                    break;
                case SPIDER:
                    spiderCounter++;
                    break;
                case TIGER:
                    tigerCounter++;
                    break;
                case SNAKE:
                    snakeCounter++;
                    break;
                case THUNDERSTORM:
                    catastropheCounter++;
                    break;
                default:
                    break;
            }
        }

        return woodCounter == resourceCount && metalCounter == resourceCount && plasticCounter == resourceCount
                && spiderCounter == animalCount && tigerCounter == animalCount && snakeCounter == animalCount
                && catastropheCounter == catastropheCount;

    }
}
