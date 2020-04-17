package tasktwo.logic;

import java.util.List;

import tasktwo.userinterface.ErrorMessages;

/**
 * The game class is the class that unites a Player with a CardStack. A Game has
 * different Phases which are determined during the commands depending on drawn
 * Cards and build Elements.
 * 
 *
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class Game {
    /**
     * Some String messages for the output in the Terminal.
     */
    private final String ok = "OK";
    private final String win = "win";
    private final String survived = "survived";
    private final String lost = "lost";
    private final String lose = "lose";
    /**
     * The Player of the Game.
     */
    private Player player;
    /**
     * The cardStack of the Game.
     */
    private CardStack cardStack;
    /**
     * The current phase.
     */
    private GamePhase phase;

    /**
     * Constructor.
     */
    public Game() {
        player = new Player();
        this.phase = GamePhase.START;

    }

    /**
     * The method that draws a Card and determines the GamePhase depending on the
     * Card drawn.
     * 
     * @return the name of the drawn Card. Eventually there is a "lost" added if the
     *         cardStack is empty and there are no more Buildables to build.
     * @throws LogicException if the CardStack is empty, or if method is called in
     *                        wrong GamepPhase.
     */
    public String drawCard() throws LogicException {
        if (!this.phase.equals(GamePhase.SCAVENGE)) {
            throw new LogicException(ErrorMessages.UNALLOWED_GAMEPHASE.getMessage());

        }
        Card card = cardStack.drawCard();
        player.addCard(card);
        if (card.getDrawable().isAnimal()) {

            phase = GamePhase.ENCOUNTER;
            return card.getDrawable().getMessage();

        } else if (card.getDrawable().isCatastrophe()) {

            this.player.catastrophe();
            // Checks if the CardStack is empty, if so the Game is completely lost.
            if (this.cardStack.isEmpty()) {

                this.endLost();
                return card.getDrawable().getMessage() + "\n" + this.lost;
            } else {
                return card.getDrawable().getMessage();
            }

        } else if (card.getDrawable().isResource()) {
            // Checks if the CardStack is empty and no more Buildings can be build, if so
            // the Game is completely lost.
            if (this.cardStack.isEmpty() && this.player.hasNoMoreBuildingsToBuild()) {
                this.endLost();
                return card.getDrawable().getMessage() + "\n" + this.lost;
            } else if (this.cardStack.isEmpty() && !this.player.hasNoMoreBuildingsToBuild()) {
                return card.getDrawable().getMessage();
            } else {

                phase = GamePhase.SCAVENGE;
                return card.getDrawable().getMessage();
            }

        }
        return null;

    }

    /**
     * Method that is called when player loses the game. Sets the game in the
     * END-Phase.
     */
    private void endLost() {
        phase = GamePhase.END;

    }

    /**
     * Getter for the Player.
     * 
     * @return the player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Resets a Game. Therefore the phase is set to SCAVENGE the cardStack is set to
     * the first state and a new Player is initialised.
     * 
     * @return "OK"
     * @throws LogicException if called in the wrong GamePhase.
     */
    public String reset() throws LogicException {
        if (this.phase.equals(GamePhase.START)) {
            throw new LogicException(ErrorMessages.UNALLOWED_GAMEPHASE.getMessage());
        }
        phase = GamePhase.SCAVENGE;
        player = new Player();
        cardStack.reset();
        return this.ok;
    }

    /**
     * Starts a Game. Gets a List of drawables as starting Card Stack.
     * 
     * @param drawables the list of the drawables for the card Stack
     * @return "OK" if everything went alright.
     * @throws LogicException if list has the wrong number of each card. And throws
     *                        Exception if called in wrong GamePhase.
     */
    public String start(List<Card> drawables) throws LogicException {

        if (!(this.phase.equals(GamePhase.START) || this.phase.equals(GamePhase.END))) {
            throw new LogicException(ErrorMessages.UNALLOWED_GAMEPHASE.getMessage());
        }
        cardStack = new CardStack(drawables);
        this.player.clearBuildings();
        this.player.removeDrawnCards();
        this.phase = GamePhase.SCAVENGE;
        return this.ok;
    }

    /**
     * Getter for the current phase.
     * 
     * @return the current GamePhase.
     */
    public GamePhase getPhase() {
        return phase;
    }

    /**
     * Builds the building fitting to the given String. Depending on the build
     * Element the current GamePhase is changed.
     * 
     * @param argument the Buildable that should be build.
     * @return "OK" if everything went alright and the game continues, "win" if the
     *         build Elements is a direct Endeavor, "OK\nlost" if cardStack is empty
     *         and no more things can be build.
     * @throws LogicException if there is no fitting Buildable to the given String,
     *                        or when called in the wrong GamePhase
     */
    public String build(String argument) throws LogicException {

        if (!this.phase.equals(GamePhase.SCAVENGE)) {
            throw new LogicException(ErrorMessages.UNALLOWED_GAMEPHASE.getMessage());
        }
        String toReturn;
        this.player.build(argument);
        if (Buildables.convertToBuildable(argument).isDirectEndeavor()) {
            phase = GamePhase.END;
            toReturn = this.win;
        } else if (Buildables.convertToBuildable(argument).isEndeavor()) {
            phase = GamePhase.ENDEAVOR;
            toReturn = this.ok;
        } else if (this.cardStack.isEmpty() && this.player.hasNoMoreBuildingsToBuild()) {
            this.endLost();
            toReturn = this.ok + "\n" + this.lost;
        } else if (this.cardStack.isEmpty() && this.player.hasNoMoreBuildingsToBuild()) {
            toReturn = this.ok;

        } else {
            toReturn = this.ok;
        }
        return toReturn;

    }

    /**
     * Method that converts a cube into the fitting Actions.
     * 
     * @param cube the cube that should be converted.
     * @return "survived" if animal attack is survived, "lose" if not or if a
     *         Endeavor fails. "lost" is returned additionaly if the User loses the
     *         whole Game.
     * @throws LogicException if a Cube is not matching the last Card, or when
     *                        called in the wrong GamePhase.
     */
    public String convertDice(Cube cube) throws LogicException {
        String toReturn;
        if (phase.equals(GamePhase.ENCOUNTER) && player.getLastDrawnCard().getDrawable().isAnimal()) {
            cube.applyBonus(player);
            if (!cube.match(player.getLastDrawnCard())) {
                throw new LogicException(ErrorMessages.UNALLOWED_CUBE.getMessage());
            }
            if (cube.getCubeNumber() > cube.getNecessaryNumber(player.getLastDrawnCard())) {
                if (this.cardStack.isEmpty() && this.player.hasNoMoreBuildingsToBuild()) {

                    this.endLost();
                    toReturn = this.lost;
                } else {
                    phase = GamePhase.SCAVENGE;
                    toReturn = this.survived;
                }
            } else {
                if (this.cardStack.isEmpty() && this.player.hasNoMoreBuildingsToBuild()) {
                    this.endLost();
                    toReturn = this.lose + "\n" + this.lost;
                } else {
                    phase = GamePhase.SCAVENGE;
                    this.animalLost();
                    toReturn = this.lose;
                }
            }

        } else if (phase.equals(GamePhase.ENDEAVOR) && cube.isTypeSix()) {
            final int numberToWin = 4;
            if (cube.getCubeNumber() < numberToWin) {
                if (this.cardStack.isEmpty() && this.player.hasNoMoreBuildingsToBuild()) {
                    this.endLost();
                    toReturn = this.lose + "\n" + this.lost;
                } else {
                    phase = GamePhase.SCAVENGE;
                    toReturn = this.lose;
                }
            } else {
                phase = GamePhase.END;
                toReturn = this.win;
            }

        } else {
            throw new LogicException(ErrorMessages.UNALLOWED_GAMEPHASE.getMessage());
        }
        return toReturn;
    }

    /**
     * Method that is called when Player loses in an animal Attack. Sets the
     * GamePhase back to SCAVENGE, removes all Cards the Player drew and does the
     * shack Logic.
     */
    private void animalLost() {
        phase = GamePhase.SCAVENGE;
        this.player.fillShack();
        this.player.removeDrawnCards();
        this.player.addCardsFromShack();
    }

    /**
     * Returns all possible Buildings of the Player to String. Throws Exception if
     * called in the wrong GamePhase.
     * 
     * @return a String of all possible Builds.
     * @throws LogicException if called in the wrong GamePhase.
     */
    public String possibleBuildingsToString() throws LogicException {
        if (!this.phase.equals(GamePhase.SCAVENGE)) {
            throw new LogicException(ErrorMessages.UNALLOWED_GAMEPHASE.getMessage());
        }
        return this.player.possibleBuildingsToString();
    }

    /**
     * Returns all buildings of the Player as String. Is extended in the Game Class
     * so GamePhases can be checked.
     * 
     * @return the buildings of the active Player as String
     * @throws LogicException if called in unallowed GamePhase.
     */
    public String returnBuildingsToString() throws LogicException {
        if (this.phase.equals(GamePhase.START)) {
            throw new LogicException(ErrorMessages.UNALLOWED_GAMEPHASE.getMessage());

        }
        return this.player.returnBuildingsToString();
    }

    /**
     * Returns all drawn Cards of the Player as String. Is extended in the Game
     * Class so GamePhases can be checked.
     * 
     * @return the drawn Cards of the active Player as String
     * @throws LogicException if called in unallowed GamePhase.
     */
    public String drawCardsToString() throws LogicException {
        if (this.phase.equals(GamePhase.START)) {
            throw new LogicException(ErrorMessages.UNALLOWED_GAMEPHASE.getMessage());
        }
        return this.player.drawCardsToString();
    }

}
