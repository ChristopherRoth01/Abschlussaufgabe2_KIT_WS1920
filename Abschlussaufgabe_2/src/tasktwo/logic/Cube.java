package tasktwo.logic;

import java.util.ArrayList;
import java.util.List;

import tasktwo.userinterface.ErrorMessages;

/**
 * This class represents a Cube used to play Games. A cube can either have 4,6
 * or 8 sides.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public class Cube {
    private final List<Integer> cubeTypes = new ArrayList<Integer>() {
        {
            add(4);
            add(6);
            add(8);
        }
    };
    /**
     * The type of the cube.
     */
    private final int cubeType;
    /**
     * The number of the cube. Not final because the usage of an axe or club could
     * raise the cubeNumber.
     */
    private int cubeNumber;

    /**
     * Constructor.
     * 
     * @param cubeType   the type of the Cube. (4,6,8)
     * @param cubeNumber the number that normally would be diced. But to test the
     *                   Program a cube gets the number the Player diced.
     * @throws LogicException if the cube cannot be build like the user wants to.
     *                        Errors could be either a wrong cubeType, or a
     *                        cubeNumber higher than the cubeType.
     */
    public Cube(int cubeType, int cubeNumber) throws LogicException {
        if (cubeTypes.contains(cubeType)) {
            this.cubeType = cubeType;
        } else {
            throw new LogicException(ErrorMessages.UNALLOWED_CUBE.getMessage());
        }
        if (!(cubeNumber <= cubeType) || cubeNumber == 0) {
            throw new LogicException(ErrorMessages.UNALLOWED_CUBE.getMessage());
        }
        this.cubeNumber = cubeNumber;
    }

    /**
     * Getter for the type of the cube.
     * 
     * @return the type of the cube.
     */
    public int getCubeType() {
        return cubeType;
    }

    /**
     * Getter for the number of the cube.
     * 
     * @return the number of the cube.
     */
    public int getCubeNumber() {
        return cubeNumber;
    }

    @Override
    public String toString() {
        return cubeType + " " + cubeNumber;

    }

    /**
     * Method that applies a Bonus to a Cube depending on the Players buildings. An
     * axe gives a bonus of two, a club a bonus of one.
     * 
     * @param player the player of the game
     */
    public void applyBonus(Player player) {
        final int axeBonus = 2;
        final int clubBonus = 1;
        if (player.hasBuilding(Buildables.AXE) != null) {
            this.cubeNumber = cubeNumber + axeBonus;
        } else if (player.hasBuilding(Buildables.CLUB) != null) {
            this.cubeNumber = cubeNumber + clubBonus;
        }
    }

    /**
     * Checks if a Cube is valid for the last drawn Card.
     * 
     * @param lastDrawnCard the last drawn card
     * @return true if valid, false if not
     */
    public boolean match(Card lastDrawnCard) {
        if (lastDrawnCard.getDrawable().equals(Drawables.SPIDER)) {
            return this.isTypeFour();
        } else if (lastDrawnCard.getDrawable().equals(Drawables.SNAKE)) {
            return this.isTypeSix();
        } else if (lastDrawnCard.getDrawable().equals(Drawables.TIGER)) {
            return this.isTypeEight();
        }
        return false;
    }

    /**
     * Returns the necessary Number needed to survive an animal Attack.
     * 
     * @param lastDrawnCard the card the user draw Last.
     * @return the necessary Number.
     */
    public int getNecessaryNumber(Card lastDrawnCard) {
        final int necessarySpider = 2;
        final int necessarySnake = 3;
        final int necessaryTiger = 4;
        if (lastDrawnCard.getDrawable().equals(Drawables.SPIDER)) {
            return necessarySpider;
        } else if (lastDrawnCard.getDrawable().equals(Drawables.SNAKE)) {
            return necessarySnake;
        } else if (lastDrawnCard.getDrawable().equals(Drawables.TIGER)) {
            return necessaryTiger;
        }
        return 0;
    }

    /**
     * Returns if a Cube has six sides.
     * 
     * @return true if six sides, false if not.
     */
    public boolean isTypeSix() {
        return this.cubeType == 6;
    }

    /**
     * 
     * Returns if a Cube has eight sides.
     * 
     * @return true if eight sides, false if not.
     */
    public boolean isTypeEight() {
        return this.cubeType == 8;
    }

    /**
     * 
     * Returns if a Cube has four sides.
     * 
     * @return true if four sides, false if not.
     */
    public boolean isTypeFour() {
        return this.cubeType == 4;
    }
}
