package tasktwo.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * The enum containing all buildables. Each object also contains the number of
 * wood,metal,plastic that is needed to build the object.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public enum Buildables {
    /**
     * Axe.
     */
    AXE("axe", 0, 3, 0),
    /**
     * Club.
     */
    CLUB("club", 3, 0, 0),
    /**
     * Shack
     */
    SHACK("shack", 2, 1, 2),
    /**
     * Fireplace
     */
    FIREPLACE("fireplace", 3, 1, 0),
    /**
     * Sailingcraft
     */
    SAILINGRAFT("sailingraft", 4, 2, 2),
    /**
     * Hangglider
     */
    HANGGLIDER("hangglider", 2, 2, 4),
    /**
     * Ballon
     */
    BALLON("ballon", 1, 0, 6),
    /**
     * Steamboat
     */
    STEAMBOAT("steamboat", 0, 6, 1);

    private final String name;
    private final int metalCount;
    private final int plasticCount;
    private final int woodCount;

    /**
     * Constructor
     * 
     * @param name         the name of the buildable
     * @param woodCount    the number of wood needed.
     * @param metalCount   the number of metal needed.
     * @param plasticCount the number of plastic needed.
     */
    Buildables(String name, int woodCount, int metalCount, int plasticCount) {
        this.woodCount = woodCount;
        this.metalCount = metalCount;
        this.plasticCount = plasticCount;
        this.name = name;
    }

    /**
     * Converts a input String into the fitting Buildable.
     * 
     * @param input the String that should be matched.
     * @return the buildable or null.
     */
    public static Buildables convertToBuildable(String input) {
        for (Buildables buildable : Buildables.values()) {
            if (buildable.name.equals(input)) {
                return buildable;
            }
        }
        return null;
    }

    /**
     * Static method that gets a number of wood,metal and plastic. Then checks which
     * enum Object has the given number of resources. Returns a list of all possible
     * Builds.
     * 
     * @param woodCount    the number of wood.
     * @param metalCount   the number of metal.
     * @param plasticCount the number of plastic.
     * @return a List of Buildables that could be build with the given Resources.
     */
    public static List<Buildables> checkForPossibleBuilds(int woodCount, int metalCount, int plasticCount) {
        List<Buildables> possibleBuilds = new ArrayList<Buildables>();
        for (Buildables buildable : Buildables.values()) {
            if (buildable.woodCount <= woodCount && buildable.metalCount <= metalCount
                    && buildable.plasticCount <= plasticCount) {
                possibleBuilds.add(buildable);
            }
        }
        return possibleBuilds;
    }

    /**
     * Getter for the amount of metal needed to build.
     * 
     * @return the amount of metal needed to build.
     */
    public int getMetalCount() {
        return metalCount;
    }

    /**
     * Getter for the amount of plastic needed to build.
     * 
     * @return the amount of plastic needed to build.
     */
    public int getPlasticCount() {
        return plasticCount;
    }

    /**
     * Getter for the amount of wood needed to build.
     * 
     * @return the amount of wood needed to build
     */
    public int getWoodCount() {
        return woodCount;
    }

    /**
     * Getter for the name of the Buildable.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns if a Buildable leads to a direct Endeavor.
     * 
     * @return true if it leads to direct Endeavor, false if not.
     */
    public boolean isDirectEndeavor() {
        return this.equals(Buildables.BALLON) || this.equals(Buildables.STEAMBOAT);
    }

    /**
     * Returns if a Buildable leads to a Endeavor possibility.
     * 
     * @return true if it leads to a possibility, false if not.
     */
    public boolean isEndeavor() {
        return this.equals(Buildables.SAILINGRAFT) || this.equals(Buildables.HANGGLIDER);
    }
}