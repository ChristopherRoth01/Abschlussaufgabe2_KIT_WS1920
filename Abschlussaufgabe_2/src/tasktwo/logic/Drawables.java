package tasktwo.logic;

/**
 * The enum, which contains all Drawables.
 * 
 * @author Christopher Roth
 * @version 1.0
 *
 */
public enum Drawables {

    /**
     * Wood.
     */
    WOOD("wood"),
    /**
     * Metal.
     */
    METAL("metal"),
    /**
     * Plastic.
     */
    PLASTIC("plastic"),
    /**
     * Spider.
     */
    SPIDER("spider"),
    /**
     * Snake.
     */
    SNAKE("snake"),
    /**
     * Tiger.
     */
    TIGER("tiger"),
    /**
     * Thunderstorm.
     */
    THUNDERSTORM("thunderstorm");

    private String message;

    /**
     * Constructor.
     * 
     * @param message the String representation of the Enum-Object.
     */
    Drawables(String message) {
        this.message = message;
    }

    /**
     * Getter for the message of the enum.
     * 
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * A static Method which converts a given String into the fitting Enum Object.
     * 
     * @param input the String to match
     * @return the fitting Enum Object or null
     */
    public static Drawables convertToDrawable(String input) {
        for (Drawables drawable : Drawables.values()) {
            if (drawable.getMessage().equals(input)) {
                return drawable;
            }
        }
        return null;
    }

    /**
     * Checks if a Object is a Animal.
     * 
     * @return true if animal false if not
     */
    public boolean isAnimal() {
        return this.equals(Drawables.SNAKE) || this.equals(Drawables.SPIDER) || this.equals(Drawables.TIGER);
    }

    /**
     * Checks if a Object is a Catastrophe.
     * 
     * @return true if catastrophe, false if not.
     */
    public boolean isCatastrophe() {
        return this.equals(Drawables.THUNDERSTORM);
    }

    /**
     * Checks if a Object is a Resource.
     * 
     * @return true if Resource, false if not.
     */
    public boolean isResource() {
        return this.equals(Drawables.WOOD) || this.equals(Drawables.METAL) || this.equals(Drawables.PLASTIC);
    }
}
